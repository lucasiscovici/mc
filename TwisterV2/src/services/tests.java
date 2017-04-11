package services;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.jws.soap.SOAPBinding.Use;

import org.json.JSONException;

import db.db_Session_Helper;
import services.comment.SAddComment;
import services.comment.SListComments;
import services.friend.SAddFriend;
import services.friend.SListFriends;
import services.like.SAddLike;
import services.post.SAddPost;
import services.post.SListPosts;
import services.post.SRemovePost;
import services.session.SLogin;
import services.user.SCreateUser;
import services.user.SListUsers;
import util.Dico;
import util.LucasException;
import util.Usefull;
import util.io;
class test {
	public static void main(String[] args) throws ClassNotFoundException, IOException, JSONException, LucasException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// "key","0d352077-ed92-4738-ba80-f389eed2d3ce"
		String key = "c503cef1-173b-4c58-ab61-37741e7a572b";
	Usefull.isLocale = true;
		//new SAddComment(Dico.toP("text","dd","id_post","58e984a9e4b0e392744f9018","key",key)).print();
		//io.print(new db_Session_Helper().SeectWith(null));
		//new SCreateUser(Dico.toP("login","lulupefffrets","password","password","email","luluzfvxc")).print(); //-> code 1, User already exist -> OK
		//new SCreateUser(Dico.toP("prenom","","nom","isco","login","luluperet2","password","password")).print();
	//new SLogin(Dico.toP("login","luluperet","password","popo")).print();
		//new SLogout(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d")).print();
		//SListUsers slu = 	new SListUsers(Dico.toP("key",key,"type","ALL"));
		//slu.print();
		//io.print(slu.to_bean());
		//new SAddLike(Dico.toP("key",key,"id_post","58e98dc4e4b05e9a6d229b7f")).print();
		//new SAddFriend(Dico.toP("key",key,"id_friend","7")).print();
	//new SListFriends(Dico.toP("key",key)).print();
		//new SRemoveFriend(Dico.toP("key","14945d4d-d77f-4c18-94f6-69839b297a77","id_friend","1")).print();
	//new SListPosts(Dico.toP("key",key,"type","ALL")).print();
//slp.print(); 
		//io.print(slp.to_bean());
		//new SAddPost(Dico.toP("key",key,"text","test")).print(); //58a4c12b77c879b41828823e
	new SRemovePost(Dico.toP("key",key,"id","")).print();
		//new SRemoveUser(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d")).print();;
	}
}