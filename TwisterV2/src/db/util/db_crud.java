package db.util;

import java.net.UnknownHostException;
import java.sql.SQLException;

import util.LucasException;
import util.Parameters;

public interface db_crud {
	public boolean Insert(Parameters params) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException;
	public boolean Remove(Parameters params) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException;
	public boolean Update(Parameters params) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException;
	public Parameters Select(Parameters params) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException;

//	public Parameters getXWithX(Parameters params) throws ClassNotFoundException, SQLException;
	public String GiveMyTable();
}
