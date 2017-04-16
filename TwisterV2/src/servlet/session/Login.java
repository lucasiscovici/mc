package servlet.session;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import services.session.SLogin;
import util.LucasException;
import util.Usefull;



/**
 * Classe représentant un servlet de connexion
 */
@SuppressWarnings("serial")
public class Login extends HttpServlet  {
	
	/**
	 * Appellera le service nécessaire à l'exécution de notre requête
	 * @param req Requête à exécuter
	 * @param resp Réponse de la requête
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		
			SLogin s = new SLogin(req,resp);
			
			s.print();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
