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
import util.TestError;

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
	

	public SCreateUser(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		return Dico.vs_a("prenom", "nom", "login", "password");
	}

	@Override
	public Parameters to_json() {
		return Dico.vT_toP(this, "response");
	}

	@Override
	public void koko() {
		try {
			if (TestError.params(this)) {

				if (!db_User_Helper.CheckIfExist(params)) {
					RespS.c(this, Error.LoginExist);
					return;
				}
				
				if (!db_User_Helper.InsertUser(params)) {
					RespS.c(this, Error.SqlError);
					return;
				}
				
				Local_params.AddParam("response", "OK");
				RespS.cj(this);
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
<<<<<<< HEAD
			RespS.c(this, Error.NumberFormatException);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
=======
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
>>>>>>> ac6e7af607ffcc8bb2755005dab74c9ffbbf2604
		}
	}

}
