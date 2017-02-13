package util;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import services.utils.Service;

public class RespS {
	public Service t = null;
	public Error e = null;
	public JSONObject j = null;
	public RespS(Service t, Error e) {
		this.t = t;
		this.e = e;
	}
	public RespS(Service t, JSONObject j) {
		this.t = t;
		this.j = j;
	}
	public void print() throws IOException, JSONException {
		if (j!=null) {
			io.print_json_or_print(t.response, j);
			return;
		}
		io.print_json_or_print(t.response, e.depuis(t));
	}
	public static void c(Service t, Error e) {
		t.RespS = new RespS(t, e);
	}
	public static void cj(Service t) throws JSONException {
		t.RespS = new RespS(t, JSONHelper.to_json(t));
		
	}
	public static void cj(Service t,JSONObject e) throws JSONException {
		t.RespS = new RespS(t, e);
		
	}
}
