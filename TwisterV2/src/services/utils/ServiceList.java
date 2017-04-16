package services.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import beans.util.Bean;
import util.Dico;
import util.LucasException;
import util.Parameters;
import util.io;

public abstract class ServiceList extends Service implements ServiceListCONF {
	
	public List<Bean> to_bean() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<Bean> lb = new ArrayList<Bean>();
	
		String key = ((Bean) this.myClassBean().getConstructor().newInstance()).myKey();
		if (!this.Local_params.getDico(key).isArray() && this.Local_params.getDico(key).valuesd.size() > 0) {
			 lb.add((Bean) this.myClassBean().getConstructor(Parameters.class).newInstance(this.Local_params.getDico(key).valuesdP()));
			 return lb;
		}else{
		for (Dico bean : (this.Local_params.getDico(key).valuesdP().parameters)) {
			try {
				lb.add((Bean) this.myClassBean().getConstructor(Parameters.class).newInstance(bean.valuesdP()));
			} catch (Exception e) {
				io.print(e.getCause());
			} 
		}
		return lb;
		}
	}

	public ServiceList() throws NumberFormatException, ClassNotFoundException, IOException, SQLException, JSONException,
			LucasException {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceList(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	public ServiceList(Parameters params, HttpServletResponse resp) throws NumberFormatException,
			ClassNotFoundException, IOException, SQLException, JSONException, LucasException {
		super(params, resp);
		// TODO Auto-generated constructor stub
	}

	public ServiceList(Parameters params) throws NumberFormatException, ClassNotFoundException, IOException,
			SQLException, JSONException, LucasException {
		super(params);
		// TODO Auto-generated constructor stub
	}


}
