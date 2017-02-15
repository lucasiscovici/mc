package db.util;

import java.net.UnknownHostException;
import java.sql.SQLException;

import util.LucasException;
import util.Parameters;

/**
 * Interface db_crud
 */

public interface db_crud {
	/**
	 * 
	 * @param params Un paramètre
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 * @throws UnknownHostException
	 */
	
	public boolean Insert(Parameters params) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException;
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return Une insertion
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 * @throws UnknownHostException
	 */
	
	public boolean Remove(Parameters params) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException;
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return Une modification
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 * @throws UnknownHostException
	 */
	
	public boolean Update(Parameters params) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException;
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return Une sélection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 * @throws UnknownHostException
	 */
	
	public Parameters Select(Parameters params) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException;

//	public Parameters getXWithX(Parameters params) throws ClassNotFoundException, SQLException;
	
	/**
	 * 
	 * @return Ma table
	 */
	
	public String GiveMyTable();
}
