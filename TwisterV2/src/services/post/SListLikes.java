package services.post;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Like_Helper;
import services.utils.Service;
import util.Dico;
import util.LucasException;
import util.Parameters;
import util.TestError;

public class SListLikes extends Service {

	public SListLikes() throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
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
		return Dico.vs_a("key","id_post");
	}

	@Override
	public Parameters to_json() {
		// TODO Auto-generated method stub
		return Dico.vT_toP(this, "response");
	}

	@Override
	public void koko() {
		// TODO Auto-generated method stub
		try {
			if (TestError.params_auth(this)) {
				Local_params.AddParam("response",Dico.toP("Likes",db_Like_Helper.c().SelectMongoWith("id_post", params)));
				RespS.cj(this);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
