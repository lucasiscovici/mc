package services;

import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;

import db.db_Helper;
import util.Dico;
import util.Error;
import util.LucasException;//import services.user.SLogin;
import util.io;
import services.friend.SAddFriend;
import services.friend.SListFriends;
import services.post.SAddComment;
import services.post.SSearch;
import services.post.SSearchBis;
import services.user.SLogin;

class test {
	public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, IOException, JSONException, LucasException, SQLException {
	//new SLogin(Dico.toP("login", "lolo","password","d"));
	//new SAddComment(Dico.toP("key","7ab65f30-0b4c-4cc5-bb53-84e1466fa353","text","trfsfsfalalo..."));
		//io.print(db_Helper.deleteMongo("Post", Dico.toP("id_user","3")));
		//new SAddFriend(Dico.toP("key","7ab65f30-0b4c-4cc5-bb53-84e1466fa353","id_friend","1"));
		//new SAddFriend(Dico.toP("key","7ab65f30-0b4c-4cc5-bb53-84e1466fa353","id_friend","2"));

		//new SSearch(Dico.toP("key","4222126b-0a4a-4c77-9e38-86bc9a5f0cfa","friends","","query",""));
	//new SListFriends(Dico.toP("key","7ab65f30-0b4c-4cc5-bb53-84e1466fa353"));
		//new SSearchBis(Dico.toP("key","7ab65f30-0b4c-4cc5-bb53-84e1466fa353","ok_friends","false","query",""));
	}
}