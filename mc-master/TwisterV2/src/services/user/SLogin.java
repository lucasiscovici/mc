package services.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_User_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.JSONHelper;
import util.LucasException;
import util.Parameters;
import util.Usefull;
import util.io;

public class SLogin extends Service {
  
	

	public SLogin() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}

	public SLogin(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public SLogin(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	//KOKO DE BASE -> SERVICE LOGIN
	public  void koko() throws IOException, NumberFormatException, ClassNotFoundException, SQLException, JSONException, LucasException {
		if (params.CheckIfErrParams(getEntry)) { // CHECK PARAMS
			 try {
				io.print_json_or_print(this.response, Error.ErrArgs.depuis(this).to_JSON());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//BD CONNEXION
			//CHECK IF USER EXIST			
			if (!db_User_Helper.CheckIfExist(params)) {
				io.print_json_or_print(this.response, Error.LoginNotExist.depuis(this));
				return;
			}
			//CHECK IF GOOD PASSWORD
			if (!db_User_Helper.CheckPassword(params)) {
				io.print_json_or_print(this.response, Error.BadPassword.depuis(this));
				return;
			}
			
			//UNIQUE KEY
			String key = Usefull.uniqueID();
			
			try {
				//GET ID FROM LOGIN 
				
				//CREATE SESSION FOR USER (id_user)
				if (!db_User_Helper.InsertSession(params.AddParam("key",key))) {
					io.print_json_or_print(this.response, Error.SqlError.setDescription("Pb insertion session",this));
					return;
				}
				this.Local_params.AddParam(params, "id_user","key","login").change("id_user", "id");
				
				io.print_json_or_print(this.response,JSONHelper.to_json(this)); 
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// CALL FOR RESPONSE JSON
	public Parameters to_json() {
		return Dico.vT_toP(this,"key","id","login");
	}

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_a("login","password");
	}

}
