package db;

import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.util.db;
import util.Dico;
import util.LucasException;
import util.Parameters;
import util.Usefull;
import util.io;

/**
 * Classe db_Session_Helper
 */

public class db_Session_Helper extends db {
	public String MyTable = Tables.Session;
	public static String session_id_user = "id_user";
	public static String session_key = "key";

	/**
	 * 
	 * @return new db_Session_Helper()
	 */
	
	public static db_Session_Helper c() {
		return new db_Session_Helper();
	}
	
	/**
	 * Constructeur db_Session_helper()
	 */

	public db_Session_Helper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return CheckIfExistWith(params.PS(session_key))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 * @throws LucasException 
	 */

	public boolean Auth(Parameters params) throws ClassNotFoundException, SQLException, ParseException, LucasException {
		if(CheckIfExistWith(params.PS(session_key))){
			Parameters date = SelectWith(params.PS("key"));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
			//io.print(date.getValue("timestamp"));
			java.util.Date d = (java.util.Date) formatter.parse(date.getValue("timestamp"));
			if (Usefull.addMinutesToDate(Tables.expire, d).before(Usefull.currentDate())) {
				return false;
			}else{
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        String dateForMySql = sdf.format(Usefull.currentDate());
				UpdateWithId(date.PS("id").AddParam("timestamp",dateForMySql));
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param params key
	 * @return id_user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public Integer getIdWithKey(Parameters params) throws ClassNotFoundException, SQLException {
		return this.getXWithX(session_id_user, params.PS(session_key)).getValueInt(session_id_user);
	}
	
	/**
	 * @return true si l'insertion c'est faites, false sinon
	 */

	@Override
	public boolean Insert(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		Parameters p2 = params.copy();
		int idWithLogin = db_User_Helper.c().getIdWithLogin(params);
		p2.AddParam(session_id_user, db_User_Helper.c().getIdWithLogin(params));

		if (SelectOK(p2.PS(session_id_user))) {
			DeleteOK(p2.PS(session_id_user));
		}
		p2 = p2.PS(session_id_user, session_key);
		
		if (InsertOK(p2)) {
			params.AddParam(p2, "id");
			//EXCEPTIONELLE
			params.AddParam(session_id_user,idWithLogin);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return this.RemoveWith(params.PS(session_key))
	 */

	@Override
	public boolean Remove(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.RemoveWith(params.PS(session_key));
	}
	
	/**
	 * @return this.UpdateWithId(params)
	 */

	@Override
	public boolean Update(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.UpdateWithId(params);
	}
	
	/**
	 * @return Tables.Session
	 */

	@Override
	public String GiveMyTable() {
		// TODO Auto-generated method stub
		return Tables.Session;
	}
	
	/**
	 * @return this.SelectWithId(params)
	 */

	@Override
	public Parameters Select(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.SelectWithId(params);
	}

	public Parameters SelectWithKey(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return SelectWith(params.PS("key"));
	}

	public boolean CheckIfExistWithAndValid(Parameters p) {
		// TODO Auto-generated method stub
		return false;
	}

}
