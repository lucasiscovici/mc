package services;

import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;

import db.db_Session_Helper;
import services.comment.SAddComment;
import services.friend.SListFriends;
import services.post.SAddPost;
import services.post.SListPosts;
import services.session.SLogin;
import services.user.SCreateUser;
import services.user.SListUsers;
import util.Dico;
import util.LucasException;
import util.io;

class test {
	public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, IOException, JSONException, LucasException, SQLException {
		// "key","0d352077-ed92-4738-ba80-f389eed2d3ce"
		//"6560627d-e674-4e97-8f0e-04761b9e58d7"
		String key = "0d352077-ed92-4738-ba80-f389eed2d3ce";
		//new SAddComment(Dico.toP("text","dd","id_post","qfsqfsq","key","0d352077-ed92-4738-ba80-f389eed2d3ce")).print();
		//io.print(new db_Session_Helper().SelectWith(null));
		//new SCreateUser(Dico.toP("prenom","lucas","nom","isco","login","luluperet","password","password")).print(); -> code 1, User already exist -> OK
		//new SCreateUser(Dico.toP("prenom","","nom","isco","login","luluperet2","password","password")).print();
	//	new SLogin(Dico.toP("login","luluperet2","password","password")).print();
		//new SLogout(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d")).print();
		///new SListUsers(Dico.toP("key",key,"id","dqg")).print();
		//new SAddFriend(Dico.toP("key","14945d4d-d77f-4c18-94f6-69839b297a77","id_friend","4")).print();
		//new SListFriends(Dico.toP("key",key,"id","4sfqg")).print();
		//new SRemoveFriend(Dico.toP("key","14945d4d-d77f-4c18-94f6-69839b297a77","id_friend","1")).print();
		new SListPosts(Dico.toP("key",key,"id","oigfoeq")).print(); 
		//new SAddPost(Dico.toP("key","6560627d-e674-4e97-8f0e-04761b9e58d7","text","test")).print(); //58a4c12b77c879b41828823e
		// new SRemovePost(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d","id","58a4c12b77c879b41828823e")).print();
		//new SRemoveUser(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d")).print();;
	}
}