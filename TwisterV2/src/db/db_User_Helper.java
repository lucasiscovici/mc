package db;

import java.sql.SQLException;
import util.Parameters;

public class db_User_Helper {
	
	public static String My_Table = Tables.User;
	public static String login = "login";
	public static String prenom = "prenom";
	public static String nom = "nom";
	public static String password = "password";
	
	public static String session_id_user = "id_user";
	public static String session_key = "key";


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public static boolean CheckIfExist(Parameters params) throws NumberFormatException, SQLException, ClassNotFoundException {
		return db_Helper.selectOK(My_Table,params.PS(login)); 
	}
	
	public static boolean Auth(Parameters params) throws ClassNotFoundException, SQLException {
		return db_Helper.selectOK(Tables.Session, params.PS(session_key));
	}
	
	public static boolean InsertUser(Parameters params) throws SQLException, ClassNotFoundException {
		return db_Helper.insertOK(My_Table,params.PS(prenom,nom,password,login)); 
	}
	
	public static boolean InsertSession(Parameters params) throws SQLException, ClassNotFoundException {
		params.AddParam(session_id_user, db_User_Helper.getIdWithLogin(params));
		
		if (db_Helper.selectOK(Tables.Session, params.PS(session_id_user))) {
			db_Helper.deleteOK(Tables.Session, params.PS(session_id_user));
		}
		return db_Helper.insertOK(Tables.Session,params.PS(session_id_user,session_key)); 
	}
	
	public static boolean DeleteSession(Parameters params) throws SQLException, ClassNotFoundException {

		return db_Helper.deleteOK(Tables.Session,params.PS(session_key)); 
	}
	
	public static boolean CheckPassword(Parameters params) throws NumberFormatException, SQLException, ClassNotFoundException {

		return db_Helper.selectOK(My_Table,params.PS(login,password));
	}
	
	public static int getIdWithLogin(Parameters params) throws NumberFormatException, SQLException, ClassNotFoundException {
		
		return db_Helper.selectAndWhereID(My_Table, params.PS(login));
	}
	
	public static Integer getIdWithKey(Parameters params) throws ClassNotFoundException, SQLException {
		
		return db_Helper.selectAndWhere("id_user",Tables.Session,params.PS(session_key)).getValueInt("id_user");
	}
}

