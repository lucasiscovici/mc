package services.post;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Post_Helper;
import db.db_Session_Helper;
import services.utils.Service;
import util.Dico;
import util.LucasException;
import util.Parameters;
import util.TestError;
import util.Error;

/**
 * Classe du service recherche bis
 */
public class SSearchBis extends Service {
	
	/**
	 * Constructeur SSearchBis()
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SSearchBis() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur SSearchBis(HttpServletRequest req, HttpServletResponse resp)
	 * @param req Notre requête
	 * @param resp Notre réponse
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SSearchBis(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur SSearchBis(Parameters params, HttpServletResponse resp)
	 * @param params Nos paramètres
	 * @param resp Notre réponse
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SSearchBis(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur SSearchBis(Parameters params)
	 * @param params Nos paramètres
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SSearchBis(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
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
			if (TestError.params(this)) {

				if (params.getValue("key").length() > 0 && db_Session_Helper.c().Auth(params)) {
					if (params.getValue("ok_friends").length() > 0 && params.getValue("ok_friends").equals("true")) {
						this.Local_params.AddParam("messages", db_Post_Helper.c().listPostFromFriends(params.PS("key")));
						this.Local_params.AddParam("response", this.Local_params.getDico("messages"));

						RespS.cj(this);

					} else {
						this.Local_params.AddParam("messages", db_Post_Helper.c().listPostFromKey(params.PS("key")));
						this.Local_params.AddParam("response", this.Local_params.getDico("messages"));
						RespS.cj(this);
					}
				} else {
					this.Local_params.AddParam("response", "pb");
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, util.Error.ClassNotFoundException);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.UnknownHostException);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException);
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.LucasException);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);

		}
	}

}
