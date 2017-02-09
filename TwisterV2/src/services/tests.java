package services;

import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;

import util.Dico;
import util.Error;
import util.LucasException;//import services.user.SLogin;
import util.io;
import services.friend.SAddFriend;
import services.friend.SRemoveFriend;

class test {
	public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, IOException, JSONException, LucasException {
		//new SLogin(Dico.toP("login", "luluperet","password","popo"));
		try {
			new SRemoveFriend(Dico.toP("key","bc58702d-71b1-4f88-902e-6f85b7e8fa09","id_friend",1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			io.print(Error.SqlError.setDescription(e.getMessage()));
		}
	}
}