package servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import services.user.SCreateUser;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.io;

@SuppressWarnings("serial")
public class CreateUser extends HttpServlet  {

//	public static void main(String[] args) throws NumberFormatException, JSONException, IOException, SQLException, ClassNotFoundException {
//		// TODO Auto-generated method stub
//		params = new Parameters();
//		params.AddParam(new Dico("nom","l"));
//		params.AddParam(new Dico("prenom","l"));
//		params.AddParam(new Dico("login","loloj"));
//		params.AddParam(new Dico("password","d"));
//		//koko();
//	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			new SCreateUser(Parameters.req(req), resp).print();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			io.print_text(resp, Error.JsonError.depuis(this));
		} catch (NumberFormatException e) {
			try {
				io.print_json_or_print(resp, Error.JavaError.depuis(this));
			} catch (JSONException e1) {
				io.print_text(resp, Error.JsonError.depuis(this));

			}
			// TODO Auto-generated catch block
		} catch (SQLException e) {
			try {
				io.print_json_or_print(resp, Error.SqlError.detail(e.getMessage()).depuis(this));
			} catch (JSONException e1) {
				io.print_text(resp, Error.JsonError.depuis(this));

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
