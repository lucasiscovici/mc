package services.session;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Session_Helper;
import services.utils.Service;
import util.Dico;
import util.LucasException;
import util.Parameters;
import util.TestError;

/**
 * Classe service pour la liste des session
 * GET: KEY | KEY + ID | KEY + TYPE=ALL
 * OUT: RESPONSE:SESSIONS:ID,KEY,DATE,ID_USER | RESPONSE:SESSIONS:[ID,KEY,DATE,ID_USER]
 *
 */
public class SListSessions extends Service {

	public SListSessions() throws NumberFormatException, ClassNotFoundException, IOException, SQLException,
			JSONException, LucasException {
		// TODO Auto-generated constructor stub
		super();
	}

	public SListSessions(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}

	public SListSessions(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public SListSessions(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
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
	public void koko()  {
		// TODO Auto-generated method stub
		
		try {
			if (TestError.params_auth(this)) {
				db_Session_Helper dSH = db_Session_Helper.c();
				Parameters sessions;
				if (params.getDicosOK("id")) { // KEY + ID -
					sessions = dSH.Select(params);	
				}else if (params.getDicosOK("type") && params.getValue("type").equals("ALL")) { // KEY + TYPE=ALL
					sessions = dSH.SelectWith(null);
				}else { // KEY - 
					sessions = dSH.SelectWithKey(params);
				}
				Local_params.AddParamResponse("sessions", sessions);
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
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
