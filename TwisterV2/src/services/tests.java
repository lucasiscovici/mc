package services;

import java.io.IOException;
import java.sql.SQLException;

import services.friend.SListFriends;
import services.post.SAddLike;
import services.post.SListLikes;
//
//import javax.servlet.http.HttpServletRequest;
//
//import services.friend.SAddFriend;
//import services.friend.SListFriends;
//import services.friend.SRemoveFriend;
//import services.post.SAddComment;
import services.post.SSearch;
//import services.post.SSearchBis;

import services.user.SLogin;

import org.json.JSONException;








import db.db_Like_Helper;
import db.db_Post_Helper;
//import db.db_Post_Helper;
//import db.db_Session_Helper;
//import db.db_User_Helper;
//import services.user.SCreateUser;
//import services.user.SLogin;
//import servlet.friend.RemoveFriend;
import util.Dico;
import util.LucasException;//import services.user.SLogin;
import util.io;



class test {
	public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, IOException, JSONException, LucasException, SQLException {
		//db_User_Helper.c().getXWithX("id");
		// "key","12689cff-7533-4b04-b0ba-139b3b230f3d"
		//new db_Post_Helper().Remove2(null);
		//new SCreateUser(Dico.toP("login","l","password","l","prenom","l","nom","l")).print();
		//new db_Post_Helper().Insert2(Dico.toP("text","jojo","date",Usefull.currentDate(),"id_user","1"));
		//io.print(new db_Post_Helper().Select2(null));
		//new SLogin(Dico.toP("login", "l","password","l")).print();;
	//new SAddComment(Dico.toP("key","1e926d3b-aec9-4c64-aeea-fd3d3c869169","text","blablabla...")).print();
		//io.print(db_Helper.deleteMongo("Post", Dico.toP("id_user","3")));
		//new SAddFriend(Dico.toP("key","e4c47871-8aa5-458f-83ab-a3e6ae311a52","id_friend","1")).print();
		//new SRemoveFriend(Dico.toP("key","2a35507d-65e0-448b-b11f-7e5dd7307bc8","id_friend","4")).print();
		//io.print(db_Session_Helper.c().getIdWithKey(Dico.toP("key","30f05067-297f-4e72-b8f6-b3a47abf27ec")));
		//new SSearch(Dico.toP("key","1e926d3b-aec9-4c64-aeea-fd3d3c869169")).print();;
	//new SListFriends(Dico.toP("key","e4c47871-8aa5-458f-83ab-a3e6ae311a52")).print();;
		// new SSearchBis(Dico.toP("key","e4c47871-8aa5-458f-83ab-a3e6ae311a52","ok_friends","false")).print();
	//	new SSearchBis(Dico.toP("key","e4c47871-8aa5-458f-83ab-a3e6ae311a52","ok_friends","true")).print();
		new SAddLike(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d","id_friend",1,"id_post","58a25fa777c8cebb69c1909f")).print();
		new SListFriends(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d")).print();
		new SSearch(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d")).print();
//io.print(db_Like_Helper.c().RemoveMongoWith(null));
		new SListLikes(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d","id_friend",1,"id_post","58a25fa777c8cebb69c1909f")).print();
	}
}