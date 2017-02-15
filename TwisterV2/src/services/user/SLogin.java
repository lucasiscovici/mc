package services.user;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Session_Helper;
import db.db_User_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;
import util.Usefull;

/**
 * Classe du service connexion
 */

public class SLogin extends Service {
	
	/**
	 * Constructeur SLogin()
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SLogin() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur SLogin(Parameters params)
	 * @param params Ensemble de nos paramètre
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SLogin(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur SLogin(Parameters params, HttpServletResponse resp)
	 * @param params Ensemble de nos paramètres
	 * @param resp Notre réponse
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SLogin(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur SLogin(HttpServletRequest req, HttpServletResponse resp)
	 * @param req Notre requête
	 * @param resp Notre réponse
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SLogin(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Méthode qui exécute notre service
	 */

	// KOKO DE BASE -> SERVICE LOGIN
	// @SuppressWarnings("static-access")
	public void koko() {
		try {
			
			if (TestError.params(this)) { // CHECK PARAMS

				// CHECK IF USER EXIST
				if (!db_User_Helper.c().CheckIfExist(params)) {
					RespS.c(this, Error.LoginNotExist);
					return;
				}

				// CHECK IF GOOD PASSWORD
				if (!db_User_Helper.c().CheckPassword(params)) {
					RespS.c(this, Error.BadPassword);
					return;
				}

				// GET UNIQUE KEY
				String key = Usefull.uniqueID();

				// CREATE SESSION FOR USER (id_user)
				if (!db_Session_Helper.c().Insert(params.AddParam("key", key))) {
					RespS.c(this, Error.SqlError.setDescription("Pb insertion session"));
					return;
				}
				
				// FOR RESP JSON
				Local_params.AddParam("response", params.copy().kill("id").PS("id_user", "key", "login").change("id_user", "id"));
				
				RespS.cj(this);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException.detail(e.getMessage()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.NumberFormatException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException.detail(e.getMessage()));
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.LucasException.detail(e.getMessage()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Récupération du retour json
	 */

	// CALL FOR RESPONSE JSON
	public Parameters to_json() {
		return Dico.vT_toP(this, "response");
	}
	
	/**
	 * Méthode permettant de récupérer nos entrée
	 */

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_a("login", "password");
	}

}
