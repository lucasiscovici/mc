package services.post;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Post_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;

/**
 * Classe du service recherche
 */

public class SSearch extends Service {
	
	/**
	 * Constructeur SSearch()
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SSearch() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur SSearch(HttpServletRequest req, HttpServletResponse resp)
	 * @param req Notre requête
	 * @param resp Notre réponse
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SSearch(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur SSearch(Parameters params, HttpServletResponse resp)
	 * @param params Nos paramètres
	 * @param resp Notre réponse
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SSearch(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur SSearch(Parameters params)
	 * @param params Nos paramètres
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SSearch(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Méthode permettant de récupérer nos entrée
	 */

	@Override
	public String[] giveGetEntry() {
		return Dico.vs_a("key");
	}
	
	/**
	 * Récupération du retour json
	 */

	@Override
	public Parameters to_json() {
		return Dico.vT_toP(this, "response");
	}
	
	/**
	 * Méthode permettant d'exécuter notre service
	 */

	@Override
	public void koko() {
		try {
			if (TestError.params_auth(this)) {

				if (params.getDicos("friends").parameters.size() > 0) {
					this.Local_params.AddParam("messages", db_Post_Helper.c().listPostFromIdFriends(params));
				} else {
					this.Local_params.AddParam("messages", db_Post_Helper.c().listPostFromKey(params));
				}
				this.Local_params.AddParamRIN("response","messages");
				RespS.cj(this);
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.UnknownHostException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException.detail(e.getMessage()));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.LucasException);
		}
	}

}
