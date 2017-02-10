package services.post;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Post_Helper;
import db.db_User_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.JSONHelper;
import util.LucasException;
import util.Parameters;
import util.io;

public class SAddComment extends Service {

	public SAddComment() throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		// TODO Auto-generated constructor stub
	}

	public SAddComment(Parameters params) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public SAddComment(Parameters params, HttpServletResponse resp)
			throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_a("text", "key");
	}

	@Override
	public Parameters to_json() {
		// TODO Auto-generated method stub
		return Dico.vT_toP(this, "response");
	}

	@Override
	public void koko() throws IOException, NumberFormatException, SQLException,
			JSONException, ClassNotFoundException, LucasException {
		// TODO Auto-generated method stub
		if (params.CheckIfErrParams(getEntry)) {
			io.print_json_or_print(response, Error.ErrArgs.depuis(this));return;
		}
		
		if (!db_User_Helper.Auth(params)) {
			io.print_json_or_print(response, Error.NAUTH.depuis(this));return;
		}
		
		if (!db_Post_Helper.insertPost(params)) {
			io.print_json_or_print(response, Error.MongoError.depuis(this));return;
		}
		//io.print_text(response, "OK");
		Local_params.AddParam("response",Dico.toP("id",db_Post_Helper.id_post));
		io.print_json_or_print(response, JSONHelper.to_json(this));
	}

}
