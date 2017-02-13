package services;

import java.io.IOException;
import java.sql.SQLException;

import services.friend.SAddFriend;
import services.friend.SListFriends;
import services.friend.SRemoveFriend;
import services.post.SAddComment;
import services.post.SSearchBis;

import org.json.JSONException;

import services.user.SLogin;
import servlet.friend.RemoveFriend;
import util.Dico;
import util.LucasException;//import services.user.SLogin;


class test {
	public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, IOException, JSONException, LucasException, SQLException {
	//new SLogin(Dico.toP("login", "lolo","password","sd")).print();;
	//new SAddComment(Dico.toP("key","e4c47871-8aa5-458f-83ab-a3e6ae311a52","text","trdfsfsfalalo...")).print();
		//io.print(db_Helper.deleteMongo("Post", Dico.toP("id_user","3")));
		//new SAddFriend(Dico.toP("key","e4c47871-8aa5-458f-83ab-a3e6ae311a52","id_friend","1")).print();
		//new SRemoveFriend(Dico.toP("key","2a35507d-65e0-448b-b11f-7e5dd7307bc8","id_friend","4")).print();
		//new SSearch(Dico.toP("key","4222126b-0a4a-4c77-9e38-86bc9a5f0cfa","friends","","query",""));
	//new SListFriends(Dico.toP("key","e4c47871-8aa5-458f-83ab-a3e6ae311a52")).print();;
		// new SSearchBis(Dico.toP("key","e4c47871-8aa5-458f-83ab-a3e6ae311a52","ok_friends","false")).print();
	//	new SSearchBis(Dico.toP("key","e4c47871-8aa5-458f-83ab-a3e6ae311a52","ok_friends","true")).print();

	}
}