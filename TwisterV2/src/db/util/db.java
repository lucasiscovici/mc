package db.util;

import java.sql.SQLException;

import db.db_Helper;
import util.LucasException;
import util.Parameters;

/**
 * Classe db pour MySql
 */

public abstract class db implements db_crud {
	public String _My_Table = "";
	
	/**
	 * Constructeur db()
	 */
	public db() {
		_My_Table = GiveMyTable();
	}
	
	/**
	 * 
	 * @param select Une chaine de caractère
	 * @param params Un paramètre
	 * @return db_Helper.selectAndWhere(select,_My_Table,params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public Parameters getXWithX(String select,Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectAndWhere(select,_My_Table,params);
	}
	
	/**
	 * 
	 * @param select Une chaine de caractère
	 * @return db_Helper.selectAndWhere(select,_My_Table,null)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public Parameters getXWithX(String select) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectAndWhere(select,_My_Table,null);
		//return null;
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.selectOK(_My_Table,params.PS("id"))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public boolean CheckIfExistWithId(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectOK(_My_Table,params.PS("id"));
	}
	
	/**
	 * 
	 * @param select Une chaine de caractère
	 * @param params Un paramètre
	 * @return db_Helper.selectOK(_My_Table,params.PS(select))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public boolean CheckIfExistWith(String select,Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//io.print(_My_Table);
		return db_Helper.selectOK(_My_Table,params.PS(select));
		//return true;
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.selectOK(_My_Table,params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public boolean CheckIfExistWith(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//io.print(_My_Table);
		return db_Helper.selectOK(_My_Table,params);
		//return true;
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.deleteOK(_My_Table, params.PS("id"))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public boolean RemoveWithId(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.deleteOK(_My_Table, params.PS("id"));
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.deleteOK(_My_Table, params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public boolean RemoveWith(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.deleteOK(_My_Table, params);
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.selectAndWhere("id",_My_Table,params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public Parameters SelectWithId(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectAndWhere("id",_My_Table,params);
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.selectAndWhere(_My_Table, params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public Parameters SelectWith(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectAndWhere(_My_Table, params);
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @param selects Un ensemble de chaine de caractère
	 * @return db_Helper.selectAndWhere(_My_Table, params,selects)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public Parameters SelectWith(Parameters params,String...selects) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectAndWhere(_My_Table, params,selects);
	}
	
	/**
	 * 
	 * @param select Une chaine de caractère
	 * @param params Un paramètre
	 * @return db_Helper.selectAndWhere(select,_My_Table,params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public Parameters SelectWith(String select,Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectAndWhere(select,_My_Table,params);
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.updateOK(_My_Table,params.PSN("id"), params.PS("id"))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 */
	
	public boolean UpdateWithId(Parameters params) throws ClassNotFoundException, SQLException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.updateOK(_My_Table,params.PSN("id"), params.PS("id"));
	}
	
	/**
	 * 
	 * @param sets Un paramètre
	 * @param where Un paramètre
	 * @return db_Helper.updateOK(_My_Table, sets ,where)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 */
	
	public boolean UpdateWith(Parameters sets,Parameters where) throws ClassNotFoundException, SQLException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.updateOK(_My_Table, sets ,where);
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.insertOK(_My_Table, params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public boolean InsertOK(Parameters params) throws ClassNotFoundException, SQLException {
		return db_Helper.insertOK(_My_Table, params);
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.selectOK(_My_Table, params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public boolean SelectOK(Parameters params) throws ClassNotFoundException, SQLException {
	//	io.print(_My_Table);
		return db_Helper.selectOK(_My_Table, params);
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.deleteOK(_My_Table, params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public boolean DeleteOK(Parameters params) throws ClassNotFoundException, SQLException {
		return db_Helper.deleteOK(_My_Table, params);
	}
	
}
