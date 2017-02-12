package util;

import java.sql.SQLException;

import db.db_User_Helper;
import services.utils.Service;

public class TestError {

	public static boolean params_auth(Service th) throws ClassNotFoundException, SQLException {
		Parameters params = th.params;
		String[] getEntry = th.getEntry;
		if (params.CheckIfErrParams(getEntry)) {
			RespS.c(th, Error.ErrArgs);
			return false;
		}

		if (!db_User_Helper.Auth(params)) {
			RespS.c(th, Error.NAUTH);
			return false;

		}
		return true;
	}
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
