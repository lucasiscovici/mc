package user;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_User_Helper;
import util.Error;
import util.IOLUCAS;
import util.JSONHelper;
import util.Parameters;
import util.TOJSON;
import util.io;
import util.Dico;


public class Login extends HttpServlet implements  IOLUCAS  {

//	public static void main(String[] args) throws IOException, NumberFormatException, SQLException, JSONException, ClassNotFoundException {
//		Login d = new Login();
//		params = new Parameters();
//		params.AddParam(Dico.kv("login","lolo"));
//		params.AddParam(Dico.kv("password","fd"));
//		String log = "user.Login main";
//		try {
//			koko();
//		} catch (NumberFormatException e) {
//			io.print(Error.JavaError.depuis(log));
//
//		} catch (SQLException e) {
//			io.print(Error.SqlError.detail(e.getMessage()).depuis(log));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			io.print(Error.JsonError.depuis(log));
//		}
//	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		services.user.Login.koko(Parameters.req(req),response);


	}

	
}
