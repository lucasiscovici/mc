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
 * Classe du service création utilisateur
 * GET: LOGIN + PRENOM + NOM + PASSWORD
 * OUT: RESPONSE:OK
 */

public class SCreateUser extends Service {
	
	/**
	 * Constructeur SCreateUser()
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

//Constructeurs + Appelle desConstructeurs de Service
	public SCreateUser() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
	}
	
	/**
	 * Constructeur SCreateUser(Parameters params)
	 * @param params Nos paramètres
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SCreateUser(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
	}
	
	/**
	 * Constructeur SCreateUser(Parameters params, HttpServletResponse resp)
	 * @param params Nos paramètres
	 * @param resp Notre réponse
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SCreateUser(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
	}
	
	/**
	 * Constructeur SCreateUser(HttpServletRequest req, HttpServletResponse resp)
	 * @param req Notre requête
	 * @param resp Notre réponse
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SCreateUser(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}
	
//OVERRIDE DES FCTS OBLIGATOIRES
	
	//Liste des entrées obligatoires
	/**
	 * Méthode permettant de récupérer nos entrée
	 */
	@Override
	public String[] giveGetEntry() {
		return Dico.vs_a("prenom", "nom", "login", "password");
	}

	/**
	 * Récupération du retour json
	 */
	//Liste des sorties Json (nom des dictionnaire Dico, a prendre dans Local_params, pour construire la reponse JSON)
	@Override
	public Parameters to_json() {
		return Dico.response(this);
	}
	
	/**
	 * Méthode qui exécute notre service
	 */
	
	//FCT PRINCIPALE DU SERVICE (C'est la fonction qui est appellé apres de la creation du service) 
	@Override
	public void koko() {
		try {
			
			if (TestError.params(this)) { // CHECK ERR PARAMS

				if (db_User_Helper.c().CheckIfExist(params)) { // CHECK IF LOGIN ALREADY EXIST IN DB
					RespS.c(this, Error.LoginExist); // CREATE AN RespS WITH THIS ERROR AND SAVE THIS ONE IN THE VARIABLE RespS OF Service
					return;
				}

				if (!db_User_Helper.c().Insert(params)) { // INSERT THE NEW USER IN DB
					RespS.c(this, Error.SqlError); // PUT IN Var RespS of Service this RespS 
					return;
				}

				Local_params.AddParamRespOK(); // CREATE THE RESPONSE FOR JSON OUTPUT
				RespS.cj(this); // CALL THE PRINT FOR JSON (call the function to_json of this service)
			
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.NumberFormatException);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		}
	}

}
