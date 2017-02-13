/**
 * 
 */
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
 * @author lucasiscovici
 *
 */
public class SLogout extends Service {

	public SLogout() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}

	public SLogout(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SLogout(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public SLogout(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_a("key");
	}

	@Override
	public Parameters to_json() {
		// TODO Auto-generated method stub
		return Dico.vT_toP(this, "response");
	}

	@Override
	public void koko() {
		// TODO Auto-generated method stub
		try {
			if (TestError.params(this)) {

				if (!db_User_Helper.DeleteSession(params)) {
					RespS.c(this, Error.SqlError);
					return;
				}

				Local_params.AddParam("response", "ok");
				RespS.cj(this);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
<<<<<<< HEAD
			RespS.c(this, Error.SQLException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
=======
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
