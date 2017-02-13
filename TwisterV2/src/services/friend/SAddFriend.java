package services.friend;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Friend_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;

public class SAddFriend extends Service {
	
	/**
	 * @return Service()
	 */

	public SAddFriend() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
	}
	
	/**
	 * @param params Ensemble de paramètre
	 * @param resp Reponse de la requête
	 * @return SAddFriend(params, resp)
	 */

	public SAddFriend(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
	}
	
	/**
	 * @param params Ensemble de paramètre
	 * @return SAddFriend(params)
	 */

	public SAddFriend(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
	}
	
	/**
	 * @param params Ensemble de paramètre
	 * @return SAddFriend(params)
	 */

	public SAddFriend(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return Dico.vs_a("id_friend", "key")
	 */

	@Override
	public String[] giveGetEntry() {
		return Dico.vs_a("id_friend", "key");
	}
	
	/**
	 * @return Dico.vT_toP(this, "reponse")
	 */

	@Override
	public Parameters to_json() {
		return Dico.vT_toP(this, "reponse");
	}
	
	/**
	 * @return la réponse 
	 */

	@Override
	public void koko() {
		try {
			if (TestError.params_auth(this)) {

				if (!db_Friend_Helper.InsertFriend(params)) {
					RespS.c(this, Error.SqlError.detail("PB Insert friend check ids"));
					return;
				}
				Local_params.AddParam("reponse", Dico.toP("id", db_Friend_Helper.id_f));
				RespS.cj(this);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.LucasException);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		}
	}

}
