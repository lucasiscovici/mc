package db;

import java.net.UnknownHostException;
import java.sql.SQLException;

import db.util.db;
import util.Dico;
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
	 * @param params Parameters
	 * @return Parameters
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 */

	public Parameters listFriendsFromKey(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException {
		
		Integer fromId = db_Session_Helper.c().getIdWithKey(params);
		return SelectWith(Dico.toP(from,fromId));
	}

	/**
	 * 
	 * @param params Parameters
	 * @return Parameters id_friend[]
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 */
	public Parameters listFriendsFromId(Parameters params) throws ClassNotFoundException, SQLException, LucasException {
		return SelectWith(params.PS("id").change("id", from));
	}
	
	/**
	 * @param params
	 * @return {@link db_Friend_Helper#listFriendsFromId}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 */
	public Parameters listFriendsFromIdUser(Parameters params) throws ClassNotFoundException, SQLException, LucasException {
		return listFriendsFromId(params.PS("id_user").change("id_user", "id"));
	}
	/**
	 * 
	 * @return true si l'insertion se fait correctement, false sinon
	 */
	@Override
	public boolean Insert(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		
		Integer fromId = db_Session_Helper.c().getIdWithKey(params);
		Parameters p2 = params.copy().AddParam(from, fromId).change(id_friend, to).PS(from, to);

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

	public boolean RemoveWithKeyandID(Parameters params) throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		// TODO Auto-generated method stub
		int idFromKey = db_Session_Helper.c().getIdWithKey(params);
		return RemoveWith(params.PS("id_friend").change("id_friend","to").AddParam("from",idFromKey));
	}
}
