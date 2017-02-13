package db;

import java.net.UnknownHostException;
import java.sql.SQLException;

import db.util.db;
import util.LucasException;
import util.Parameters;

public class db_User_Helper extends db {

	public static String My_Table = Tables.User;
	public static String login = "login";
	public static String prenom = "prenom";
	public static String nom = "nom";
	public static String password = "password";

	public static db_User_Helper c() {
		return new db_User_Helper();
	}

	public db_User_Helper() {
		super();
	}

	public boolean CheckPassword(Parameters params) throws NumberFormatException, SQLException, ClassNotFoundException {

		return SelectOK(params.PS(login, password));
	}
	
	public boolean CheckIfExist(Parameters params) throws NumberFormatException, SQLException, ClassNotFoundException {
		return db_Helper.selectOK(My_Table,params.PS(login)); 
	}
	
	public int getIdWithLogin(Parameters params) throws NumberFormatException, SQLException, ClassNotFoundException {

		return db_Helper.selectAndWhereID(My_Table, params.PS(login));
	}

	@Override
	public boolean Insert(Parameters params) throws ClassNotFoundException, SQLException {
		Parameters p2 = params.PS(prenom, nom, password, login);
		if (InsertOK(p2)) {
			params.AddParam(p2, "id");
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean Remove(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return this.RemoveWithId(params);
	}

	@Override
	public boolean Update(Parameters params) throws ClassNotFoundException, SQLException, LucasException {
		// TODO Auto-generated method stub
		return this.UpdateWithId(params);
	}

	@Override
	public String GiveMyTable() {
		// TODO Auto-generated method stub
		return this.My_Table;
	}

	@Override
	public Parameters Select(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.SelectWithId(params);
	}

}
