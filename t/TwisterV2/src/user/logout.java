package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class logout extends HttpServlet{
	private String token;
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void  koko(String key) {
		this.token = key;
		//TRAITEMENT
				
	}	
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
	String tok = req.getParameter("key");
	koko(tok);
}
}
