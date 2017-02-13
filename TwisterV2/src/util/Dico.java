package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import services.utils.Service;

public class Dico {
	String key;
	String value = null;
	List<String> values = null;
	List<Dico> valuesd = null;
	public boolean false_key = false;
	public Dico(Object o, Object o1){
		
	}
	
	boolean is_dicd = false;

	boolean is_dic = false;
	public Dico(String key, String value) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.value = value;
	}
	public boolean is_null() {
		return key.equals("null") && value.equals("null");
	}
	@SuppressWarnings("unchecked")
	public Dico(String key, List<?> values) {
		// TODO Auto-generated constructor stub
		this.key = key;
		 if(values != null && !values.isEmpty())
		    {
		        if (values.get(0) instanceof String)
		        {
		        	this.values = (List<String>) values;
		    		this.is_dic = true;
		        }
		        else if(values.get(0) instanceof Dico)    
		        {
		        	this.valuesd = (List<Dico>) values;
		    		this.is_dicd = true;
		        }
		        
		    }

	}

	public Dico copy() {
		if (is_dic) {
			return Dico.kvs(key, values);
		}else if (is_dicd){
			return Dico.kvsd(key, valuesd);
		}
		else{
			return Dico.kv(key, value);
		}
	}
	
	public Dico(String value) {
		this.key = Usefull.uniqueID();
		this.value = value;
	}
	public int countD = 0;
	public Dico addD(String s,String g) {
		
		this.is_dicd = true;
		if (this.valuesd==null) {
			this.valuesd = new ArrayList<Dico>();
			this.setKey(countD);
			
		}
		this.valuesd.add(Dico.kv(s, g));
		this.is_dicd = true;
		return this;
	}
	public Dico setKey(Object ob) {
		this.key = ob.toString();
		return this;
	}
	public static Parameters fv(String...values) {
		Parameters p = new Parameters();
		for (String string : values) {
			p.AddParam(Dico.v(string));
		}
		return p;
	}
	public static Dico vT(Service ob,String key) {
		return ob.Local_params.getDico(key).copy();
	}
	
	public static Parameters vT_toP(Service ob,String key) {
		return Parameters.fromDicos(vT(ob, key));
	}
	public static Parameters vT_toP(Service ob,String...keys) {
		return  Dico.psp(ob.Local_params, keys);
	}
	public static Parameters toP(Object...values) throws LucasException {
		Parameters p = new Parameters();
		if (values.length%2!=0) {
			 throw new LucasException("toP key,value,key2,value2,etc.... nb de parametres paires");
		}
		for (int i = 0; i < values.length; i++) {
			if (i%2==0) {
				
			p.AddParam(values[i].toString(), values[i+1].toString());
			}
			
		}
		return p;
	}
	public Parameters toPa() {
		return Parameters.fromDicos(this);
	}
	public static List<Dico> vsT(Service ob,String...keys) {
		return Dico.ps(ob.Local_params.PS(keys));
	}
	public static Dico kvs(String key, List<String> values) {
		return new Dico(key,values);
	}
	public static Dico kvsd(String key, List<Dico> dicos) {
		return new Dico(key,dicos);
	}
	public static Dico kv(String key, String value) {
		return new Dico(key,value);
	}
	public static Dico kv(String key, int value) {
		return new Dico(key,""+value);
	}
	public static Dico v(String value) {
		return new Dico(value);
	}
	public static Dico k(String value) {
		return new Dico(value,"");
	}
	
	public static Parameters ks(String...values) {
		Parameters p = new Parameters();
		
		for (String string : values) {
	
			p.AddParam(Dico.k(string));
		}
		return p;
	}
	public static Dico v(int value) {
		return new Dico(""+value);
	}
	public Dico(String key, String[] values) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.values = Arrays.asList(values);
		this.is_dic = true;
	}
	public Dico() {
		// TODO Auto-generated constructor stub
	}
	public String getKey() {
		return key;
	}
	public Dico setKey(String key) {
		this.key = key;
		return this;
	}
	public void setValue(String value) {
		if (value.length() > 0) {
			
		
		this.value = value;
		}
	}
public static String[] vs_a(String...values) {
		return values;
	}
public static Dico kvs(String key,String[] values) {
	Dico d = Dico.k(key);
	List<String> value = Arrays.asList(values);
	for (String string : value) {
		if (string.length() == 0) {
			value.remove(value.indexOf(string));
		}
	}
	d.values = value;
	d.is_dic = true;
	return d;
}
	public void setValues(String[] values) {
		
		List<String> value = Arrays.asList(values);
		for (String string : value) {
			if (string.length() == 0) {
				value.remove(value.indexOf(string));
			}
		}
		this.values = value;
		this.is_dic = true;
	}
	public boolean is_Dico() {
		return is_dic == true;
	}
	public String getValue() {
		if (this.value == null) {
			return "";
		}
		return value;
	}
	public List<String> getValues() {
		if (this.values == null) {
			return new ArrayList<String>();
		}
		return values;
	}
	@Override
	public String toString() {
		String d = this.key+": ";
		// TODO Auto-generated method stub
		if (this.is_Dico()) {
			for (String string : values) {
				d += string+" ";
			}
		}else if(this.is_dicd) {
			if (this.valuesd == null) {
				return "\n";
			}
			for (Dico dd : valuesd) {
				d+= "\n"+dd.toString();
			}
			d+="\n\n";
		}
		else{
			d+=this.value;
		}
		return d;
	}
	
	public Object toJSON() throws JSONException {
		//io.print(this.is_dicd);

		if(this.is_dicd) {
			JSONArray arr = new JSONArray();
			JSONObject k = new JSONObject();
			
			for (Dico d : this.valuesd) {
				//io.print(d);
				if (d.false_key) {
					Object dff = d.toJSON();
					if (dff instanceof JSONArray) {
						arr.put( ((JSONArray) d.toJSON()).get(0));

					}else{
						arr.put(d.toJSON());

					}
				}else{
				k.put(d.key, d.toJSON());

				
				}
			}
			
			if (k.length() >0) {
				
			
			arr.put(k);
			}
			if (arr.length()>1) {
				
			
			
			return arr;
			}else{
				return k;
			}
		}
		else{
			return (String) this.getValue();
		}

	}
	public static Dico fromString(String[] strings) {
		return new Dico(strings[0],strings[1]);
	}
	public static List<Dico> fromString(String[]...strings) {
		List<Dico> d = new ArrayList<Dico>();
		for (int i = 0; i < strings.length; i++) {
			d.add(fromString(strings[i]));
		}
		return d;
	}
	public static List<Dico> ps(Parameters pa,String...strings) {
		List<Dico> d = new ArrayList<Dico>();
		for (int i = 0; i < strings.length; i++) {
			for (String dico : pa.getValues(strings[i])) {
				
			
			d.add(fromString(new String[]{strings[i],dico}));
			}
		}
		return d;
	}
	public static Parameters psp(Parameters pa,String...strings) {
		return pa.PS(strings);
	}
	
}
