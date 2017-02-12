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
import util.TestError;
import util.io;

public class SSearch extends Service {

	public SSearch() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
	}

	public SSearch(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		super(params);
	}

	public SSearch(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
	}

	@Override
	public String[] giveGetEntry() {
		return Dico.vs_a("key");
	}

	@Override
	public Parameters to_json() {
		return Dico.vT_toP(this, "messages");
	}

	@Override
	public void koko() {
		try {
			if (TestError.params(this)) {
				
			
			if (params.getValue("key").length()>0 && db_User_Helper.Auth(params)) {
				this.Local_params.AddParam("messages", db_Post_Helper.listPostFromKey(params.PS("key")));

				io.print_json_or_print(response, JSONHelper.to_json(this));
			}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
