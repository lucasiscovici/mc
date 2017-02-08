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

public class RemoveFriend extends HttpServlet implements IOLUCAS, IParameters {
	public HttpServletResponse response = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public String[] getEntry = {"id_friend","key"};
	static Parameters params = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.response  = resp;
		this.params = new Parameters(req);
		try {
			koko();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void koko() throws JSONException, IOException, ClassNotFoundException, SQLException {
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
			boolean ok_insert = db_Friend_Helper.RemoveFriend(p2);
			if (!ok_insert) {
				io.print_json_or_print(response, Error.SqlError.detail("PB delete friend check ids"));
			}else{
				io.print_text(response, "OK");
			}
		}
	}
	
	
}
