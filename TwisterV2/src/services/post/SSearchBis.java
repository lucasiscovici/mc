package services.post;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Post_Helper;
import db.db_User_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;

public class SSearchBis extends Service {

	public SSearchBis() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}

	public SSearchBis(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	public SSearchBis(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SSearchBis(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		return Dico.vs_a("key");
	}

	@Override
	public Parameters to_json() {
		return Dico.vT_toP(this, "response");
	}

	@Override
	public void koko() {
		try {
			if (TestError.params(this)) {

				if (params.getValue("key").length() > 0 && db_User_Helper.Auth(params)) {
					if (params.getValue("ok_friends").length() > 0 && params.getValue("ok_friends").equals("true")) {
						this.Local_params.AddParam("messages", db_Post_Helper.listPostFromFriends(params.PS("key")));
						this.Local_params.AddParam("response", this.Local_params.getDico("messages"));

						RespS.cj(this);

					} else {
						this.Local_params.AddParam("messages", db_Post_Helper.listPostFromKey(params.PS("key")));
						this.Local_params.AddParam("response", this.Local_params.getDico("messages"));
						RespS.cj(this);
					}
				}else{
					this.Local_params.AddParam("response", "pb");
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.UnknownHostException);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException);
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.LucasException);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		}
	}

}
