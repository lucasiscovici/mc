package services.user;

import java.io.IOException;
import java.sql.SQLException;

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
	}

	public SCreateUser(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
	}

	public SCreateUser(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
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
			RespS.c(this, Error.ErrArgs);
			return;
		}else{

			
			try {
				if (db_User_Helper.CheckIfExist(params)) {
					RespS.c(this, Error.LoginExist);
					return;
				}else{
					if (db_User_Helper.InsertUser(params)){
						io.print_json_or_printFromString(response, "OK");
					}else{
						RespS.c(this, Error.SqlError);
						return;
					}
					
				}
				RespS.cj(this);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
