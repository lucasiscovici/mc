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
import util.Usefull;

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

	public SLogin(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	// KOKO DE BASE -> SERVICE LOGIN
	// @SuppressWarnings("static-access")
	public void koko() {
		try {
			
			if (TestError.params(this)) { // CHECK PARAMS

				// CHECK IF USER EXIST
				if (!db_User_Helper.CheckIfExist(params)) {
					RespS.c(this, Error.LoginNotExist);
					return;
				}

				// CHECK IF GOOD PASSWORD
				if (!db_User_Helper.CheckPassword(params)) {
					RespS.c(this, Error.BadPassword);
					return;
				}

				// GET UNIQUE KEY
				String key = Usefull.uniqueID();

				// CREATE SESSION FOR USER (id_user)
				if (!db_User_Helper.InsertSession(params.AddParam("key", key))) {
					RespS.c(this, Error.SqlError.setDescription("Pb insertion session"));
					return;
				}

				// FOR RESP JSON

				this.Local_params.AddParam("response", params.PS("id_user", "key", "login").change("id_user", "id"));

				RespS.cj(this);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// CALL FOR RESPONSE JSON
	public Parameters to_json() {
		return Dico.vT_toP(this, "response");
	}

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_a("login", "password");
	}

}
