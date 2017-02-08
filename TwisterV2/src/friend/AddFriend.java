package friend;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Friend_Helper;
import db.db_User_Helper;
import util.Dico;
import util.Error;
import util.IOLUCAS;
import util.IParameters;
import util.Parameters;
import util.io;

public class AddFriend extends HttpServlet implements IOLUCAS, IParameters {
	public static HttpServletResponse response = null;
	public static String[] getEntry = {"id_friend","key"};
	static Parameters params = null;

public void koko() throws JSONException, IOException, SQLException, ClassNotFoundException {
	// TODO Auto-generated method stub
if (params.CheckIfErrParams(getEntry)) {
	io.print_json_or_print(response, Error.ErrArgs.to_JSON());
}else{
	boolean ok_key = db_User_Helper.Auth(params);
	if (!ok_key) {
		io.print_json_or_print(response, Error.NAUTH.detail("key incorrect").depuis(this));
		return;
	}
	Parameters p2 = Parameters.fromDicos(Dico.kv("from", db_User_Helper.getIdWithKey(params)),Dico.kv("to", params.getValue("id_friend")));
	boolean ok_insert = db_Friend_Helper.InsertFriend(p2);
	if (!ok_insert) {
		io.print_json_or_print(response, Error.SqlError.detail("PB Insert friend check ids"));
	}else{
		io.print_text(response, "OK");
	}
}
}
public static void koko2() throws JSONException, IOException, SQLException, ClassNotFoundException {
	// TODO Auto-generated method stub
if (params.CheckIfErrParams(getEntry)) {
	io.print_json_or_print(response, Error.ErrArgs.to_JSON());
}else{

	boolean ok_key = db_User_Helper.Auth(params);
	
	if (!ok_key) {
		io.print_json_or_print(response, Error.NAUTH.detail("key incorrect").depuis("friend.AddFriend"));
		return;
	}
	Parameters p2 = Parameters.fromDicos(Dico.kv("from", db_User_Helper.getIdWithKey(params)),Dico.kv("to", params.getValue("id_friend")));
	boolean ok_insert = db_Friend_Helper.InsertFriend(params);
	if (!ok_insert) {
		io.print_json_or_print(response, Error.SqlError.detail("PB Insert friend check ids"));
	}else{
		io.print_text(response, "OK");
	}
}
}
	public static void main(String[] args) throws JSONException, IOException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		params = new Parameters();
		params.AddParam(Dico.kv("key", "95ce6855-b2ac-4c36-9d66-c0f5da17bb64"));
		params.AddParam(Dico.kv("id_friend", "2"));
		
		try{
		koko2();
		}
		catch (SQLException e) {
			io.print("dz");
			io.print_json_or_print(response, Error.SqlError.detail(e.getMessage()).depuis("friend.AddFriend main"));
		}catch (Exception e) {
			io.print("dz");
		}

	}

//	io.print_json_or_print(response, Error.ErrArgs.to_JSON());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.response  = resp;
		this.params = Parameters.req(req);
		try {
			koko();
		} catch (JSONException e) {
			io.print_text(resp,Error.JsonError);
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				io.print_json_or_print(response, Error.SqlError.detailT(this, e.getMessage()));
			} catch (JSONException e1) {
				io.print_text(response, Error.SqlError.detailT(this, e.getMessage()));
				e1.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
