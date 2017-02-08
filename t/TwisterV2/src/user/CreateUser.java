package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import util.Error;
import util.io;

public class CreateUser extends HttpServlet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private String prenom;
	private String nom;
	private String login;
	private String password;
	
	public void koko(String prenom, String nom, String login, String password) throws JSONException {
		this.prenom = prenom;
		this.nom = nom;
		this.login = login;
		this.password = password;
		if (prenom == null || nom == null || login == null || password == null) {
			io.print(Error.ErrArgs.to_JSON());
		}else{
			// TRAITEMENT 1 -> VERIF DISPO LOGIN 
			// CREATE USER WITH DB
		}
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			koko(req.getParameter("prenom"),req.getParameter("nom"),req.getParameter("login"),req.getParameter("password"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
