package services.session;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Session_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;

/**
 * Classe du service déconnection (SESSION)
 * GET: KEY | KEY + TYPE=ALL | KEY + ID
 * OUR: RESPONSE:OK
 */
public class SLogout extends Service {
	
	/**
	 * Constructeur SLogout()
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SLogout() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur (Parameters params, HttpServletResponse resp)
	 * @param params Ensemble de paramètre
	 * @param resp Response de notre requête
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SLogout(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur SLogout(Parameters params)
	 * @param params Ensemble de paramètre
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SLogout(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur SLogout(HttpServletRequest req, HttpServletResponse resp)
	 * @param req Notre requête
	 * @param resp La réponse de notre requête
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SLogout(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Méthode permettant de récupérer nos entrée
	 */

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_ak();
	}
	
	/**
	 * Récupération du retour json
	 */

	@Override
	public Parameters to_json() {
		// TODO Auto-generated method stub
		return Dico.response(this);
	}
	
	/**
	 * Méthode qui exécute notre service
	 */

	@Override
	public void koko() {
		// TODO Auto-generated method stub
		try {
			
			if (TestError.params(this)) {
				
				db_Session_Helper dSH = db_Session_Helper.c();
				
				if (params.getDicosOK("id")) {
					if (!dSH.RemoveWithId(params)) {
						RespS.c(this, Error.SqlError.detail("pb id"));
						return;
					}
				}else if (params.getDicosOK("type") && params.getValue("type").equals("ALL")) {
					if (!dSH.RemoveWith(params)) {
						RespS.c(this, Error.SqlError.detail("pb all"));
						return;
					}
				}else{
					if (!db_Session_Helper.c().Remove(params)) {
						RespS.c(this, Error.SqlError);
						return;
					}
				}
				Local_params.AddParamRespOK();
				RespS.cj(this);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.LucasException);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
