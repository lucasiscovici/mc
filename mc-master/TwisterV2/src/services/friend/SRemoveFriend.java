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
		return Dico.vs_a("id_friend","key");
	}

	@Override
	public Parameters to_json() {
		return null;
	}

	@Override
	public void koko() throws IOException, NumberFormatException, SQLException, JSONException, ClassNotFoundException,
	LucasException {

		if (params.CheckIfErrParams(getEntry)) {
			io.print_json_or_print(response, Error.ErrArgs.depuis(this).to_JSON());
		}else{
			if (!db_User_Helper.Auth(params)) {
				io.print_json_or_print(response, Error.NAUTH.detail("key incorrect",this));
				return;
			}

			if (!db_Friend_Helper.RemoveFriend(params)) {
				io.print_json_or_print(response, Error.SqlError.detail("PB delete friend check ids",this));
			}else{
				io.print_text(response, "OK");
			}
		}
	}

}
