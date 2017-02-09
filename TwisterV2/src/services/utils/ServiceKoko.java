package services.utils;

import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;

import util.LucasException;

public interface ServiceKoko {
	public void koko() throws IOException, NumberFormatException, SQLException, JSONException, ClassNotFoundException, LucasException;
}
