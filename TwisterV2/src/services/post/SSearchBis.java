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
import util.JSONHelper;
import util.LucasException;
import util.Parameters;
import util.io;

public class SSearchBis extends Service {

	public SSearchBis() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
	}

	public SSearchBis(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		super(params);
	}

	public SSearchBis(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
	}

	@Override
	public String[] giveGetEntry() {
		return Dico.vs_a("key","query","ok_friends");
	}

	@Override
	public Parameters to_json() {
		return Dico.vT_toP(this, "messages");
	}

	@Override
	public void koko() {
		if(params.CheckIfErrParams(getEntry)){
			RespS.c(this, Error.ErrArgs);
			return;
		}
		
		if (params.getValue("key").length()>0 && db_User_Helper.Auth(params)) {
			if (params.getValue("ok_friends").length() > 0 && params.getValue("ok_friends").equals("true")) {
				this.Local_params.AddParam("messages", db_Post_Helper.listPostFromFriends(params.PS("key")));
				io.print_json_or_print(response, JSONHelper.to_json(this));

				
			}else{
			this.Local_params.AddParam("messages", db_Post_Helper.listPostFromKey(params.PS("key")));

			io.print_json_or_print(response, JSONHelper.to_json(this));
			}
		}
	}

}
