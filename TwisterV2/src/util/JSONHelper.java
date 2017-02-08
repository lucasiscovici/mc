package util;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


public final class JSONHelper {
	public static String to_json(List<Dico> list) throws JSONException {
		JSONObject k = new JSONObject();
		for (Dico elt: list) {
			k.put(elt.key, elt.value);
		}
		return k.toString();
	}
}
