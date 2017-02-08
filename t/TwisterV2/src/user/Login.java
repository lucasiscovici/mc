package user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import util.*;
import util.Error;
import util.Dico;


public class Login extends HttpServlet implements TOJSON  {

	public static void main(String[] args) {
		Login d = new Login();
		d.koko("lucas", null);

	}
	private String login;
	private String password;
	private String token;
	
	public void koko(String log, String pass) {
		this.login = log;
		this.login = log;
		if (log == null || pass == null) {
			 try {
				io.print(Error.ErrArgs.to_JSON());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//BD CONNEXION
			String token = io.uniqueID();
			this.token = token;
			try {
				io.print(JSONHelper.to_json(this));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String log = req.getParameter("login");
		String pass = req.getParameter("password");
		koko(log,pass);

	}
	public List<Dico> to_json() {
		List<Dico> dic = new ArrayList<Dico>();
		dic.add(new Dico("login",login));
		dic.add(new Dico("token",token));
		dic.add(new Dico("id","0"));
		return dic;
	}
	
}
