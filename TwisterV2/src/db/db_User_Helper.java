package db;

import java.sql.SQLException;
import java.util.List;

import util.Dico;
import util.Parameters;
import util.StringArray;
import util.io;

public class db_User_Helper {
	public static String My_Table = "User";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static boolean CheckIfExist(Parameters p) throws NumberFormatException, SQLException, ClassNotFoundException {
		Parameters p2 = p.PS("login");
		return db_Helper.selectOK(My_Table,p2); 
	}
	public static boolean Auth(Parameters params) throws ClassNotFoundException, SQLException {
		io.print(params);
		return db_Helper.selectOK("Session", params.PS("key"));
	}
	public static boolean InsertUser(Parameters params) throws SQLException, ClassNotFoundException {
		Parameters p = params.PS("prenom","nom","password","login");
		return db_Helper.insertOK(My_Table,p); 
	}
	public static boolean InsertSession(Parameters params) throws SQLException, ClassNotFoundException {
		boolean exist_session = db_Helper.selectOK("Session", params.PS("id_user"));
		if (exist_session) {
			db_Helper.deleteOK("Session", params.PS("id_user"));
		}
		Parameters p = params.PS("id_user","key");
		return db_Helper.insertOK("Session",p); 
	}
	public static boolean DeleteSession(Parameters params) throws SQLException, ClassNotFoundException {
		Parameters p = params.PS("key");
		return db_Helper.deleteOK("Session",p); 
	}
	public static boolean CheckPassword(Parameters p) throws NumberFormatException, SQLException, ClassNotFoundException {
		Parameters p2 = p.PS("login","password");
		//Parameters p2 = Parameters.fromDicos(Dico.ps(params, StringArray.X("login","password")));
		return db_Helper.selectOK(My_Table,p2);
	}
	public static int getIdWithLogin(Parameters p) throws NumberFormatException, SQLException, ClassNotFoundException {
		return db_Helper.selectAndWhere("id",My_Table, p.PS("login")).getValueInt("id");
	}
	public static int getIdWithKey(Parameters p) throws ClassNotFoundException, SQLException {
		return db_Helper.selectAndWhere("id","Session",p.PS("key")).getValueInt("id");
	}
}

