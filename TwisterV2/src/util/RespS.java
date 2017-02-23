package util;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import services.utils.Service;

public class RespS {
	
	public Service t = null;
	public Error e = null;
	public JSONObject j = null;
	
	/**
	 * Constructeur RespS(Service t, Error e)
	 * @param t Un service
	 * @param e Une erreur
	 */
	
	public RespS(Service t, Error e) {
			
		
		this.t = t;
		this.e = e;
	}
	
	/**
	 * Constructeur (Service t, JSONObject j)
	 * @param t Un service 
	 * @param j Un JSONObject
	 */
	
	public RespS(Service t, JSONObject j) {
		this.t = t;
		this.j = j;
		
	}
	
	/**
	 * Affiche notre réponse
	 * @throws IOException
	 * @throws JSONException
	 */
	
	public void print() throws IOException, JSONException {
		if (j!=null) {
			io.print_json_or_print(t.response, j);
			return;
		}
		io.print_json_or_print(t.response, e.depuis(t));
	}
	
	/**
	 * Initialise une réponse RespS(t, e)
	 * @param t Un service
	 * @param e Une erreur
	 */
	
	public static void c(Service t, Error e) {
		if (t.RespS == null) {

		t.RespS = new RespS(t, e);
		}
	}
	public static LucasException cl(Service t, Error e) {
		if (t.RespS == null) {
			
		
		t.RespS = new RespS(t, e);
		}
		return  new LucasException("FALSE");
	}
	
	/**
	 * Initialise une réponse et avec l'appelle a to_json du service
	 * @param t Un service
	 * @throws JSONException
	 */
	public static void cj(Service t) throws JSONException {
		t.RespS = new RespS(t, JSONHelper.to_json(t));
		
	}
	
	/**
	 * Initialise une réponse RespS(t, e)
	 * @param t
	 * @param e
	 * @throws JSONException
	 */
	
	public static void cj(Service t,JSONObject e) throws JSONException {
		t.RespS = new RespS(t, e);
		
	}
}
