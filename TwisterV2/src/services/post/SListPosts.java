package services.post;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import beans.util.Post;
import db.db_Post_Helper;
import services.utils.Service;
import services.utils.ServiceList;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;

/**
 * Classe du service recherche GET: KEY | KEY + TYPE=ALL | KEY + ID | KEY +
 * FRIENDS(IDS) | KEY + TYPE=MY  | KEY + TYPE=TOTAL + ID_MIN , NB(10)
 * OUT: RESPONSE:MESSAGES:[ID,DATE,TEXT,ID_USER]
 * | RESPONSE:MESSAGES:ID,DATE,TEXT,ID_USER
 */

public class SListPosts extends ServiceList {

	public SListPosts() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}

	public SListPosts(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	public SListPosts(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SListPosts(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
		// TODO Auto-generated cxonstructor stub
	}

	@Override
	public String[] giveGetEntry() {
		return Dico.vs_ak();
	}

	@Override
	public Parameters to_json() {
		return Dico.response(this);
	}

	@Override
	public void koko() throws IOException {
		try {

			if (TestError.params_auth(this)) {

				Parameters messages;
				db_Post_Helper dPH = db_Post_Helper.c();

				if (params.getDicosOK("friends")) { // KEY + FRIENDS -
					messages = dPH.listPostFromIdFriends(params);
				} else if (params.getDicosOK("id")) { // KEY + ID -
					if (params.getDicosOK("type") && params.getValue("type").equals("TOTAL")) {
						messages = dPH.SelectMongoWithIdTotal(params);

					}else{
					messages = dPH.SelectMongoWithId(params);
					}
				} else if (params.getDicosOK("type")) { // KEY + TYPE
					String Vtype = params.getValue("type");
					if (Vtype.equals("MY")) { // TYPE=MY -
						messages = dPH.listPostFromFriendsMoreLogin(params);
					} else if (Vtype.equals("ALL")) { // TYPE=ALL -
						messages = dPH.SelectMongoWith();
					}else if (Vtype.equals("TOTAL")) { // TYPE=TOTAL -
						messages = dPH.total(params);
					} else {
						RespS.c(this, Error.ErrArgs);
						return;
					}
				} else { // KEY -
					messages = dPH.listPostFromKeyMoreLogin(params);
				}

				Local_params.AddParamResponse("messages", messages);
				// io.print(Local_params);
				RespS.cj(this);

			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.UnknownHostException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException.detail(e.getMessage()));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.LucasException);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Class myClassBean() {
		// TODO Auto-generated method stub
		return Post.class;
	}

}
