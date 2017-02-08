package friend;

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

public class Search extends HttpServlet implements IOLUCAS, IParameters {
	public String[] getEntry = {"key","query","friends"};
	static Parameters params = null;
	public HttpServletResponse response = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void koko() throws JSONException, IOException, ClassNotFoundException, SQLException {
		if(params.CheckIfErrParams(getEntry)){
			io.print_json_or_print(response, Error.ErrArgs.to_JSON());
		}else{
			boolean ok_key = db_User_Helper.Auth(params);
			if (!ok_key) {
				io.print_json_or_print(response, Error.NAUTH.detail("key incorrect").depuis(this));
				return;
			}
		}
	}
	
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
		}
	}
	
	
}
