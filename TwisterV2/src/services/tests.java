package services;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.jws.soap.SOAPBinding.Use;

import org.json.JSONException;

import db.db_Session_Helper;
import services.comment.SAddComment;
import services.friend.SAddFriend;
import services.friend.SListFriends;
import services.post.SAddPost;
import services.post.SListPosts;
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
		String key = "a4e47f10-3f19-4a77-9f33-1c44d3f1f068";
	Usefull.isLocale = true;
		//new SAddComment(Dico.toP("text","dd","id_post","qfsqfsq","key","0d352077-ed92-4738-ba80-f389eed2d3ce")).print();
		//io.print(new db_Session_Helper().SeectWith(null));
		//new SCreateUser(Dico.toP("login","lulupefffrets","password","password","email","luluzfvxc")).print(); //-> code 1, User already exist -> OK
		//new SCreateUser(Dico.toP("prenom","","nom","isco","login","luluperet2","password","password")).print();
	//new SLogin(Dico.toP("login","luluperet","password","popo")).print();
		//new SLogout(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d")).print();
		//SListUsers slu = 	new SListUsers(Dico.toP("key",key,"type","ALL"));
		//slu.print();
		//io.print(slu.to_bean());
		
		//new SAddFriend(Dico.toP("key",key,"id_friend","7")).print();
	//new SListFriends(Dico.toP("key",key)).print();
		//new SRemoveFriend(Dico.toP("key","14945d4d-d77f-4c18-94f6-69839b297a77","id_friend","1")).print();
		SListPosts slp =  new SListPosts(Dico.toP("key",key,"type","TOTAL","date_min","Thu Feb 16 17:10:13 CET 2017"));
slp.print(); 
		//io.print(slp.to_bean());
		//new SAddPost(Dico.toP("key","6560627d-e674-4e97-8f0e-04761b9e58d7","text","test")).print(); //58a4c12b77c879b41828823e
		// new SRemovePost(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d","id","58a4c12b77c879b41828823e")).print();
		//new SRemoveUser(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d")).print();;
	}
}