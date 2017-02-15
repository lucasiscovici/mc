package db;

import java.net.UnknownHostException;
import java.sql.SQLException;

import db.util.db;
import util.LucasException;
import util.Parameters;

/**
 * Constructeur db_User_Helper
 */

public class db_User_Helper extends db {

	
	public String My_Table = Tables.User;
	
	public static String login = "login";
	public static String prenom = "prenom";
	public static String nom = "nom";
	public static String password = "password";

	/**
	 * 
	 * @return new db_User_Helper()
	 */
	
	public static db_User_Helper c() {
		return new db_User_Helper();
	}
	
	/**
	 * Constructeur db_User_Helper()
	 */
	
	public db_User_Helper() {
		super();
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return SelectOK(params.PS(login, password))
	 * @throws NumberFormatException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public boolean CheckPassword(Parameters params) throws NumberFormatException, SQLException, ClassNotFoundException {

		return SelectOK(params.PS(login, password));
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return SelectOK(params.PS(login))
	 * @throws NumberFormatException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public boolean CheckIfExist(Parameters params) throws NumberFormatException, SQLException, ClassNotFoundException {
		return SelectOK(params.PS(login)); 
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.selectAndWhereID(My_Table, params.PS(login))
	 * @throws NumberFormatException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public int getIdWithLogin(Parameters params) throws NumberFormatException, SQLException, ClassNotFoundException {

		return db_Helper.selectAndWhereID(My_Table, params.PS(login)); //Get ID of the User White his Login 
	}
	
	/**
	 * 
	 * @return true s'il y a eu insertion, false sinon
	 */
	
	@Override
	public boolean Insert(Parameters params) throws ClassNotFoundException, SQLException {
		Parameters p2 = params.PS(prenom, nom, password, login);
		if (InsertOK(p2)) {
			params.AddParam(p2, "id");
			return true;
		}	
		return false;
	}
	
	/**
	 * @param params Un paramètre
	 * @return RemoveWithId(params)
	 */

	@Override
	public boolean Remove(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return RemoveWithId(params);
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return UpdateWithId(params)
	 */

	@Override
	public boolean Update(Parameters params) throws ClassNotFoundException, SQLException, LucasException {
		// TODO Auto-generated method stub
		return UpdateWithId(params);
	}
	
	/**
	 * 
	 * @return Tables.User
	 */

	@Override
	public String GiveMyTable() {
		// TODO Auto-generated method stub
		return Tables.User;
	}
	
	/**
	 * 
	 * @param Un paramètre
	 * @return SelectWithId(params)
	 */

	@Override
	public Parameters Select(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return SelectWithId(params);
	}

}
