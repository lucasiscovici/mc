package services.comment;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import services.utils.Service;
import util.Dico;
import util.LucasException;
import util.Parameters;
import util.TestError;

/**
 * GET: KEY | KET + id | key + TYPE=ALL
 *
 */
public class SRemoveComment extends Service {

	public SRemoveComment() throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		// TODO Auto-generated constructor stub
		super();
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

	public SRemoveComment(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
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

	@Override
	public void koko() {
		// TODO Auto-generated method stub
		try {
			if (TestError.params_auth(this)) {
				
				
				Local_params.AddParamRespOK();
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
