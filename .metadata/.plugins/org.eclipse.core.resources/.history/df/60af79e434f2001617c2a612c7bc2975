package services.friend;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Friend_Helper;
import db.db_User_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;
import util.io;

public class SRemoveFriend extends Service {

	public SRemoveFriend() throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		super();
	}

	public SRemoveFriend(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
	}

	public SRemoveFriend(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
	}

	@Override
	public String[] giveGetEntry() {
		return Dico.vs_a("id_friend", "key");
	}

	@Override
	public Parameters to_json() {
		return Dico.vT_toP(this, "response");
	}

	@Override
	public void koko() {

		try {
			if (TestError.params_auth(this)) {

				if (!db_Friend_Helper.RemoveFriend(params)) {
					RespS.c(this, Error.SqlError.detail("PB delete friend check ids"));
					return;
				}

				Local_params.AddParam("response", "OK");
				RespS.cj(this);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
