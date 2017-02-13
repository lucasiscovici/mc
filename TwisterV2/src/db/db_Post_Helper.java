package db;

import java.net.UnknownHostException;
import java.sql.SQLException;

import db.util.dbM;
import util.LucasException;
import util.Parameters;
import util.Usefull;
//import util.io;

public class db_Post_Helper extends dbM {
	public static String My_Table = Tables.Post;

	public static String date = "date";
	public static String text = "text";
	public static String id_user = "id_user";
	
	public static String id_friend = "id_friend";


	public static db_Post_Helper c() {
		return new db_Post_Helper();
	}

	public db_Post_Helper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Parameters listPostFromKey(Parameters params)
			throws ClassNotFoundException, SQLException, UnknownHostException {
		Parameters p2 = params.copy().AddParam(id_user, db_Session_Helper.c().getIdWithKey(params));
		return SelectMongoWith(id_user, p2);
	}

	public Parameters listPostFromFriends(Parameters params)
			throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		Parameters p2 = db_Friend_Helper.c().listFriendsFromKey(params);
		return db_Helper.selectMongoIn(My_Table, id_user, p2.getValues(id_friend));
	}

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

	@Override
	public boolean Remove(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.RemoveMongoWithId(params);
	}

	@Override
	public boolean Update(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.UpdateMongoWithId(params);
	}

	@Override
	public Parameters Select(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.SelectMongoWithId(params);
	}

	@Override
	public String GiveMyTable() {
		// TODO Auto-generated method stub
		return My_Table;
	}
}
