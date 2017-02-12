package services.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_User_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.io;

public class SCreateUser extends Service {

	
	
	public SCreateUser() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}

	public SCreateUser(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	public SCreateUser(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SCreateUser(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		return Dico.vs_a("prenom","nom","login","password");
	}

	@Override
	public Parameters to_json() {
		return null;
	}

	@Override
	public void koko() {
		if (params.CheckIfErrParams(getEntry)) {
			io.print_json_or_print(response, Error.ErrArgs.depuis(this));
		}else{

			
			if (db_User_Helper.CheckIfExist(params)) {
				io.print_json_or_print(response, Error.LoginExist.depuis(this));
			}else{
				if (db_User_Helper.InsertUser(params)){
					io.print_json_or_printFromString(response, "OK");
				}else{
					io.print_json_or_print(response, Error.SqlError.setDescription("pb d'ajout d'un user",this));
				}
				
			}
		}

	}

}
