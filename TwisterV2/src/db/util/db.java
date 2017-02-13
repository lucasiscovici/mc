package db.util;

import java.sql.SQLException;

import db.db_Helper;
import util.LucasException;
import util.Parameters;

public abstract class db implements db_crud {
	public static String _My_Table = "";
	
	public db() {
		_My_Table = GiveMyTable();
	}
	
	
	public Parameters getXWithX(String select,Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectAndWhere(select,_My_Table,params);
	}
	public Parameters getXWithX(String select) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectAndWhere(select,_My_Table,null);
		//return null;
	}
	
	public boolean CheckIfExistWithId(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectOK(_My_Table,params.PS("id"));
	}
	public boolean CheckIfExistWith(String select,Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//io.print(_My_Table);
		return db_Helper.selectOK(_My_Table,params.PS(select));
		//return true;
	}
	
	public boolean CheckIfExistWith(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//io.print(_My_Table);
		return db_Helper.selectOK(_My_Table,params);
		//return true;
	}
	public boolean RemoveWithId(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.deleteOK(_My_Table, params.PS("id"));
	}
	public boolean RemoveWith(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.deleteOK(_My_Table, params);
	}
	public Parameters SelectWithId(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectAndWhere(_My_Table, params.PS("id"));
	}
	public Parameters SelectWith(Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectAndWhere(_My_Table, params);
	}
	public Parameters SelectWith(Parameters params,String...selects) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectAndWhere(_My_Table, params,selects);
	}
	public Parameters SelectWith(String select,Parameters params) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return db_Helper.selectAndWhere(_My_Table, params.PS(select));
	}
	public boolean UpdateWithId(Parameters params) throws ClassNotFoundException, SQLException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.updateOK(_My_Table,params.PSN("id"), params.PS("id"));
	}
	public boolean UpdateWith(Parameters sets,Parameters where) throws ClassNotFoundException, SQLException, LucasException {
		// TODO Auto-generated method stub
		return db_Helper.updateOK(_My_Table, sets ,where);
	}
	public boolean InsertOK(Parameters params) throws ClassNotFoundException, SQLException {
		return db_Helper.insertOK(_My_Table, params);
	}
	public boolean SelectOK(Parameters params) throws ClassNotFoundException, SQLException {
		return db_Helper.selectOK(_My_Table, params);
	}
	public boolean DeleteOK(Parameters params) throws ClassNotFoundException, SQLException {
		return db_Helper.deleteOK(_My_Table, params);
	}
	
}
