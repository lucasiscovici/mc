package db;

import java.net.UnknownHostException;
import java.sql.SQLException;

import db.util.dbM;
import util.Dico;
import util.LucasException;
import util.Parameters;
import util.RespS;
import util.Usefull;
import util.io;

public class db_Comment_Helper extends dbM {
	
	public String MyTable = Tables.Comment;
	public String text = "text";
	public String date = "date";
	public String id_post = "id_post";
	public String id_user = "id_user";
	
	public db_Comment_Helper() {
		super();
	}
	
	public static db_Comment_Helper c() {
		return new db_Comment_Helper();
	}
	public Parameters ListPostsFromIdPost(Parameters params) throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		return SelectMongoWith("id_post", params);
	}
	public Parameters ListCommentsFromIdPost(Parameters params) throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		Parameters p = SelectMongoWith(params.PS("id_post"));
		Parameters p2 = p.copy();
		for (Dico d : p.parameters) {
			d.addD("login", db_User_Helper.c().getXWithX("login", d.valuesdP().getDico("id_user").toPa().change("id_user", "id")).getValue("login"));
		}
		return p;
	}
	
	@Override
	public boolean Insert(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		
		if (!db_Post_Helper.c().CheckIfExistWithId(params.copy().change("id_post", "id"))) {			
			return false;
		}
		Parameters p2 = params.copy().PS("text", "id_post");
		int idWKey = db_Session_Helper.c().getIdWithKey(params);
		p2.AddParam("id_user", idWKey);
		p2.AddParam("date", Usefull.currentDate());
		if (InsertMongoOK(p2)) {
			params.AddParam(p2,"id");
			return true;
		}
		return false;
		
	}

	@Override
	public boolean Remove(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return RemoveMongoWithId(params);
	}

	@Override
	public boolean Update(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return UpdateMongoWithId(params);
	}

	@Override
	public Parameters Select(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		return SelectMongoWithId(params);
	}

	@Override
	public String GiveMyTable() {
		// TODO Auto-generated method stub
		return Tables.Comment;
	}


}
