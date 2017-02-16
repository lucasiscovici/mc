package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import services.utils.Service;

/**
 * Classe Dico
 */

public class Dico {
	String key;
	String value = null;
	List<String> values = null;
	public List<Dico> valuesd = null;
	public boolean false_key = false;
	
	/**
	 * Constructeur Dico(Object o, Object o1)
	 * @param o Un objet
	 * @param o1 Un objet
	 */
	
	public Dico(Object o, Object o1){
		
	}
	
	/**
	 * 
	 * @param k Une chaine de caractère
	 * @return Notre dico
	 */
	
	public Dico kill(String k) {
		Iterator<Dico> d = valuesd.iterator();
		while (d .hasNext()) {
			Dico dd = d.next();
			if(dd.key==k) {
				d.remove();
			}
			else if (dd.is_dic) {
				dd.kill(k);
			}
			
		}
		return this;
	}
	
	/**
	 * 
	 * @return Parameters.fromDico(d)
	 */
	
	public Parameters valuesdP() {
		List<Dico> d = this.copy().valuesd;
		return Parameters.fromDico(d);
	}
	public boolean is_dicd = false;

	public boolean is_dic = false;
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @param value Une chaine de caractère
	 */
	
	public Dico(String key, String value) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.value = value;
	}
	
	/**
	 * 
	 * @return key.equals("null") && value.equals("null")
	 */
	
	public boolean is_null() {
		return key.equals("null") && value.equals("null");
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @param values Une liste d'objet quelconque
	 */
	
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
	
	/**
	 * 
	 * @return la copy du dico
	 */

	public Dico copy() {
		if (is_dicd){
			List<Dico> d = new ArrayList<Dico>();
			for (Dico dico : valuesd) {
					d.add(dico.copy());
			}
			Dico dd =  Dico.kvsd(key, d);
			dd.false_key = this.false_key;
			return dd;
		}
		else{
			return Dico.kv(key, value);
		}
	}
	
	/**
	 * 
	 * @param value Une chaine de caractère
	 */
	
	public Dico(String value) {
		this.key = Usefull.uniqueID();
		this.value = value;
		this.false_key = true;
	}
	public int countD = 0;
	
	/**
	 * 
	 * @param s Une chaine de caractère
	 * @param g Une chaine de caractère
	 * @return Notre Dico avec une clé et une valeur en plus ajoute dans la list<Dico>
	 */
	
	public Dico addD(String s,String g) {
		
		this.is_dicd = true;
		if (this.valuesd==null) {
			this.valuesd = new ArrayList<Dico>();
			this.false_key = true;
			this.setKey(countD);
			
		}
		this.false_key = true;
		this.valuesd.add(Dico.kv(s, g));
		this.is_dicd = true;
		return this;
	}
	
	/**
	 * 
	 * @param g Un Dico
	 * @return Notre Dico avec un Dico en plus (List<Dico>)
	 */
	
	public Dico addD(Dico g) {
		
		this.is_dicd = true;
		if (this.valuesd==null) {
			this.valuesd = new ArrayList<Dico>();
			this.setKey(countD);
			
		}
		this.valuesd.add(g);
		this.is_dicd = true;
		return this;
	}
	
	/**
	 * 
	 * @param g Un paramètre
	 * @return Notre dico avec ajout des Dico de la List<Dico> du PArameters g
	 */

	public Dico addD(Parameters g) {
		
		this.is_dicd = true;
		if (this.valuesd==null) {
			this.valuesd = new ArrayList<Dico>();
			this.setKey(countD);
			
		}
		this.valuesd.addAll(g.parameters);
		this.is_dicd = true;
		return this;
	}
	
	/**
	 * 
	 * @param ob Un objet
	 * @return Notre Dico avec modification de notre key
	 */
	
	public Dico setKey(Object ob) {
		this.key = ob.toString();
		return this;
	}
	
	/**
	 * fv -> from values
	 * @param values Un ensemble de chaine de caractère
	 * @return Un nouveau paramètre
	 */
	
	public static Parameters fv(String...values) {
		Parameters p = new Parameters();
		for (String string : values) {
			if (string.length() > 0) {
				
			
			p.AddParam(Dico.v(string));
			}
		}
		return p;
	}
	
	/**
	 * prend depuis Local_params les keys 
	 * @param ob Un service
	 * @param key Une chaine de caractère
	 * @return Un nouveau Dico
	 */
	
	public static Dico vT(Service ob,String key) {
		Dico d = ob.Local_params.getDico(key).copy();
		//io.print(ob.Local_params.getDico(key));
		return d;
	}
	
	/**
	 * prend depuis Local_params les keys 
	 * @param ob Un service
	 * @param key Une chaine de caractère
	 * @return Parameters.fromDicos(vT(ob, key))
	 */
	
	public static Parameters vT_toP(Service ob,String key) {
		return Parameters.fromDicos(vT(ob, key));
	}
	
	/**
	 * prend depuis Local_params les keys 
	 * @param ob Un service
	 * @param keys Un ensemble de chaine de caractère
	 * @return Dico.psp(ob.Local_params, keys)
	 */
	
	public static Parameters vT_toP(Service ob,String...keys) {
		return  Dico.psp(ob.Local_params, keys);
	}
	
	/**
	 * 
	 * @param values Un ensemble d'objet (String,parameters, Int, double, etc...Dico)
	 * @return Un nouveau paramètre
	 * @throws LucasException
	 */
	
	public static Parameters toP(Object...values) throws LucasException {
		Parameters p = new Parameters();
		
		if (values.length%2!=0) {
			 throw new LucasException("toP key,value,key2,value2,etc.... nb de parametres paires");
		}
		for (int i = 0; i < values.length; i++) {
			if (i%2==0) {
				if (values[i+1] instanceof String || values[i+1] instanceof Integer || values[i+1] instanceof Double || values[i+1] instanceof Boolean) {
					
					if(((String) values[i]).substring(((String) values[i]).length()-2, ((String) values[i]).length()).equals("[]")){
						p.AddParam(((String) values[i]).substring(0, ((String) values[i]).length()-2).toString(), values[i+1].toString());

					}else{
						p.AddParam(values[i].toString(), values[i+1].toString());

					}
				}else if(values[i+1] instanceof Parameters){
					p.AddParam((String) values[i],((Parameters) values[i+1]));

				}else if(values[i+1] instanceof Dico){
					p.AddParam( (String) values[i].toString(),((Dico) values[i+1]));

				}
			}
			
		}
	
		return p;
	}
	
	/**
	 * 
	 * @return Parameters.fromDicos(this)
	 */
	
	public Parameters toPa() {
		return Parameters.fromDicos(this);
	}
	
	/**
	 * 
	 * @param ob Un service
	 * @param keys Un ensemble de chaine de caractère
	 * @return Dico.ps(ob.Local_params.PS(keys))
	 */
	
	public static List<Dico> vsT(Service ob,String...keys) {
		return Dico.ps(ob.Local_params.PS(keys));
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @param values Une liste de chaine de caractère
	 * @return new Dico(key,values)
	 */
	
	public static Dico kvs(String key, List<String> values) {
		return new Dico(key,values);
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @param dicos Une liste de Dico
	 * @return new Dico(key,dicos)
	 */
	
	public static Dico kvsd(String key, List<Dico> dicos) {
		return new Dico(key,dicos);
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @param value Une chaine de caractère
	 * @return new Dico(key,value)
	 */
	
	public static Dico kv(String key, String value) {
		return new Dico(key,value);
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @param value Une valeur
	 * @return new Dico(key,""+value)
	 */
	
	public static Dico kv(String key, int value) {
		return new Dico(key,""+value);
	}
	
	/**
	 * 
	 * @param value Une chaine de caractère
	 * @return new Dico(value)
	 */
	
	public static Dico v(String value) {
		return new Dico(value);
	}
	
	/**
	 * 
	 * @param value Une chaine de caractère
	 * @return new Dico(value,"")
	 */
	
	public static Dico k(String value) {
		return new Dico(value,"");
	}
	
	/**
	 * 
	 * @param values Un ensemble de chaine de caractère
	 * @return un nouveau paramètre
	 */
	
	public static Parameters ks(String...values) {
		Parameters p = new Parameters();
		
		for (String string : values) {
	
			p.AddParam(Dico.k(string));
		}
		return p;
	}
	
	/**
	 * 
	 * @param value Une valeur
	 * @return new Dico(""+value)
	 */
	
	public static Dico v(int value) {
		return new Dico(""+value);
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @param values Un ensemble de chaine de caractère
	 */
	
	public Dico(String key, String[] values) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.values = Arrays.asList(values);
		this.is_dic = true;
	}
	
	/**
	 * Constructeur Dico()
	 */
	
	public Dico() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return Notre clé
	 */
	
	public String getKey() {
		return key;
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @return Notre Dico
	 */
	
	public Dico setKey(String key) {
		this.key = key;
		return this;
	}
	
	/**
	 * 
	 * @param value Une chaine de caractère
	 */
	
	public void setValue(String value) {
		if (value.length() > 0) {
			
		
		this.value = value;
		}
	}
	
	/**
	 * 
	 * @param values Un ensemble de chaine de caractère
	 * @return values
	 */
	
	public static String[] vs_a(String...values) {
		return values;
	}

	public static String[] combine(String v1,String...values) {
		List<String> d = new ArrayList<String>(Arrays.asList(values));
		List<String> d2= new ArrayList<String>(Arrays.asList(v1));
		d2.addAll(d);
		return d2.toArray(new String[0]);
	}
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @param values Un ensemble de chaine de caractère
	 * @return Un Dico
	 */
	
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
	
	/**
	 * 
	 * @param values Un ensemble de chaine de caractère
	 */
	
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
	
	/**
	 * 
	 * @return true si c'est un Dico, false sinon
	 */
	
	public boolean is_Dico() {
		return is_dic == true;
	}
	
	/**
	 * 
	 * @return notre valeur ou une chaine null
	 */
	
	public String getValue() {
		if (this.value == null) {
			return "";
		}
		return value;
	}
	
	/**
	 * 
	 * @return Une ArrayList<String> vide ou contenant nos valeurs
	 */
	
	public List<String> getValues() {
		if (this.values == null) {
			return new ArrayList<String>();
		}
		return values;
	}
	
	/**
	 * Affiche nos valeurs de notre Dico
	 */
	
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
			d+=this.value+"\n";
		}
		return d;
	}
	
	/**
	 * 
	 * @return Un JSONArray ou un JSONObject
	 * @throws JSONException
	 */
	
	public Object toJSON() throws JSONException {
		//io.print(this.is_dicd);

		if(this.is_dicd) {
			JSONArray arr = new JSONArray();
			JSONObject k = new JSONObject();
			
			for (Dico d : this.valuesd) {
			//io.print(d.false_key);
				if (d.false_key) {
					Object dff = d.toJSON();
					//io.print("e");
					if (dff instanceof JSONArray) {
						arr.put( ((JSONArray) d.toJSON()).get(0));

					}
					else{
						arr.put(dff);

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
				if (arr.length() >0) {
			
				
				return arr.get(0);
				}else{
					return k;
				}
			}
		}
		else{
			return (String) this.getValue();
		}

	}
	
	/**
	 * 
	 * @param strings Un ensemble de chaine de caractère
	 * @return new Dico(strings[0],strings[1])
	 */
	
	public static Dico fromString(String[] strings) {
		return new Dico(strings[0],strings[1]);
	}
	
	/**
	 * 
	 * @param strings Un ensemble de chaine de caractère
	 * @return Une ArrayList<Dico>
	 */
	
	public static List<Dico> fromString(String[]...strings) {
		List<Dico> d = new ArrayList<Dico>();
		for (int i = 0; i < strings.length; i++) {
			d.add(fromString(strings[i]));
		}
		return d;
	}
	
	/**
	 * 
	 * @param pa Un paramètre
	 * @param strings Un ensemble de chaine de caractère
	 * @return Une ArrayList<Dico>
	 */
	
	public static List<Dico> ps(Parameters pa,String...strings) {
		List<Dico> d = new ArrayList<Dico>();
		
		for (int i = 0; i < strings.length; i++) {
			for (Dico dico : pa.getDicos(strings[i]).parameters) {
				
					d.add(dico.copy());
			}
		}
		return d;
	}
	
	/**
	 * 
	 * @param pa Un paramètre
	 * @param strings Un ensemble de chaine de caractère
	 * @return Une ArrayList<Dico>
	 */
	
	public static List<Dico> psn(Parameters pa,String...strings) {
		List<Dico> d = new ArrayList<Dico>();
		List<String> s = new ArrayList<String>(Arrays.asList(strings));
		for (Dico dico : pa.parameters) {
			if (!s.contains(dico.key)) {
				d.add(dico.copy());
			}
		}
		
		return d;
	}
	
	/**
	 * 
	 * @param pa Un paramètre
	 * @param strings Un ensemble de chaine de caractère
	 * @return pa.PS(strings)
	 */
	
	public static Parameters psp(Parameters pa,String...strings) {
		return pa.PS(strings);
	}

	/** Dico.vT_toP(sAddLike, "response");
	 * @param sAddLike
	 * @return Dico.vT_toP(sAddLike, "response");
	 */
	public static Parameters response(Service sAddLike) {
		// TODO Auto-generated method stub
		return Dico.vT_toP(sAddLike, "response");
	}

	/** Dico.vs_a(string+"key");
	 * @param string
	 * @return Dico.vs_a(string+"key");
	 */
	public static String[] vs_ak(String...string) {
		// TODO Auto-generated method stub
		
		return Dico.combine("key",string);
	}
	
}
