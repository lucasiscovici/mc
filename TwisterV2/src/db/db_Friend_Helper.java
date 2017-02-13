package db;

import java.sql.SQLException;

import util.LucasException;
import util.Parameters;
//import util.io;

public class db_Friend_Helper {
	
	public static String My_Table = Tables.Friend;
	
	public static String from = "from";
	public static String to = "to";
	
	public static String id_friend = "id_friend";
	public static String id_f = null;

	public static Parameters listFriendsFromKey(Parameters params) throws ClassNotFoundException, SQLException, LucasException {
		return db_Helper.selectAndWhere("to", My_Table, params.AddParam("from",db_User_Helper.getIdWithKey(params)).PS("from")).change("to", "id_friend");
	}
	public static boolean InsertFriend(Parameters p) throws SQLException, ClassNotFoundException, LucasException {

		Parameters p2 = p.copy().AddParam(from, db_User_Helper.getIdWithKey(p)).change(id_friend, to).PS(from,to);

		if (!p2.getValue(from).equals(p2.getValue(to))) {
			
			if (db_Helper.insertOK(My_Table, p2)){
				id_f = p2.getValue("id");
				return true;
			}else{
				return false;
			}

		}

		return false;
	}

	public static boolean RemoveFriend(Parameters p) throws SQLException, ClassNotFoundException, LucasException {

		Parameters p2 = p.copy().AddParam(from, db_User_Helper.getIdWithKey(p)).change(id_friend, to);

		return db_Helper.deleteOK(My_Table, p2.PS(from,to));

	}
}
