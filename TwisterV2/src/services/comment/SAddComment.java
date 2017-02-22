package services.comment;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Comment_Helper;
import db.db_Post_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;
import util.io;

/**
 * class SAddComment service d'ajout de commentaires
 *GET: KEY + TEXT + ID_POST
 *OUT: RESPONSE:ID:X
 */
public class SAddComment extends Service {

	public SAddComment() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}

	public SAddComment(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public SAddComment(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SAddComment(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_ak("text","id_post");
	}

	@Override
	public Parameters to_json() {
		// TODO Auto-generated method stub
		return Dico.response(this);
	}

	@Override
	public void koko() {
		// TODO Auto-generated method stub
		try {
			if (TestError.params_auth(this)) {
				if (!db_Comment_Helper.c().Insert(params)) {
					RespS.c(this, Error.MongoError.detail("Insert"));return;
				}
				Local_params.AddParam("response", params.getDico("id"));
				RespS.cj(this);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		} catch (LucasException e) {
			RespS.c(this, Error.LucasException);
			// TODO Auto-generated catch block
		} catch (ClassNotFoundException e) {
			RespS.c(this, Error.ClassNotFoundException);
			// TODO Auto-generated catch block
		} catch (SQLException e) {
			RespS.c(this, Error.SQLException);
			// TODO Auto-generated catch block
		} catch (ParseException e) {
			RespS.c(this, Error.ParseException);
			// TODO Auto-generated catch block
		} catch (UnknownHostException e) {
			RespS.c(this, Error.UnknownHostException);
			// TODO Auto-generated catch block
			
		}
	}

}
