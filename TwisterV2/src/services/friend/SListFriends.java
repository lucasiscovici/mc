package services.friend;

import java.io.IOException;
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
 * Classe du service lister ses amis
 * GET: KEY + ID | KEY + TYPE=ALL | KEY
 * OUT:RESPONSE:FRIENDS:FROM,TO,SINCE,ID  | RESPONSE:FRIENDS:[FROM,TO,SINCE,ID]  | RESPONSE:FRIENDS:[FROM=X,TO,SINCE,ID]  
 */
public class SListFriends extends Service {

	/**
	 * Constructeur SListFriends()
	 * 
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SListFriends() throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		// TODO Auto-generated constructor stub
	}


	public SListFriends(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur SListFriends(Parameters params, HttpServletResponse resp)
	 * 
	 * @param params
	 * @param resp
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SListFriends(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
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

			if (TestError.params_auth(this)) {
				Parameters friends;
				
				if (params.getDicosOK("id")) { // KEY + ID - 
					friends = db_Friend_Helper.c().SelectWithId(params);
				}else if(params.getDicosOK("type") && params.getValue("type").equals("ALL")) { // KEY + TYPE=ALL -
					friends = db_Friend_Helper.c().SelectWith(null);
				}else { // KEY - 
					friends = db_Friend_Helper.c().listFriendsFromKey(params);
				}
				
				Local_params.AddParamResponse("friends",friends);
				RespS.cj(this);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException);
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.LucasException);
		}
	}

}
