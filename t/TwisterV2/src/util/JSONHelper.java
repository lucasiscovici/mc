package util;

import org.json.JSONException;
import org.json.JSONObject;


public final class JSONHelper {
	public static String to_json(TOJSON cl) throws JSONException {
		JSONObject k = new JSONObject();
		for (Dico elt: cl.to_json()) {
			k.put(elt.key, elt.value);
		}
		return k.toString();
	}
}
