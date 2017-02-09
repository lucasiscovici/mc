package services;

import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;

import db.db_Helper;
import util.Dico;
import util.Error;
import util.LucasException;//import services.user.SLogin;
import util.io;
//import services.friend.SAddFriend;
//import services.friend.SRemoveFriend;
import services.friend.SSearch;
import services.post.SAddComment;
import services.user.SLogin;

class test {
	public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, IOException, JSONException, LucasException {
		//new SLogin(Dico.toP("login", "luluperet","password","popo"));
		//new SAddComment(Dico.toP("key","4222126b-0a4a-4c77-9e38-86bc9a5f0cfa","text","tralala..."));
		io.print(db_Helper.selectMongo("Post", null));
		//new SSearch(Dico.toP("key","4222126b-0a4a-4c77-9e38-86bc9a5f0cfa","text","tralala...","friends","","query",""));
	}
}