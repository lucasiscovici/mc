package db;

import java.net.UnknownHostException;
import java.sql.SQLException;

import db.util.db;
import util.LucasException;
import util.Parameters;
//import util.io;

/**
 * Classe db_Friend_Helper pour les amis
 */

public class db_Friend_Helper extends db {

	/**
	 * 
	 * @return new db_Friend_Helper()
	 */

	public static db_Friend_Helper c() {
		return new db_Friend_Helper();
	}

	/**
	 * Constructeur db_Friend_Helper()
	 */

	public db_Friend_Helper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String My_Table = Tables.Friend;

	public static String from = "from";
	public static String to = "to";

	public static String id_friend = "id_friend";

	/**
	 * 
	 * @param params
	 *            Un paramètre
	 * @return SelectWith(to,
	 *         params.AddParam(from,db_Session_Helper.c().getIdWithKey(params)).PS(from)).change(to,
	 *         id_friend)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 */

	public Parameters listFriendsFromKey(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException {
		return SelectWith(to, params.AddParam(from, db_Session_Helper.c().getIdWithKey(params)).PS(from)).change(to,
				id_friend);
	}

	/**
	 * 
	 * @param params
	 *            Un paramètre
	 * @return SelectWith(to,
	 *         params.AddParam(from,params.getValue("id"))).change(to,
	 *         id_friend)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 */
	public Parameters listFriendsFromId(Parameters params) throws ClassNotFoundException, SQLException, LucasException {
		return SelectWith(to, params.AddParam(from, params.getValue("id"))).change(to, id_friend);
	}

	/**
	 * 
	 * @return true si l'insertion se fait correctement, false sinon
	 */
	@Override
	public boolean Insert(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		Parameters p2 = params.copy().AddParam(from, db_Session_Helper.c().getIdWithKey(params)).change(id_friend, to)
				.PS(from, to);

		if (!p2.getValue(from).equals(p2.getValue(to))) {

			if (InsertOK(p2)) {
				params.AddParam(p2, "id");
				return true;
			} else {
				return false;
			}

		}

		return false;
	}

	/**
	 * 
	 * @param params
	 *            Un paramètre
	 * @return this.RemoveWithId(params)
	 */

	@Override
	public boolean Remove(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		// Parameters p2 = params.copy().change(id_friend, to);
		return this.RemoveWithId(params);
	}

	/**
	 * 
	 * @param params
	 *            Un paramètre
	 * @return this.UpdateWithId(params)
	 */

	@Override
	public boolean Update(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.UpdateWithId(params);
	}

	/**
	 * 
	 * @param params
	 *            Un paramètre
	 * @return this.SelectWithId(params)
	 */

	@Override
	public Parameters Select(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.SelectWithId(params);
	}

	/**
	 * @return Tables.Friend
	 */

	@Override
	public String GiveMyTable() {
		// TODO Auto-generated method stub
		return Tables.Friend;
	}
}
