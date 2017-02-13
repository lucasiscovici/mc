package services.post;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Post_Helper;
import db.db_User_Helper;
import services.utils.Service;
import util.Dico;
<<<<<<< HEAD
import util.Error;
=======
>>>>>>> ac6e7af607ffcc8bb2755005dab74c9ffbbf2604
import util.LucasException;
import util.Parameters;
import util.TestError;

public class SSearch extends Service {

	public SSearch() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}

	public SSearch(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	public SSearch(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SSearch(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		return Dico.vs_a("key");
	}

	@Override
	public Parameters to_json() {
		return Dico.vT_toP(this, "messages");
	}

	@Override
	public void koko() {
		try {
			if (TestError.params(this)) {

				if (params.getValue("key").length() > 0 && db_User_Helper.Auth(params)) {
					this.Local_params.AddParam("messages", db_Post_Helper.listPostFromKey(params.PS("key")));

					RespS.cj(this);
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.UnknownHostException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
<<<<<<< HEAD
			RespS.c(this, Error.SQLException);
=======
			e.printStackTrace();
>>>>>>> ac6e7af607ffcc8bb2755005dab74c9ffbbf2604
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		}
	}

}
