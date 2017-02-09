package db;

import java.net.UnknownHostException;
import java.sql.SQLException;

import util.Parameters;
import util.Usefull;

public class db_Post_Helper {
	public static String My_Table = Tables.Post;
	public static String date = "date";
	public static String text = "text";
	public static String id_user = "id_user";
	
	public static boolean insertPost(Parameters p) throws UnknownHostException, ClassNotFoundException, SQLException {
		p.AddParam("date", Usefull.currentDate());
		p.AddParam("id_user", db_User_Helper.getIdWithKey(p));	
		return db_Helper.insertMongoOK(My_Table, p.PS("text", "id_user", "date"));
	}
	

	public static Parameters listPostFromKey(Parameters p) throws ClassNotFoundException, SQLException, UnknownHostException {
		Parameters p2 = p.copy().AddParam(id_user, db_User_Helper.getIdWithKey(p));
		return db_Helper.selectMongo(My_Table, p2.PS(id_user));
	}
}
