package db;

import java.sql.SQLException;

import util.Parameters;

public class db_Friend_Helper {
	public static String My_Table = "Friend";

public static boolean InsertFriend(Parameters dico) throws SQLException, ClassNotFoundException {
	if (!dico.getValue("from").equals(dico.getValue("to"))) {
		
	
	return db_Helper.insertOK(My_Table, dico.PS("from","to"));
	}
	return false;
}

public static boolean RemoveFriend(Parameters dico) throws SQLException, ClassNotFoundException {
		
	
	return db_Helper.deleteOK(My_Table, dico.PS("from","to"));
	}
}
