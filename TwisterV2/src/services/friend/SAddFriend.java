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

public class SAddFriend extends Service {


	public SAddFriend() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
	}

	public SAddFriend(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
	}

	public SAddFriend(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
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
	public void koko() {
		if (params.CheckIfErrParams(getEntry)) {
			RespS.c(this, Error.ErrArgs);
			return;
		}else{
			//Check Auth with Key
			try {
				if (!db_User_Helper.Auth(params)) {
					RespS.c(this, Error.NAUTH.detail("key incorrect"));
					return;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Insert Friend
			try {
				if (!db_Friend_Helper.InsertFriend(params)) {
					RespS.c(this, Error.SqlError.detail("PB Insert friend check ids"));
					return;
				}else{
					try {
						io.print_text(response, "OK");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
			}
		}
	}

}
