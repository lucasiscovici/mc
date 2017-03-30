package db;

import java.net.UnknownHostException;
import java.sql.SQLException;

import db.util.dbM;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.RespS;
import util.Usefull;
//import util.io;

public class db_Like_Helper extends dbM {
	
	public static String My_Table = Tables.Like;

	public static String date = "date";
	public static String id_post = "id_post";
	public static String id_user = "id_user";

	public db_Like_Helper() {
		super();
	}

	public static db_Like_Helper c() {
		return new db_Like_Helper();
	}

	public Parameters ListLikesFromIdPost(Parameters params) throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		return SelectMongoWith("id_post", params);
	}
	
	@Override
	public boolean Insert(Parameters params) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		Integer idFromKey = db_Session_Helper.c().getIdWithKey(params);
		Parameters p2 = params.PS("id_post").AddParam("id_user",idFromKey );
		
		if (CheckIfExistWith(p2)) {
			return false;
		}
		p2 = params.copy();
		p2.AddParam(date, Usefull.currentDate());
		p2.AddParam(id_user, idFromKey);
		
		p2 = p2.PS(id_user, id_post, date);
		
		if (InsertMongoOK(p2)) {
			params.AddParam(p2, "id");
			return true;
		} else {
			return false;
		}
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
		return Tables.Like;
	}
	
	public boolean RemoveMongoWithKey(Parameters params) throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		// TODO Auto-generated method stub
		int IdWK = db_Session_Helper.c().getIdWithKey(params);
		return RemoveMongoWithId(Dico.toP("id",IdWK));
	}

	public Parameters SelectMangoIdPost(Parameters params) throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		// TODO Auto-generated method stub
		if (!db_Post_Helper.c().CheckIfExistWithId(params.copy().change("id_post", "id"))) {
			throw RespS.cl(params.myService, Error.ErrArgs.detail("mauvais id_post"));
		}
		return SelectMongoWith(params.PS("id_post"));
	}

	//KEY -> ID , Id-POST ->>ID_LIKE
	public boolean RemoveWithIdPost(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		int IdWK = db_Session_Helper.c().getIdWithKey(params);
		return RemoveMongoWith(Dico.toP("id_user",IdWK,params.PS("id_post")));
	}
}
