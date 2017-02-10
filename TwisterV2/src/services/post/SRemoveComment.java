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
import util.LucasException;
import util.Parameters;
import util.io;

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

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_a("id","key");
	}

	@Override
	public Parameters to_json() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void koko() throws IOException, NumberFormatException, SQLException, JSONException, ClassNotFoundException,
			LucasException {
		if (params.CheckIfErrParams(getEntry)) {
			io.print_json_or_print(response, Error.ErrArgs.depuis(this));
		}
		if (!db_User_Helper.Auth(params)) {
			io.print_json_or_print(response, Error.NAUTH.depuis(this));
		}
		if (!db_Post_Helper.removePost(params)) {
			io.print_json_or_print(response, Error.MongoError.depuis(this));
		}
		io.print_text(response,"OK");
	}

}
