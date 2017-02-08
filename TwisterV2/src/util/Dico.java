package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dico {
	String key;
	String value = null;
	List<String> values = null;
	boolean is_dic = false;
	public Dico(String key, String value) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.value = value;
	}
	public Dico(String value) {
		// TODO Auto-generated constructor stub
		this.key = io.uniqueID();
		this.value = value;
	}
	public static Parameters fv(String...values) {
		Parameters p = new Parameters();
		for (String string : values) {
			p.AddParam(Dico.v(string));
		}
		return p;
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
	public void setKey(String key) {
		this.key = key;
	}
	public void setValue(String value) {
		if (value.length() > 0) {
			
		
		this.value = value;
		}
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
		// TODO Auto-generated method stub
		String d = ""+this.key+":  " ;
		if (this.is_Dico()) {
			for (String string : values) {
				d += string+" ";
			}
		}else{
			d+=this.value;
		}
		d+="\n";
		return d;
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
			d.add(fromString(new String[]{strings[i],pa.getValue(strings[i])}));
		}
		return d;
	}
}
