package db;

import java.net.UnknownHostException;
import java.sql.SQLException;

import util.LucasException;
import util.Parameters;
import util.Usefull;
//import util.io;

public class db_Post_Helper {
	public static String My_Table = Tables.Post;
	public static String date = "date";
	public static String text = "text";
	public static String id_user = "id_user";
	public static String id_post = null;
	
	public boolean idPostOK() {
		return id_post != null;
	}
	public static boolean insertPost(Parameters params) throws UnknownHostException, ClassNotFoundException, SQLException {
		params.AddParam("date", Usefull.currentDate());
		params.AddParam("id_user", db_User_Helper.getIdWithKey(params));	
		params.AddParam("id", db_Post_Helper.listPost(null).parameters.size());	
		if (db_Helper.insertMongoOK(My_Table, params.PS("text", "id_user", "date","id"))){
			id_post = params.getValue("id");
			return true;
		}else{
			id_post = null;
			return false;
		}
	}
	public static Parameters listPost(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		return db_Helper.selectMongo(My_Table, params);
	}
	public static boolean removePost(Parameters params) throws UnknownHostException {
		return db_Helper.deleteMongoOK(My_Table, params.PS("id"));
	}
	
	
	public static Parameters listPostFromKey(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		Parameters p2 = params.copy().AddParam(id_user, db_User_Helper.getIdWithKey(params));
		return db_Helper.selectMongo(My_Table, p2.PS(id_user));
	}
	public static Parameters listPostFromFriends(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		Parameters p2 = db_Friend_Helper.listFriendsFromKey(params);
		return db_Helper.selectMongoIn(My_Table,"id_user",p2.getValues("id_friend"));
	}
}
