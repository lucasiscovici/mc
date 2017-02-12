package servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import services.user.SLogout;
import util.LucasException;
import util.Parameters;

public class Logout extends HttpServlet {

protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	try {
		new SLogout(Parameters.req(req), resp).print();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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
