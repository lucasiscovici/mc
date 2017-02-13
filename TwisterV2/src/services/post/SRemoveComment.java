package services.post;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Post_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;

public class SRemoveComment extends Service {

	public SRemoveComment() throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		// TODO Auto-generated constructor stub
	}

	public SRemoveComment(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public SRemoveComment(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SRemoveComment(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_a("id", "key");
	}

	@Override
	public Parameters to_json() {
		// TODO Auto-generated method stub
		return Dico.vT_toP(this, "response");
	}

	@Override
	public void koko() {
		try {

			if (TestError.params_auth(this)) {

				if (!db_Post_Helper.removePost(params)) {
					RespS.c(this, Error.MongoError.detail("remove post"));
					return;
				}

				this.Local_params.AddParam("response", "OK");
				RespS.cj(this);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.IOException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		}
	}

}
