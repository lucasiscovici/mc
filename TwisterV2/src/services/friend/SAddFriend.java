package services.friend;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_Friend_Helper;
import services.utils.Service;
import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.TestError;

/**
 * Classe du service d'ajout ami
 * GET: KEY + ID_FRIEND
 * OUT: RESPONSE:ID=X
 */

public class SAddFriend extends Service {
	
	/**
	 * Constructeur SAddFriend()
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SAddFriend() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
	}
	
	/**
	 * Constructeur SAddFriend(Parameters params, HttpServletResponse resp)
	 * @param params Nos paramètres
	 * @param resp Notre réponse
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SAddFriend(Parameters params, HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException,
			IOException, SQLException, JSONException, LucasException {
		super(params, resp);
	}
	
	/**
	 * Constructeur SAddFriend(Parameters params)
	 * @param params Nos paramètres
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SAddFriend(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
	}
	
	/**
	 * Constructeur SAddFriend(HttpServletRequest req, HttpServletResponse resp)
	 * @param req Notre requête
	 * @param resp Notre réponse
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSONException
	 * @throws LucasException
	 */

	public SAddFriend(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] giveGetEntry() {
		return Dico.vs_ak("id_friend");
	}

	@Override
	public Parameters to_json() {
		return Dico.response(this);
	}
	
	@Override
	public void koko() {
		try {
			if (TestError.params_auth(this)) {

				if (!db_Friend_Helper.c().Insert(params)) {
					RespS.c(this, Error.SqlError.detail("PB Insert friend check ids"));
					return;
				}
				Local_params.responseID(params);
				RespS.cj(this);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.SQLException);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.ClassNotFoundException);
		} catch (LucasException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.LucasException);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			RespS.c(this, Error.JSONException);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
