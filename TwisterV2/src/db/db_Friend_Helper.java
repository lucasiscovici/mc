package db;

import java.net.UnknownHostException;
import java.sql.SQLException;

import db.util.db;
import util.LucasException;
import util.Parameters;
//import util.io;

public class db_Friend_Helper extends db {
	
	public static db_Friend_Helper c() {
		return new db_Friend_Helper();
	}
	public db_Friend_Helper() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static String My_Table = Tables.Friend;
	
	public static String from = "from";
	public static String to = "to";
	
	public static String id_friend = "id_friend";

	public  Parameters listFriendsFromKey(Parameters params) throws ClassNotFoundException, SQLException, LucasException {
		return SelectWith(to, params.AddParam(from,db_Session_Helper.c().getIdWithKey(params)).PS(from)).change(to, id_friend);
	}

	@Override
	public boolean Insert(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		Parameters p2 = params.copy().AddParam(from, db_Session_Helper.c().getIdWithKey(params)).change(id_friend, to).PS(from,to);

		if (!p2.getValue(from).equals(p2.getValue(to))) {
			
			if (InsertOK( p2)){
				params.AddParam(p2,"id");
				return true;
			}else{
				return false;
			}

		}

		return false;
	}
	@Override
	public boolean Remove(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		Parameters p2 = params.copy().AddParam(from, db_Session_Helper.c().getIdWithKey(params)).change(id_friend, to);
		return this.RemoveWithId(p2);
	}
	@Override
	public boolean Update(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.UpdateWithId(params);
	}
	@Override
	public Parameters Select(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return this.SelectWithId(params);
	}
	@Override
	public String GiveMyTable() {
		// TODO Auto-generated method stub
		return My_Table;
	}
}
