package db;

import java.net.UnknownHostException;
import java.sql.SQLException;

import db.util.dbM;
import util.LucasException;
import util.Parameters;
import util.Usefull;
//import util.io;

/**
 * Classe db_Post_helper pour les post
 */

public class db_Post_Helper extends dbM {
	public String My_Table = Tables.Post;

	public static String date = "date";
	public static String text = "text";
	public static String id_user = "id_user";
	
	public static String id_friend = "id_friend";
	public static String friends = "friends";
	
	/**
	 * 
	 * @return new db_Post_Helper()
	 */

	public static db_Post_Helper c() {
		return new db_Post_Helper();
	}
	
	/**
	 * Constructeur db_Post_Helper
	 */

	public db_Post_Helper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return SelectMongoWith(id_user, p2)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 */

	public Parameters listPostFromKey(Parameters params)
			throws ClassNotFoundException, SQLException, UnknownHostException {
		Parameters p2 = params.copy().AddParam(id_user, db_Session_Helper.c().getIdWithKey(params));
		return SelectMongoWith(id_user, p2);
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.selectMongoIn(My_Table, id_user, p2.getValues(id_friend))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException
	 */

	public Parameters listPostFromFriends(Parameters params)
			throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		Parameters p2 = db_Friend_Helper.c().listFriendsFromKey(params);
		return db_Helper.selectMongoIn(My_Table, id_user, p2.getValues(id_friend));
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return db_Helper.selectMongoIn(My_Table, id_user, params.copy().change(friends, id_friend).getValues(id_friend))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException
	 */
	
	public Parameters listPostFromIdFriends(Parameters params)
			throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		//io.print(params);
		return db_Helper.selectMongoIn(My_Table, id_user, params.copy().change(friends, id_friend).getValues(id_friend));
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return true si l'insertion a été faites, false sinon
	 */

	@Override
	public boolean Insert(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {

		params.AddParam(date, Usefull.currentDate());
		params.AddParam(id_user, db_Session_Helper.c().getIdWithKey(params));

		Parameters p2 = params.PS(text, id_user, date);

		if (InsertMongoOK(p2)) {
			params.AddParam(p2, "id"); // pique le "id" de p2 et le met dans params
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param params
	 * @return true si l'insertion a été faites, false sinon
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 * @throws UnknownHostException
	 */
	
	public boolean Insert2(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {

		params.AddParam(date, Usefull.currentDate());

		Parameters p2 = params.PS(text, id_user, date);

		if (InsertMongoOK(p2)) {
			params.AddParam(p2, "id"); // pique le "id" de p2 et le met dans params
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @return this.RemoveMongoWithId(params)
	 */
	
	@Override
	public boolean Remove(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.RemoveMongoWithId(params);
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return this.RemoveMongoWith(params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 * @throws UnknownHostException
	 */
	
	public boolean Remove2(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.RemoveMongoWith(params);
	}
	
	/**
	 * 
	 * @return this.UpdateMongoWithId(params)
	 */

	@Override
	public boolean Update(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.UpdateMongoWithId(params);
	}
	
	/**
	 * 
	 * @return this.SelectMongoWithId(params)
	 */

	@Override
	public Parameters Select(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.SelectMongoWithId(params);
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return this.SelectMongoWith(params)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws LucasException
	 * @throws UnknownHostException
	 */
	
	public Parameters Select2(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.SelectMongoWith(params);
	}
	
	/**
	 * 
	 * @return Tables.Post
	 */

	@Override
	public String GiveMyTable() {
		// TODO Auto-generated method stub
		return Tables.Post;
	}
}
