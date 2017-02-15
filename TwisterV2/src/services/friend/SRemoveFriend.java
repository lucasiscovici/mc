package services.friend;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Friend_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;

/**
 * Classe du service Suppression d'ami
 * GET: KEY + ID | KEY + ID_FRIEND | KEY + TYPE=ALL
 * OUT: RESPONSE:OK
 */

public class SRemoveFriend extends Service {

	/**
	 * Constructeur SRemoveFriend()
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */
	
	public SRemoveFriend() throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		super();
	}
	
	/**
	 * Constructeur SRemoveFriend(Parameters params)
	 * @param params Nos paramètres
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SRemoveFriend(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
	}
	
	/**
	 * Constructeur SRemoveFriend(Parameters params, HttpServletResponse resp)
	 * @param params Nos paramètres
	 * @param resp Notre réponse
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SRemoveFriend(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
	}
	
	/**
	 * Méthode permettant de récupérer nos entrée
	 */

	@Override
	public String[] giveGetEntry() {
		return Dico.vs_ak();
	}
	
	/**
	 * Récupération du retour json
	 */

	@Override
	public Parameters to_json() {
		return Dico.response(this);
	}
	
	/**
	 * Méthode qui exécute notre service
	 */

	@Override
	public void koko() {

		try {
			if (TestError.params_auth(this)) {
				
				if (params.getDicosOK("id_friend")) {
					if (!db_Friend_Helper.c().RemoveWithKeyandID(params)) {
						RespS.c(this, Error.SqlError.detail("PB delete friend check id id_friend"));
						return;
					}
				}else if(params.getDicosOK("id")){
					if (!db_Friend_Helper.c().RemoveWithId(params)) {
						RespS.c(this, Error.SqlError.detail("PB delete friend check ids"));
						return;
					}
				}else if(params.getDicosOK("type") && params.getValue("type").equals("ALL")){
					if (!db_Friend_Helper.c().RemoveWith(null)) {
						RespS.c(this, Error.SqlError.detail("PB delete friend all"));
						return;
					}
				}
				
				else{
					RespS.c(this, Error.ErrArgs);
					return;
				
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
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.LucasException);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
