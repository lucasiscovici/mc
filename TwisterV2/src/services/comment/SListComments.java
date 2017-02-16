package services.comment;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import services.utils.Service;
import util.LucasException;
import util.Parameters;

public class SListComments extends Service {

	public SListComments() throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
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
		return null;
	}

	@Override
	public Parameters to_json() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void koko() {
		// TODO Auto-generated method stub

	}

}
