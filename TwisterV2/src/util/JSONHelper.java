package util;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe JSONHelper
 */

public final class JSONHelper {
	
	/**
	 * 
	 * @param list Un TOJSON
	 * @return un jsonobject
	 * @throws JSONException
	 */

	public static JSONObject to_json(TOJSON list) throws JSONException {
		JSONObject k = new JSONObject();
	
		for (Dico elt: list.to_json().parameters) {
			
			k.put(elt.key, elt.toJSON());
		}
		return k;
	}
	
	/**
	 * 
	 * @param list Un dico
	 * @return un objet json par rapport à notre dico
	 * @throws JSONException
	 */
	
	public static JSONObject to_json_dico(Dico list) throws JSONException {
		JSONObject k = new JSONObject();
		k.put(list.key, list.toJSON());
		return k;
	}
}
