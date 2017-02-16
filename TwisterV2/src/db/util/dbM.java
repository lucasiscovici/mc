package db.util;

import java.net.UnknownHostException;
import java.sql.SQLException;

import db.db_Helper;
import util.LucasException;
import util.Parameters;

/**
 * Classe dbM pour Mongo
 */

public abstract class dbM implements db_crud {
	public static String _My_Table = "";
	
	public dbM() {
		_My_Table = GiveMyTable();
	}
	
	/**
	 * 
	 * @param select Une chaine de caractère
	 * @param params Un paramètre
	 * @return db_Helper.selectMongo(select,_My_Table,params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public Parameters getXWithX(String select,Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(select,_My_Table,params.copy().change("id", "_id"));
	}
	
	/**
	 * 
	 * @param select Une chaine de caractère
	 * @return db_Helper.selectMongo(select,_My_Table,null)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public Parameters getXWithX(String select) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(select,_My_Table,null);
		//return null;
	}
	
	/**
	 * 
	 * @param select Une chaine de caractère
	 * @param params Un paramètre
	 * @return db_Helper.selectMongoOK(_My_Table,params.PS("id"))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public boolean CheckIfExistWithId(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		//io.print(_My_Table);
		return db_Helper.selectMongoOK(_My_Table,params.PS("id").change("id", "_id"));
		//return true;
	}
	
	/**
	 * 
	 * @param select Une chaine de caractère
	 * @param params Un paramètre
	 * @return db_Helper.selectMongoOK(_My_Table,params.PS(select))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public boolean CheckIfExistWith(String select,Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		//io.print(_My_Table);
		return db_Helper.selectMongoOK(_My_Table,params.PS(select).change("id", "_id"));
		//return true;
	}
	
	/**
	 * CHECK IF ROW IN DB EXIST WITH PARTICULAR PARAMS (WHERE)
	 * @param params Un paramètre
	 * @return db_Helper.selectMongoOK(_My_Table,params.copy().change("id", "_id"))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException
	 */
	public boolean CheckIfExistWith(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		//io.print(_My_Table);
		return db_Helper.selectMongoOK(_My_Table,params.copy().change("id", "_id"));
		//return true;
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.deleteMongoOK(_My_Table, params.PS("id"))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public boolean RemoveMongoWithId(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.deleteMongoOK(_My_Table, params.PS("id").change("id", "_id"));
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.deleteMongoOK(_My_Table, params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public boolean RemoveMongoWith(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.deleteMongoOK(_My_Table, params.copy().change("id", "_id"));
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.selectMongo(_My_Table, params.PS("id"))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public Parameters SelectMongoWithId(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(_My_Table, params.PS("id").change("id", "_id"));
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.selectMongo(_My_Table, params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public Parameters SelectMongoWith(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(_My_Table, params.copy().change("id", "_id"));

	}
	public Parameters SelectMongoWith() throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(_My_Table,null);

	}
	/**
	 * 
	 * @param select Une chaine de caractère
	 * @param params Un paramètre
	 * @return db_Helper.selectMongo(_My_Table, params.PS(select))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public Parameters SelectMongoWith(String select,Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(_My_Table, params.PS(select).change("id", "_id"));

	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @param selects Un ensemble de chaines de caractère
	 * @return db_Helper.selectMongo(_My_Table, params,selects)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public Parameters SelectMongoWith(Parameters params,String...selects) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(_My_Table, params.copy().change("id", "_id"),selects);
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.updateMongoOK(_My_Table,params.PSN("id"),params.PS("id"))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 * @throws UnknownHostException
	 */
	
	public boolean UpdateMongoWithId(Parameters params) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return db_Helper.updateMongoOK(_My_Table,params.PSN("id"),params.PS("id").change("id", "_id"));
	}
	
	/**
	 * 
	 * @param sets Un paramètre
	 * @param where Un paramètre
	 * @return db_Helper.updateMongoOK(_My_Table, sets,where)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 * @throws UnknownHostException
	 */
	
	public boolean UpdateMongoWith(Parameters sets,Parameters where) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return db_Helper.updateMongoOK(_My_Table, sets,where.copy().change("id", "_id"));
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.insertMongoOK(_My_Table, params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public boolean InsertMongoOK(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		return db_Helper.insertMongoOK(_My_Table, params.change("id", "_id"));
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.selectMongoOK(_My_Table, params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public boolean SelectMongoOK(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		return db_Helper.selectMongoOK(_My_Table, params.copy().change("id", "_id"));
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.deleteMongoOK(_My_Table, params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public boolean DelectMongoOK(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		return db_Helper.deleteMongoOK(_My_Table, params.copy().change("id", "_id"));
	}
}
