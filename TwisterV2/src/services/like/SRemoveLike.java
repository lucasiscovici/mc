//NOT FINI
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

public class SRemoveLike extends Service {

	public SRemoveLike() throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		// TODO Auto-generated constructor stub
	}

	public SRemoveLike(Parameters params) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}
	public SRemoveLike(Parameters params, HttpServletResponse resp)
			throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SRemoveLike(HttpServletRequest req, HttpServletResponse resp)
			throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		// TODO Auto-generated method stub
		return Dico.vs_ak("id");
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
				
				if (!db_Like_Helper.c().Remove(params)) {
					RespS.c(this, Error.MongoError);
				}
				
				Local_params.AddParamRespOK();
				RespS.cj(this);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
