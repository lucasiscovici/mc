package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.db_Session_Helper;
import db.db_User_Helper;
import util.Dico;
import util.LucasException;
import util.Usefull;
import util.io;

public class matrix extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
    String key_exist = Usefull.getCookieValue( req, "key");
    /* Si le cookie existe, alors calcul de la durée */
    try {
		if ( key_exist == null ||  (key_exist != null && !db_Session_Helper.c().CheckIfExistWith(Dico.toP("key",key_exist)))) {
			if (key_exist != null) {
				Usefull.deleteCookie(resp, "key");
			}
			req.setAttribute("request", req);
			 this.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( req, resp );
		}else{
			req.setAttribute("type", "matrix");
			req.setAttribute("request", req);
			req.setAttribute("qui", db_User_Helper.c().SelectWithKey(Dico.toP("key",Usefull.getCookieValue(req,"key"))).getValue("login"));
			 this.getServletContext().getRequestDispatcher( "/home.jsp" ).forward( req, resp );
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (LucasException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
}

}