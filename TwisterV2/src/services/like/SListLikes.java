//NOT DONE
package services.like;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Like_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;

/**
 * class SListLikes service qui liste les likes
 *GET: ID
 *OUT: RESPONSE:ID:X
 */

public class SListLikes extends Service {

	public SListLikes() throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super();
		// TODO Auto-generated constructor stub
	}

	public SListLikes(Parameters params) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}
	public SListLikes(Parameters params, HttpServletResponse resp)
			throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SListLikes(HttpServletRequest req, HttpServletResponse resp)
			throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_ak("id_post");
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
	
				Parameters Likes = db_Like_Helper.c().ListLikesFromIdPost(params);
				Local_params.AddParamResponse("Likes",Likes);
				RespS.cj(this);
				
			}
		} catch (Exception e) {
		}
	}

}
