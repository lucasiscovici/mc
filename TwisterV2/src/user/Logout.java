package user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_User_Helper;
import util.Error;
import util.IOLUCAS;
import util.IParameters;
import util.Parameters;
import util.io;

public class Logout extends HttpServlet implements IOLUCAS, IParameters {
	static public HttpServletResponse response = null;
	static public String[] getEntry = {"key"};
	static Parameters params = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void koko() throws JSONException, IOException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		if(params.CheckIfErrParams(getEntry)){
			io.print_json_or_print(response, Error.ErrArgs.to_JSON());
		}else{
			boolean ok_delete_session = db_User_Helper.DeleteSession(params);
			if (!ok_delete_session) {
				io.print_json_or_print(response, Error.SqlError.detail("Key Introuvable"));
			}else{
				io.print_text(response, "OK");
			}
		}
	}

protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
	response = resp;
	params = Parameters.req(req);
	try {
		koko();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
