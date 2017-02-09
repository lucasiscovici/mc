package services.utils;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import util.IOLUCAS;
import util.IParameters;
import util.LucasException;
import util.Parameters;
import util.TOJSON;

public abstract class Service implements IOLUCAS, IParameters, TOJSON, ServiceKoko  {
	public Parameters Local_params = new Parameters(); 
	public HttpServletResponse response = null; // FROM IOLUCAS
	public String[] getEntry = {}; // FROM IParameters
	public Parameters params = null; //
	
	public Service() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		this(null);
	}

	public Service(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		this(params,null);
	}

	public Service(Parameters params,HttpServletResponse resp) throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		this.params = params;
		this.response = resp;
		getEntry = giveGetEntry();
		koko();
	}
	

}
