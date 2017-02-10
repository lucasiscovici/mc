package util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.util.JSON;


public final class JSONHelper {

	public static JSONObject to_json(TOJSON list) throws JSONException {
		JSONObject k = new JSONObject();
	
		for (Dico elt: list.to_json().parameters) {
			k.put(elt.key, elt.toJSON());
		}
		return k;
	}
}
