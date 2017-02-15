package services.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_User_Helper;
import services.utils.Service;
import util.Dico;
import util.LucasException;
import util.Parameters;
import util.TestError;

/**
 * SERVICE POuR LISTER LES USERS 
 * GET: KEY | KEY + TYPE=ALL | KEY + ID 
 * OUT: RESPONSE:USERS:ID,PRENOM,NOM,LOGIN,PASSWORD | RESPONSE:USERS:[ID,PRENOM,NOM,LOGIN,PASSWORD]
 */
public class SListUsers extends Service {

	public SListUsers() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		// TODO Auto-generated constructor stub
	}

	public SListUsers(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public SListUsers(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SListUsers(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_ak();
	}

	@Override
	public Parameters to_json() {
		// TODO Auto-generated method stub
		return Dico.response(this);
	}

	@Override
	public void koko() {
		// TODO Auto-generated method stub
		try {

			if (TestError.params_auth(this)) {
				Parameters users;
				db_User_Helper dUH = db_User_Helper.c();

				if (params.getDicosOK("id")) { // KEY + ID - 
					users = dUH.SelectWithId(params);
				} else if (params.getDicosOK("type") && params.getValue("type").equals("ALL")) { // KEY + TYPE=ALL -
					users = dUH.SelectWith(null);
				} else { // KEY -
					users = dUH.SelectWith(null);
				}

				Local_params.AddParamResponse("users", users);
				RespS.cj(this);

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
