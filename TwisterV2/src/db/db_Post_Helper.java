package db;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import db.util.dbM;
import util.Dico;
import util.LucasException;
import util.Parameters;
import util.Usefull;
//import util.io;
import util.io;

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
	
	/* (non-Javadoc)
	 * @see db.util.dbM#CheckIfExistWith(util.Parameters)
	 */
	@Override
	public boolean CheckIfExistWith(Parameters params)
			throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		return super.CheckIfExistWith(params.PS("id_post").change("id_post", "id"));
	}
	
	
	

	public boolean removeMine(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		int MyId = db_Session_Helper.c().getIdWithKey(params);
		return RemoveMongoWith(Dico.toP(id_user,MyId));
	}
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return SelectMongoWith(id_user, p2)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */

	public Parameters listPostFromKey(Parameters params)
			throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		int idFromKey = db_Session_Helper.c().getIdWithKey(params);
		Parameters p2 = params.copy().AddParam(id_user,idFromKey );
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
		
		return db_Helper.selectMongoIn(My_Table, id_user, p2.getDicos("to").change("to", "id_friend").getValues(id_friend),params.getDico("p2").valuesdP());
	}
	public Parameters listPostFromFriendsMoreLogin(Parameters params)
			throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		Parameters p2 = db_Friend_Helper.c().listFriendsFromKey(params);
		
		Parameters p3 = db_Helper.selectMongoIn(My_Table, id_user, p2.getDicos("to").change("to", "id_friend").getValues(id_friend),params.getDico("p2").valuesdP());
		for (Dico d : p3.parameters) {
			d.addD("login", db_User_Helper.c().getXWithX("login", Dico.toP("id",d.toPa().getValue("id_user"))).getValue("login"));
		}
	return p3;
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
		if (params.getValue(friends).equals("-1")) {
			List<String> friends_id = db_Friend_Helper.c().listFriendsFromIdUser(params).getDicos("to").change("to", id_friend).getValues(id_friend);
			return db_Helper.selectMongoIn(My_Table, id_user, friends_id);
 
		}else if (params.getValue(friends).equals("-2")){
			List<String> friends_id = db_Friend_Helper.c().listFriendsFromIdUser(params).getDicos("to").change("to", id_friend).AddParam(id_friend,db_Session_Helper.c().getIdWithKey(params)).getValues(id_friend);
			return db_Helper.selectMongoIn(My_Table, id_user, friends_id);
		}else{
			return db_Helper.selectMongoIn(My_Table, id_user, params.copy().change(friends, id_friend).getValues(id_friend));
	
		}
	}	
	
	/**
	 * 
	 * @param params Un paramètre
	 * @return true si l'insertion a été faites, false sinon
	 */


	@Override
	public boolean Insert(Parameters params)
			throws ClassNotFoundException, SQLException, LucasException, UnknownHostException {
		Parameters p2 = params.copy();
		p2.AddParam(date, Usefull.currentDate());
		p2.AddParam(id_user, db_Session_Helper.c().getIdWithKey(params));

		p2 = p2.PS(text, id_user, date);

		if (InsertMongoOK(p2)) {
			//io.print(p2);
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
		
		Parameters p2 = params.copy();
		p2.AddParam(date, Usefull.currentDate());

		p2 = p2.PS(text, id_user, date);

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

	public Parameters total(Parameters params) throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		// TODO Auto-generated method stub
		Parameters p2 =  new Parameters();
		if (params.getDicosOK("date_min")) {
//			  SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy",Locale.ENGLISH);
//		        Date date = null;
//				try {
//					date = parser.parse(params.getValue("date"));
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				p2.AddParam("date_$gte", params.getDico("date_min").setKey("date"));
		}
		if (params.getDicosOK("limit")) {
			p2.AddParam("limit",params.getValue("limit"));
		}else{
			p2.AddParam("limit",10);
		}
		params.AddParam("p2", p2);
		Parameters messages = listPostFromFriends(params);
		for (Dico d : messages.parameters) {
				Parameters dicop = d.toPa();
				String id_user = dicop.getValue("id_user");
				String login = db_User_Helper.c().getXWithX("login",Dico.toP("id",id_user)).getValue("login");
				int nb_likes = db_Like_Helper.c().ListLikesFromIdPost(dicop.getDico("id").toPa().change("id", "id_post")).parameters.size();
				int nb_coms = db_Comment_Helper.c().ListPostsFromIdPost(dicop.getDico("id").toPa().change("id", "id_post")).parameters.size();
				d.addD("nb_likes", nb_likes);
				d.addD("nb_coms", nb_coms);
				d.addD("login", login);
		}
		
		return messages;
	}

	public Parameters listPostFromKeyMoreLogin(Parameters params) throws ClassNotFoundException, SQLException, UnknownHostException, LucasException {
		// TODO Auto-generated method stub
		int idFromKey = db_Session_Helper.c().getIdWithKey(params);
		Parameters p2 = params.copy().AddParam(id_user,idFromKey );
		Parameters p3 =  SelectMongoWith(id_user, p2);
		for (Dico d : p3.parameters) {
			d.addD("login", db_User_Helper.c().getXWithX("login", Dico.toP("id",d.toPa().getValue("id_user"))).getValue("login"));
		}
		return p3;
	}

}
