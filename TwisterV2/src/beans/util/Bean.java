package beans.util;

import java.lang.reflect.Field;
import java.util.List;

import util.Dico;
import util.Parameters;
import util.io;

public abstract class Bean implements BeanCONF {
	public String id;
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public Bean() {
		// TODO Auto-generated constructor stub
	}
	public Bean(Parameters params) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		fromParameters(params);
	}
	public Bean fromParameters(Parameters params) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Class c = getClass();
		
		if (params==null || params.parameters==null) {
			return null;
		}
		io.print(params);
		//io.print(params);
		for (Dico d : params.parameters) {
			c.getField(d.getKey()).set(this, d.getValue());
		}
		return this;
		
	}
	public List<Bean> fromParametersL(Parameters params) {
		
		return null;
		
	}
	public Bean fromParametersR(Parameters params) {
		return null;
		
	}
	public String toString() {
		  StringBuilder result = new StringBuilder();
		  String newLine = System.getProperty("line.separator");

		  result.append( this.getClass().getName() );
		  result.append( " Object {" );
		  result.append(newLine);

		  //determine fields declared in this class only (no fields of superclass)
		  Field[] fields = this.getClass().getDeclaredFields();

		  //print field names paired with their values
		  for ( Field field : fields  ) {
		    result.append("  ");
		    try {
		      result.append( field.getName() );
		      result.append(": ");
		      //requires access to private field:
		      result.append( field.get(this) );
		    } catch ( IllegalAccessException ex ) {
		      System.out.println(ex);
		    }
		    result.append(newLine);
		  }
		  result.append("}");

		  return result.toString();
		}
	public Class myClass() {
		// TODO Auto-generated method stub
		return getClass();
	}
}
