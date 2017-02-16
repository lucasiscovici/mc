package services.post;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;

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
 * Classe du service suppression commentaire
 * GET: KEY + ID | KEY + TYPE=ALL | KEY + TYPE=MY
 * OUT: RESPONSE:OK
 */

public class SRemovePost extends Service {
	
	
	public SRemovePost() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}

	public SRemovePost(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	public SRemovePost(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SRemovePost(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
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
		try {

			if (TestError.params_auth(this)) {
				
				db_Post_Helper dPH = db_Post_Helper.c();
				if (params.getDicosOK("id")) { // KEY + ID -
					if (!dPH.Remove(params)) {
						RespS.c(this, Error.MongoError.detail("remove post"));
						return;
					}
				}else if (params.getDicosOK("type")) { // KEY + TYPE
					String VType = params.getValue("type"); 
					if (VType.equals("ALL")) { // TYPE=ALL - 
						if (!dPH.RemoveMongoWith(null)) {
							RespS.c(this, Error.MongoError.detail("remove post"));
							return;
						}
					}else if (VType.equals("MY")) { //TYPE=MY - 
						if (!dPH.removeMine(params)) {
							RespS.c(this, Error.MongoError.detail("remove post"));
							return;
						}
					}else{
						RespS.c(this, Error.ErrArgs);
					}
				}else{
					RespS.c(this, Error.ErrArgs);
					return;
				}
				this.Local_params.AddParamRespOK();
				RespS.cj(this);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.LucasException);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
