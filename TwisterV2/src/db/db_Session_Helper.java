package db;

import java.net.UnknownHostException;
import java.sql.SQLException;

import db.util.db;
import util.LucasException;
import util.Parameters;

public class db_Session_Helper extends db {
	public static String MyTable = Tables.Session;
	public static String session_id_user = "id_user";
	public static String session_key = "key";

	public static db_Session_Helper c() {
		return new db_Session_Helper();
	}

	public db_Session_Helper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean Auth(Parameters params) throws ClassNotFoundException, SQLException {
		return CheckIfExistWith(params.PS(session_key));
	}

	public Integer getIdWithKey(Parameters params) throws ClassNotFoundException, SQLException {
		return this.getXWithX(session_id_user, params.PS(session_key)).getValueInt(session_id_user);
	}

	@Override
	public boolean Insert(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		params.AddParam(session_id_user, db_User_Helper.c().getIdWithLogin(params));

		if (SelectOK(params.PS(session_id_user))) {
			DeleteOK(params.PS(session_id_user));
		}
		Parameters p2 = params.PS(session_id_user, session_key);
		if (InsertOK(p2)) {
			params.AddParam(p2, "id");
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean Remove(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.RemoveWith(params.PS(session_key));
	}

	@Override
	public boolean Update(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.UpdateWithId(params);
	}

	@Override
	public String GiveMyTable() {
		// TODO Auto-generated method stub
		return this.MyTable;
	}

	@Override
	public Parameters Select(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.SelectWithId(params);
	}

}
