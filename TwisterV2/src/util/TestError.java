package util;

import java.sql.SQLException;
import java.util.Arrays;

import db.db_Session_Helper;
import services.utils.Service;

/**
 * Classe de test de différentes erreurs
 */

public class TestError {
	
	/**
	 * 
	 * @param th Un service
	 * @return true si erreur, false sinon
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public static boolean params_auth(Service th) throws ClassNotFoundException, SQLException {
		Parameters params = th.params;
		String[] getEntry = th.getEntry;
		if (params.CheckIfErrParams(getEntry)) {
			RespS.c(th, Error.ErrArgs.detail(Arrays.asList(th.getEntry).toString()));
			return false;		}

		if (!db_Session_Helper.c().Auth(params)) {
			RespS.c(th, Error.NAUTH);
			return false;

		}
		return true;
	}
	
	/**
	 * 
	 * @param th Un service
	 * @return true si erreur, false sinon
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public static boolean params(Service th) throws ClassNotFoundException, SQLException {
		Parameters params = th.params;
		String[] getEntry = th.getEntry;
		if (params.CheckIfErrParams(getEntry)) {
			RespS.c(th, Error.ErrArgs);
			return false;
		}
		return true;
	}
}
