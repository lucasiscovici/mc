package user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Helper;
import db.db_User_Helper;
import util.Dico;
import util.Error;
import util.IOLUCAS;
import util.IParameters;
import util.Parameters;
import util.io;

public class CreateUser extends HttpServlet implements IOLUCAS, IParameters {

	public static void main(String[] args) throws NumberFormatException, JSONException, IOException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		params = new Parameters();
		params.AddParam(new Dico("nom","l"));
		params.AddParam(new Dico("prenom","l"));
		params.AddParam(new Dico("login","loloj"));
		params.AddParam(new Dico("password","d"));
		//koko();
	}
	private static String[] getEntry = {"prenom","nom","login","password"};
	public static HttpServletResponse response = null;
	private static Parameters params;

	public void koko() throws JSONException, IOException, NumberFormatException, SQLException, ClassNotFoundException {
		if (params.CheckIfErrParams(getEntry)) {
			io.print_json_or_print(response, Error.ErrArgs);
		}else{
			String login = params.getValue("login");
			// TRAITEMENT 1 -> VERIF DISPO LOGIN 
			// CREATE USER WITH DB
			
			if (db_User_Helper.CheckIfExist(params)) {
				io.print_json_or_print(response, Error.LoginExist.depuis(this));
			}else{
				if (db_User_Helper.InsertUser(params)){
					io.print_json_or_printFromString(response, "OK");
				}else{
					io.print_json_or_print(response, Error.SqlError.setDescription("pb d'ajout d'un user"));
				}
				
			}
		}
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.response=resp;
		params = new Parameters(req);

		try {
			koko();
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
		}
	}

}
