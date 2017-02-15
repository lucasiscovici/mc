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

/**
 * Service de suprression d'un User
 * GET: KEY + ID | KEY + TYPE=ALL | KEY + LOGIN | KEY
 * OUT: RESPONSE:OK
 */
public class SRemoveUser extends Service {

	public SRemoveUser() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		// TODO Auto-generated constructor stub
	}

	public SRemoveUser(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public SRemoveUser(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SRemoveUser(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
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
				db_User_Helper dUH= db_User_Helper.c();
				if (params.getDicosOK("id")) { // KEY + ID -
					if (!dUH.RemoveWithId(params)) {
						RespS.c(this, Error.SqlError.detail("remove w/Id"));
						return;
					}
				}else if (params.getDicosOK("type") && params.getValue("type").equals("ALL")) { // KEY + TYPE=ALL - 
					if (!dUH.RemoveWith(null)) {
						RespS.c(this, Error.SqlError.detail("remove w/type=ALL"));
						return;
					}
				}else if (params.getDicosOK("login")) { // KEY + LOGIN
					if (!dUH.removeWithLogin(params)) {
						RespS.c(this, Error.SqlError.detail("remove w/login"));
						return;
					}
				}else{
					if (!dUH.RemoveWithKey(params)) { // KEY
						RespS.c(this, Error.SqlError.detail("remove w/key"));
						return;
					}
				}
				Local_params.AddParamRespOK();
				RespS.cj(this);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
