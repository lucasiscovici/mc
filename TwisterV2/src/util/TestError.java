package util;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;

import db.db_Session_Helper;
import services.utils.Service;

/**
 * Classe de test de différentes erreurs
 */
public class TestError {
	
	/**
	 * CHECK params {@link TestError#params(Service)} et NAUTH {@link TestError#auth(Service)}
	 * @param th Un service
	 * @return true si pas de pb, false si erreur
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 * @throws LucasException 
	 */

	public static boolean params_auth(Service th) throws ClassNotFoundException, SQLException, ParseException, LucasException {
		if (!TestError.params(th) || !TestError.auth(th)) {
			return false;
		}		
		return true;
	}
	public static boolean params_authAdmin(Service th) throws ClassNotFoundException, SQLException, ParseException, LucasException {
		if (!TestError.params(th) || !TestError.authAdmin(th)) {
			return false;
		}		
		return true;
	}
	/**
	 * CHECK ERRPARAMS {@link util.Parameters#CheckIfErrParams(String[])}
	 * @param th Un service
	 * @return true si pas de pb, false si erreur
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public static boolean params(Service th) throws ClassNotFoundException, SQLException {
		Parameters params = th.params;
		String[] getEntry = th.getEntry;
		if (params.CheckIfErrParams(getEntry)) {
			RespS.c(th, Error.ErrArgs.detail(Arrays.asList(th.getEntry).toString()));
			return false;
		}
		return true;
	}
	
	/**
	 * CHECK NAUTH {@link db.db_Session_Helper#Auth(Parameters)}
	 * @param th Un service
	 * @return true si pas de pb, false si erreur
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 * @throws LucasException 
	 */
	public static boolean auth(Service th) throws ClassNotFoundException, SQLException, ParseException, LucasException {
		Parameters params = th.params;
		
		
		if (!db_Session_Helper.c().Auth(params)) {
			RespS.c(th, Error.NAUTH);
			return false;
		}
		return true;
	}
	public static boolean authAdmin(Service th) throws ClassNotFoundException, SQLException, ParseException, LucasException {
		Parameters params = th.params;
		if (params.getValue("key").equals("babar")) {
			return true;
		}
		return false;
	}
}
