package services.utils;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.Tables;
import db.util.db_crud;
import util.RespS;
import util.IOLUCAS;
import util.IParameters;
import util.LucasException;
import util.Parameters;
import util.TOJSON;
import util.io;
//import util.io;

/**
 * Classe Service qui sera hérité par nos différents service
 */

public abstract class Service implements IOLUCAS, IParameters, TOJSON, ServiceKoko  {
	/**
	 * parametres du services Local (les parameters ou dicos dont la fonction to_json a besoin pour constuire la response JSON)
	 */
	public boolean router(String ok,Object value){
		if (params.getDicosOK(ok)){
			if (value!=null) {
				if (params.getValue(ok).equals(value)) {
					return true;
				}
			}else{
				return true;
			}
		}		
		return false;
	}
	public boolean router(String ok){
		return router(ok,null);
	}
	
	public static Service me = null;
	public Parameters Local_params = new Parameters(); 
	public HttpServletResponse response = null; // FROM IOLUCAS
	public HttpServletRequest request = null; // FROM IOLUCAS

	
	/**
	 * Entrées obligatoires pour le service
	 */
	public String[] getEntry = {}; // FROM IParameters
	/**
	 * parametres du services (avec les parametres d'entrés par def)
	 */
	public Parameters params = null; //
	/**
	 * Response du service -> soit erreur , soit response
	 */
	public RespS RespS = null;
	
	/**
	 * Constructeur Service()
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */
	
	public Service() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		this(new Parameters());
	}
	
	/**
	 * Constructeur Service(Parameters params)
	 * @param params L'ensemble de nos paramètres
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public Service(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		this(params,null);
	}
	
	/**
	 * Constructeur Service(Parameters params,HttpServletResponse resp)
	 * @param params L'ensemble de nos paramètres
	 * @param resp La réponse de l'exécution de notre requête
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public Service(Parameters params,HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		this.params = params;
		
		this.response = resp;
		
		getEntry = giveGetEntry();
		params.myService = this.me = this;
		koko();
	}
	
	/**
	 * Constructeur Service(HttpServletRequest req,HttpServletResponse resp)
	 * @param req Notre requête
	 * @param resp La réponse de l'exécution de notre requête
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */
	
	public Service(HttpServletRequest req,HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
	
		this(Parameters.req(req),resp);
		this.request = req;
	}
	
	/**
	 * Permettra d'afficher la réponse de notre requête (ERR, ou response)
	 * @throws IOException
	 * @throws JSONException
	 */
	public void print() throws IOException, JSONException {
		if (RespS != null) {
		RespS.print();
		}
	}
	

}
