package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import beans.util.Bean;
import beans.util.Post;
import db.db_Session_Helper;
import db.db_User_Helper;
import services.post.SListPosts;
import util.Dico;
import util.LucasException;
import util.TestError;
import util.Usefull;
import util.io;

public class postS extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String key_exist = Usefull.getCookieValue(req, "key");

		/* Si le cookie existe, alors calcul de la durée */
		try {
			if (key_exist == null
					|| (key_exist != null && !db_Session_Helper.c().Auth(Dico.toP("key", key_exist)))) {
				if (key_exist != null) {
					Usefull.deleteCookie(resp, "key");
				}
				req.setAttribute("request", req);
				this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
			} else {
				req.setAttribute("type", "matrix");
				req.setAttribute("jsp_file", "matrix");
				req.setAttribute("que", "posts");
				req.setAttribute("request", req);
				req.setAttribute("key", key_exist);
				req.setAttribute("qui", db_User_Helper.c()
						.SelectWithKey(Dico.toP("key", key_exist)).getValue("login"));
				this.getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(resp.getWriter());
		}

	}

}