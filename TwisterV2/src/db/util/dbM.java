package db.util;

import java.net.UnknownHostException;
import java.sql.SQLException;

import db.db_Helper;
import util.LucasException;
import util.Parameters;

public abstract class dbM implements db_crud {
	public static String _My_Table = "";
	
	public dbM() {
		_My_Table = GiveMyTable();
	}
	
	
	public Parameters getXWithX(String select,Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(select,_My_Table,params);
	}
	public Parameters getXWithX(String select) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(select,_My_Table,null);
		//return null;
	}
	
	public boolean CheckIfExistWithId(String select,Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub
		//io.print(_My_Table);
		return db_Helper.selectMongoOK(_My_Table,params.PS("id"));
		//return true;
	}
	public boolean CheckIfExistWith(String select,Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub
		//io.print(_My_Table);
		return db_Helper.selectMongoOK(_My_Table,params.PS(select));
		//return true;
	}
	public boolean CheckIfExistWith(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub
		//io.print(_My_Table);
		return db_Helper.selectMongoOK(_My_Table,params);
		//return true;
	}
	public boolean RemoveMongoWithId(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub
		return db_Helper.deleteMongoOK(_My_Table, params.PS("id"));
	}
	public boolean RemoveMongoWith(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub
		return db_Helper.deleteMongoOK(_My_Table, params);
	}
	public Parameters SelectMongoWithId(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(_My_Table, params.PS("id"));
	}
	public Parameters SelectMongoWith(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(_My_Table, params);

	}
	public Parameters SelectMongoWith(String select,Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(_My_Table, params.PS(select));

	}
	public Parameters SelectMongoWith(Parameters params,String...selects) throws ClassNotFoundException, SQLException, UnknownHostException {
		// TODO Auto-generated method stub
		return db_Helper.selectMongo(_My_Table, params,selects);
	}
	public boolean UpdateMongoWithId(Parameters params) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return db_Helper.updateMongoOK(_My_Table,params.PSN("id"),params.PS("id"));
	}
	public boolean UpdateMongoWith(Parameters sets,Parameters where) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return db_Helper.updateMongoOK(_My_Table, sets,where);
	}
	public boolean InsertMongoOK(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		return db_Helper.insertMongoOK(_My_Table, params);
	}
	public boolean SelectMongoOK(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		return db_Helper.selectMongoOK(_My_Table, params);
	}
	public boolean DelectMongoOK(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException {
		return db_Helper.deleteMongoOK(_My_Table, params);
	}
}
