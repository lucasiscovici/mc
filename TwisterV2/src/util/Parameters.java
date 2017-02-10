package util;

import java.util.Arrays;  
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.DBObject;

public class Parameters {
	public List<Dico> parameters;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Dico getDico(String key) {
		for (Dico dico : parameters) {
			if (dico.key.equals(key)) {
				return dico;
			}
		}
		return new Dico("null","null");
	}
	public List<Dico> getDicos(String...key) {
		List<Dico> d = new ArrayList<Dico>();

		 List<String> ds = new ArrayList<String>(Arrays.asList(key));
for (Dico dico : parameters) {
	if (ds.contains(dico.key)) {
		d.add(dico);
	}
}
		
		return d;
	}

	public Parameters copy() {
		List<Dico> d = new ArrayList<Dico>();
		for (Dico dico : parameters) {
			d.add(dico.copy());
		}
		return Parameters.fromDico(d);
	}
	public Parameters change(String key1, final String nk) throws LucasException {
		for (Dico dico : parameters) {
			if (dico.key==nk) {
				throw new LucasException("Parameters: change(String key1,String nk) ->  nk deja present danns les parameters");

			}
		}
		for (Dico dico : parameters) {
			if (dico.false_key) {
				for (Dico dico2 : dico.valuesd) {
					if (dico2.key.equals(key1)) {
						dico2.key=nk;
					}
				}
			}else{
			if (dico.key.equals(key1)) {
				dico.key=nk;
			}
			}
		}
		return this;
	}
	public Parameters change_newk(String key1, final String nk) throws LucasException {
		for (Dico dico : parameters) {
			if (dico.key.equals(nk)) {
				throw new LucasException("Parameters: change(String key1,String nk) ->  nk deja present danns les parameters");

			}
		}
		Parameters df = this.getDicos(key1).copy(); 
		for (Dico dico : df.parameters) {
			dico.setKey(nk);
		}
		this.AddParam(df);
		return this;
	}
	public Parameters getDicos(String key) {
		Parameters p = new Parameters();
		for (Dico dico : parameters) {
			if (dico.false_key==true) {
				for (Dico dico2 : dico.valuesd) {
					if (dico2.key.equals(key)) {
						p.AddParam(dico2);
					}
				}
			}else{
			if (dico.key.equals(key)) {
				p.AddParam(dico);
			}
			}
		}
		return p;
	}
	public List<String> getOnlyValues() {
		List<String> liste = new ArrayList<String>();
		for (Dico dico : parameters) {
			if (!dico.is_Dico()) {
				liste.add(dico.value);
			}
		}
		return liste;
	}
	public List<String> getOnlyKeys() {
		List<String> liste = new ArrayList<String>();
		for (Dico dico : parameters) {
			liste.add(dico.getKey());
		}
		return liste;
	}
	public String getValue(String key) {
			return getDico(key).getValue();
	}
	public Integer getValueInt(String key) {
		Dico c =getDico(key);
		if (c==null || c.getValue() == "null") {
			return null;
		}else{
			return Integer.parseInt(c.getValue());

		}
				
}
	public List<String> getValuesk(String key) {
		return getDicos(key).getOnlyValues();
	}
	public List<String> getValues(String key) {
		return getDicos(key).getOnlyValues();
}
	
	public Parameters(HttpServletRequest req) {
		parameters = new ArrayList<Dico>();
		@SuppressWarnings("unchecked")
		Enumeration<String> parameterNames = req.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			Dico d = new Dico();
			String paramName = parameterNames.nextElement();
			d.setKey(paramName);
			String[] paramValues = req.getParameterValues(paramName);
			if (paramValues.length > 1) {
				d.setValues(paramValues);
				this.parameters.add(d);

			}else{
				
				if (paramValues[0].length() > 0) {
					
				d.value=paramValues[0];
				this.parameters.add(d);

				}
			}
		}
	
	}
	public static Parameters req(HttpServletRequest req) {
		return new Parameters(req);
	}
	public Parameters() {
		this.parameters = new ArrayList<Dico>();
				}
	public Parameters(List<Dico> l_dico) {
		this.parameters = l_dico;
	}
	public Parameters(Dico... values){
		this.parameters = new ArrayList<Dico>();
		for (int i = 0; i < values.length; i++) {
			this.AddParam(values[i]);
		}
	}
	public static Parameters fromDicos(Dico... values) {
		return new Parameters(values);
	}
	public Parameters AddParam(Dico dico){
//		if (dico.getValue().length() > 0) {
		this.parameters.add(dico);
//		}
		return this;
	}
	public int co = 0;
	public Parameters AddParam(DBObject db){
//		if (dico.getValue().length() > 0) {
		Dico d = new Dico();
		d.is_dicd = true;
		d.false_key = true;
		d.setKey(co);
		d.countD = co;
//io.print(co);
		for (String key : db.keySet()) {
			if (key.charAt(0)!='_') {
				
			
			d.addD(key, db.get(key).toString());
			}
		}
		this.AddParam(d);
//		}
		return this;
	}
	public Parameters AddParam(Parameters p, String key){
//		if (dico.getValue().length() > 0) {
		this.parameters.add(p.getDico(key));
//		}
		return this;
	}
	public Parameters AddParam(Object...keys) throws LucasException{
//		if (dico.getValue().length() > 0) {
		this.parameters.addAll(Dico.toP(keys).parameters);
//		}
		return this;
	}
	public Parameters AddParam(Parameters p, String...keys){
//		if (dico.getValue().length() > 0) {
		this.parameters.addAll(p.getDicos(keys));
//		}
		return this;
	}
	public Parameters AddParam(String key,Object id){

		return AddParam(Dico.kv(key, id.toString()));
	}
	public Parameters AddParam(String key,Parameters id){

		return AddParam(Dico.kvsd(key, id.parameters));
	}
	public boolean CheckIfErrParams(String[] getEntry) {
		List<String> l = Arrays.asList(getEntry);
		ArrayList<String> al = new ArrayList<String>(l);
		
		for (Dico dico : this.parameters) {
			if (al.contains(dico.key)) {
				al.remove(al.indexOf(dico.key));
			}
		}
		if (al.size() > 0) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String d = "";
		for (Dico dico : parameters) {
			d += dico.toString();
		}
		return d;
	}
	public static Parameters fromDico(List<Dico> d) {
		return new Parameters(d);
	}
	public static Parameters fromDicoPS(Parameters p,String...strings) {
		return fromDico(Dico.ps(p, strings));
	}
	public Parameters PS(String...strings) {
		return fromDico(Dico.ps(this, strings));
	}
}