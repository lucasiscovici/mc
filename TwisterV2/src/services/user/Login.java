package services.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import db.db_User_Helper;
import util.Dico;
import util.Error;
import util.JSONHelper;
import util.Parameters;
import util.io;

public class Login {
	private static String key;
	private static HttpServletResponse response = null;
	private static String[] getEntry = {"login","password"};
	
	private static Parameters params;
	public static void koko(Parameters params,HttpServletResponse resp) throws IOException, NumberFormatException, SQLException, JSONException, ClassNotFoundException {
	response=resp;
	koko(params);
	}
	public static void koko(Parameters params) throws IOException, NumberFormatException, SQLException, JSONException, ClassNotFoundException {
		
		
		if (params.CheckIfErrParams(getEntry)) {
			 try {
				io.print_json_or_print(response, Error.ErrArgs.to_JSON());

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//BD CONNEXION
			boolean user_exist = db_User_Helper.CheckIfExist(params);
			
			if (!user_exist) {
				io.print_json_or_print(response, Error.LoginNotExist);
				return;
			}
			boolean good_password = db_User_Helper.CheckPassword(params);

			if (!good_password) {
				io.print_json_or_print(response, Error.BadPassword);
				return;
			}
			key = io.uniqueID();
			try {
				int id = db_User_Helper.getIdWithLogin(params);
				boolean ok_insert = db_User_Helper.InsertSession(Parameters.fromDicos(Dico.kv("id_user", id),Dico.kv("key", key)));
				if (!ok_insert) {
					io.print_json_or_print(response, Error.SqlError.setDescription("Pb insertion session"));
					return;
				}
				io.print_json_or_print(response,JSONHelper.to_json(to_json())); 
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static List<Dico> to_json() {
		List<Dico> dic = new ArrayList<Dico>();
		dic.add(Dico.kv("key",key));
		return dic;
	}
}
