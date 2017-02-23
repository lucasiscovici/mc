package beans.util;

import java.util.List;

import util.Dico;
import util.Parameters;

public class Bean {
	public Bean() {
		// TODO Auto-generated constructor stub
	}
	public Bean(Parameters params) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		fromParameters(params);
	}
	public Bean fromParameters(Parameters params) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

		for (Dico d : params.parameters) {
			Bean.class.getField(d.getKey()).set(this, d.getValue());
		}
		return this;
		
	}
	public List<Bean> fromParametersL(Parameters params) {
		
		return null;
		
	}
	public Bean fromParametersR(Parameters params) {
		return null;
		
	}
}
