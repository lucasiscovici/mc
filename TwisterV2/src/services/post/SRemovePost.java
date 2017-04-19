package services.post;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Post_Helper;
import db.util.db_crud;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;
import util.io;

/**
 * Classe du service suppression commentaire
 * GET: KEY + ID | KEY + TYPE=ALL | KEY + TYPE=MY
 * OUT: RESPONSE:OK
 */

/**
 * @author lucasiscovici
 *
 */
public class SRemovePost extends Service {
	db_Post_Helper H = null;
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
	 * FONCTIONS UTILISÉES
	 * @param params
	 * @param dPH
	 * @return
	 * @throws ClassNotFoundException
	 * @throws UnknownHostException
	 * @throws SQLException
	 * @throws LucasException
	 */
	private boolean Key_ID() throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		if (!H.Remove(params)) {
			RespS.c(this, Error.MongoError.detail("remove post"));
			return false;
		}
		return true;
	}
	private boolean Key_ID_Check() throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		
		if (!H.checkIfSameIDUser(params)) {
			RespS.c(this, Error.NAUTH.detail("pas authauriser").setCode(190));
			return false;
		}
		return Key_ID();
	}
	private boolean Key_Type_ALL() throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {

		if (!H.RemoveMongoWith(null)) {
			RespS.c(this, Error.MongoError.detail("remove post"));
			io.print("feqqsfs");

			return false;
		}
		return true;
	}
	private boolean Key_Type_MY() throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		if (!H.removeMine(params)) {
			RespS.c(this, Error.MongoError.detail("remove post"));
			return false;
		}
		return true;
	}
	
	private boolean Ok() throws JSONException {
		this.Local_params.AddParamRespOK();
		RespS.cj(this);
		return true;
	}
	/**
	 * Méthode qui exécute notre service
	 * @throws LucasException 
	 * @throws SQLException 
	 * @throws UnknownHostException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public void koko() {
		H = new db_Post_Helper();
		boolean _;
		try {
			if ((TestError.params_authAdmin(this))) { // CHECK PARAMS + KEY ADMIN

				_ = (
					router("id") ? Key_ID() : false //KEY + ID
				 || router("type","ALL") ? Key_Type_ALL() : false // KEY + TYPE=ALL
			     	) ? 
			     	Ok() : 
			     	RespS.c(this, Error.ErrArgs); 				//Si pas de combinaison avec les params -> ErrorArgs sinon Ok
			
			
			}
			else if (TestError.params_auth(this)) {
			 	_ = (
					router("id" ) ? Key_ID_Check() : false 		//KEY + ID + CHECK_AUTH
			     || router("type","MY") ? Key_Type_MY() : false // KEY + TYPE=MY
			     	) ? 
			     	Ok() : 
			     	RespS.c(this, Error.ErrArgs); 				//Si pas de combinaison avec les params -> ErrorArgs sinon Ok	     
					     
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
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}