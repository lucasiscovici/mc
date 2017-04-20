//NOT DONE
package services.like;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Like_Helper;
import db.db_User_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;

/**
 * class SListLikes service qui liste les likes
 * GET: KEY | KEY + TYPE=ALL | KEY + ID | KEY + ID_POST
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
		return Dico.vs_ak();
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
					Parameters likes = null;
					db_Like_Helper dUH = db_Like_Helper.c();

					if (params.getDicosOK("id")) { // KEY + ID -
						likes = dUH.Select(params);
					} else if (params.getDicosOK("type") && params.getValue("type").equals("ALL")) { //KEY + TYPE=ALL 
						likes = dUH.SelectMongoWith(null);
					} else if (params.getDicosOK("id_post")) {
						likes = dUH.SelectMangoIdPost(params);
					}
					else { // KEY -
						//likes = dUH.SelectMongoWithKey(params);
					}

					Local_params.AddParamResponse("likes", likes);
					RespS.cj(this);

				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LucasException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

}
