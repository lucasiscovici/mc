package services.comment;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Comment_Helper;
import db.db_User_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;

/**
 * class SListComments service qui listes les commentaires
 * GET: KEY + TYPE=ALL | KEY + ID | KEY + ID_POST
 *OUT: RESPONSE:ID:X
 */

public class SListComments extends Service {
	db_Comment_Helper dUH = null;
	Parameters comments = null;
	public SListComments() throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		super();
		// TODO Auto-generated constructor stub
	}

	public SListComments(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public SListComments(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SListComments(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_ak();
	}

	@Override
	public Parameters to_json() {
		// TODO Auto-generated method stub
		return Dico.response(this);
	}
	
	private boolean KEY_ID() throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		comments = dUH.SelectMongoWithId(params);
		return true;
	}
	private boolean KEY_ID_POST() throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		comments = dUH.ListCommentsFromIdPost(params);
		return true;
	}
	
	private boolean KEY_ALL() throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		comments = dUH.SelectMongoWith(null);
		return true;
	}
	private boolean ok() throws LucasException, JSONException {
		Local_params.AddParamResponse("comments", comments);
		RespS.cj(this);
		return true;
	}
	@Override
	public void koko() {
		dUH = db_Comment_Helper.c();
		boolean _;
		try {
			if (TestError.params_authAdmin(this)) { // ADMIN
				_ = (
					   router("id") ? KEY_ID() : false
					|| router("type","ALL") ? KEY_ALL() : false
					|| router("id_post") ? KEY_ID_POST() : false 
					) ? 
					ok() :
					RespS.c(this, Error.ErrArgs);
			}
		else if (TestError.params_auth(this)) {
				_ = ( 
						router("id") ? KEY_ID() : false
						|| router("id_post") ? KEY_ID_POST() : false 
						) ? 
						ok() :
						RespS.c(this, Error.ErrArgs);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ParseException);
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.LucasException);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.UnknownHostException);
		}
	}

}
