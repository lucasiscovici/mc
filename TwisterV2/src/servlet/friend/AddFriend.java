package servlet.friend;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import services.friend.SAddFriend;
import util.Error;
import util.LucasException;
import util.io;

@SuppressWarnings("serial")
public class AddFriend extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			new SAddFriend(req, resp).print();
		} catch (JSONException e) {
			io.print_text(resp,Error.JsonError);
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				io.print_json_or_print(resp, Error.SqlError.detailT(this, e.getMessage()));
			} catch (JSONException e1) {
				io.print_text(resp, Error.SqlError.detailT(this, e.getMessage()));
				e1.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
