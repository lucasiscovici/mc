package util;


import org.json.JSONException;
import org.json.JSONObject;



public final class JSONHelper {

	public static JSONObject to_json(TOJSON list) throws JSONException {
		JSONObject k = new JSONObject();
	
		for (Dico elt: list.to_json().parameters) {
			k.put(elt.key, elt.toJSON());
		}
		return k;
	}
}
