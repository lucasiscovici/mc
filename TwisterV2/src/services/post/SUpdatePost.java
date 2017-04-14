package services.post;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

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

public class SUpdatePost extends Service {

	public SUpdatePost() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		// TODO Auto-generated constructor stub
	}

	public SUpdatePost(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public SUpdatePost(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SUpdatePost(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_ak("text", "title", "lg");
	}

	@Override
	public Parameters to_json() {
		// TODO Auto-generated method stub
		return Dico.response(this);
	}

	@Override
	public void koko() throws IOException {
		
		try {
			if (TestError.params_auth(this)) { // ErrParams+AUTH

				if (!db_Post_Helper.c().Update(params)) {
					RespS.c(this, Error.MongoError.detail("modification"));
					return;
				}

				Local_params.responseID(params);
				//io.print(Local_params);
				RespS.cj(this);
			}
		} catch (ClassNotFoundException e) {
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
