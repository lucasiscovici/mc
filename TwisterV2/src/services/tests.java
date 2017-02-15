package services;

import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;



import util.LucasException;

class test {
	public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, IOException, JSONException, LucasException, SQLException {
		// "key","12689cff-7533-4b04-b0ba-139b3b230f3d"
		//"14945d4d-d77f-4c18-94f6-69839b297a77"
		//new SCreateUser(Dico.toP("prenom","lucas","nom","isco","login","luluperet","password","password")).print(); -> code 1, User already exist -> OK
		//new SCreateUser(Dico.toP("prenom","","nom","isco","login","luluperet2","password","password")).print();
		//new SLogin(Dico.toP("login","luluperet2","password","password")).print();
		//new SLogout(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d")).print();
		//new SListUsers(Dico.toP("key","14945d4d-d77f-4c18-94f6-69839b297a77","id","1")).print();
	//new SAddFriend(Dico.toP("key","14945d4d-d77f-4c18-94f6-69839b297a77","id_friend","4")).print();
		//new SListFriends(Dico.toP("key","14945d4d-d77f-4c18-94f6-69839b297a77","id","4")).print();
		//new SRemoveFriend(Dico.toP("key","14945d4d-d77f-4c18-94f6-69839b297a77","id_friend","1")).print();
		// new SListPosts(Dico.toP("key","14945d4d-d77f-4c18-94f6-69839b297a77","type","ALL")).print(); 
		//new SAddPost(Dico.toP("key","14945d4d-d77f-4c18-94f6-69839b297a77","text","ddd")).print(); //58a4c12b77c879b41828823e
		// new SRemovePost(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d","id","58a4c12b77c879b41828823e")).print();
		//new SRemoveUser(Dico.toP("key","12689cff-7533-4b04-b0ba-139b3b230f3d")).print();;
	}
}