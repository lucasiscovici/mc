package services.post;

import java.io.IOException;
import java.net.UnknownHostException;
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
	db_Post_Helper dPH = null;
	public SUpdatePost() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		// TODO Auto-generated constructor stub
		super();
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
		return Dico.vs_ak("id");
	}

	@Override
	public Parameters to_json() {
		// TODO Auto-generated method stub
		return Dico.response(this);
	}
	public boolean UpdateAdmin() throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		if (!dPH.Update(params)) {
			RespS.c(this, Error.MongoError.detail("modification"));
			return false;
		}
		return true;
	}
	public boolean Update() throws ClassNotFoundException, UnknownHostException, SQLException, LucasException {
		
		if (!dPH.checkIfSameIDUser(params)) {
			RespS.c(this, Error.NAUTH.detail("pas les droits neccessaires"));
			return false;
		}
		return UpdateAdmin();
	}
	public boolean ok() throws JSONException {
		Local_params.responseID(params);
		//io.print(Local_params);
		RespS.cj(this);
		return true;
	}
	
	@Override
	public void koko() throws IOException {
		dPH = new db_Post_Helper();
		boolean _;
		try {
			if ((TestError.params_authAdmin(this))) { // CHECK PARAMS + KEY ADMIN
				_ = UpdateAdmin() ? ok() : RespS.c(this, Error.ErrArgs) ;
			}
			if (TestError.params_auth(this)) { // ErrParams+AUTH
				_ = Update() ? ok() : RespS.c(this, Error.ErrArgs) ;
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
