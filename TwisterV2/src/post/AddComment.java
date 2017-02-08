package post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import util.Error;
import util.IOLUCAS;
import util.io;

public class AddComment extends HttpServlet implements IOLUCAS {
	public HttpServletResponse response = null;
	private String text;
	private String token;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void koko(String text, String key) throws JSONException, IOException {
		this.text = text;
		this.token = key;
		if (text == null || token == null) {
			io.print_json_or_print(response, Error.ErrArgs.to_JSON());
		}

		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.response  = resp;
		try {
			koko(req.getParameter("text"), req.getParameter("key"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
