package util;

import java.util.Arrays;  
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.DBObject;

/**
 * Classe pour nos paramètres
 */

public class Parameters {
	public List<Dico> parameters;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * @param k Une chaine de caractère
	 * @return notre paramètre
	 */
	
	public Parameters kill(String k) {
		Iterator<Dico> d = parameters.iterator();
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
	 * @param k Une chaine de caractère
	 * @param enfants Un boolean
	 * @return notre paramètre
	 */
	
	public Parameters kill(String k,boolean enfants) {
		Iterator<Dico> d = parameters.iterator();
	while (d .hasNext()) {
		Dico dd = d.next();
		if(dd.key==k) {
			d.remove();
		}
		else if (dd.is_dicd && enfants) {
			dd.kill(k);
		}
		
	}
	return this;
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @return un Dico
	 */
	public Dico getDico(String key) {
		for (Dico dico : parameters) {
			if (dico.key.equals(key)) {
				return dico;
			}
			if (dico.is_dicd) {
				return dico.valuesdP().getDico(key);
			}
			
		}
		return new Dico("null","null");
	}
	
	/**
	 * 
	 * @param key Un ensemble de chaine de caractère
	 * @return Une liste de Dico
	 */
	
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
	
	/**
	 * 
	 * @return Une copie de notre paramètre
	 */

	public Parameters copy() {
		List<Dico> d = new ArrayList<Dico>();
		for (Dico dico : parameters) {
			d.add(dico.copy());
		}
		return Parameters.fromDico(d);
	}
	
	/**
	 * 
	 * @param key1 Une chiane de caractère
	 * @param nk Une chaine de caractère
	 * @return Notre paramètre modifié
	 * @throws LucasException
	 */
	
	public Parameters change(String key1, final String nk) throws LucasException {
		for (Dico dico : parameters) {
			if (dico.key==nk) {
				throw new LucasException("Parameters: change(String key1,String nk) ->  nk deja present danns les parameters");

			}
		}
		for (Dico dico : parameters) {
			if (dico.false_key || dico.is_dicd) {
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
	
	/**
	 * 
	 * @param key1 Une chaine de caractère
	 * @param nk Une chaine de caractère
	 * @return Notre paramètre modifié
	 * @throws LucasException
	 */
	
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
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @return Notre paramètre
	 */
	
	public Parameters getDicos(String key) {
		Parameters p = new Parameters();
		for (Dico dico : parameters) {
			
			if (dico.false_key==true &&  dico.is_dicd) {
				p.AddParam(dico.toPa().getDicos(key));
			}else{
			if (dico.key.equals(key)) {
				p.AddParam(dico);
			}
			}
		}
		
		return p;
	}
	
	/**
	 * 
	 * @return l'ensemble des valeurs de nos paramètres
	 */
	
	public List<String> getOnlyValues() {
		List<String> liste = new ArrayList<String>();
		for (Dico dico : parameters) {
			if (!dico.is_Dico()) {
				liste.add(dico.value);
			}
		}
		return liste;
	}
	
	/**
	 * 
	 * @return l'ensemble des clés de nos paramètres
	 */
	
	public List<String> getOnlyKeys() {
		List<String> liste = new ArrayList<String>();
		for (Dico dico : parameters) {
			liste.add(dico.getKey());
		}
		return liste;
	}
	
	/**
	 * 
	 * @param key Une chaine de caractères
	 * @return La veleur de notre paramètre
	 */
	
	public String getValue(String key) {
			return getDico(key).getValue();
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @return Le numéro correspondant à key
	 */
	
	public Integer getValueInt(String key) {
		Dico c =getDico(key);
		if (c==null || c.getValue() == "null") {
			return null;
		}else{
			return Integer.parseInt(c.getValue());

		}
				
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @return Une liste de Chaine de caractère d'un Dico correspondant à key
	 */
	
	public List<String> getValuesk(String key) {
		return getDicos(key).getOnlyValues();
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @return Une liste de chaine de caractère dans un objet dico correspondant à key
	 */
	
	public List<String> getValues(String key) {
		return getDicos(key).getOnlyValues();
	}
	
	/**
	 * 
	 * @param req Une requête
	 */
	
	public Parameters(HttpServletRequest req) {
		parameters = new ArrayList<Dico>();
		@SuppressWarnings("unchecked")
		Enumeration<String> parameterNames = req.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			Dico d = new Dico();
			String paramName = parameterNames.nextElement();
			if (paramName.substring(paramName.length()-2, paramName.length()).equals("[]")) {
				d.setKey(paramName.substring(0,paramName.length()-2));

			}else{
			d.setKey(paramName);
			}
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
	
	/**
	 * 
	 * @param req Une requête
	 * @return Un paramètre
	 */
	
	public static Parameters req(HttpServletRequest req) {
		return new Parameters(req);
	}
	
	/**
	 * Constructeur Parameters()
	 */
	
	public Parameters() {
		this.parameters = new ArrayList<Dico>();
	}
	
	/**
	 * 
	 * @param l_dico Liste de dico
	 */
	
	public Parameters(List<Dico> l_dico) {
		this.parameters = l_dico;
	}
	
	/**
	 * 
	 * @param values Liste de valeur d'un Dico
	 */
	
	public Parameters(Dico... values){
		this.parameters = new ArrayList<Dico>();
		for (int i = 0; i < values.length; i++) {
			this.AddParam(values[i]);
		}
	}
	
	/**
	 * 
	 * @param values Un ensemble de Dico
	 * @return new Parameters(values)
	 */
	
	public static Parameters fromDicos(Dico... values) {
		return new Parameters(values);
	}
	
	/**
	 * 
	 * @param dico Un Dico
	 * @return Notre paramètre
	 */
	
	public Parameters AddParam(Dico dico){
//		if (dico.getValue().length() > 0) {
	
		this.parameters.add(dico);
//		}
		return this;
	}
	public int co = 0;
	
	/**
	 * 
	 * @param db Un DBObject
	 * @return Notre paramètre
	 */
	
	public Parameters AddParam(DBObject db){
//		if (dico.getValue().length() > 0) {
		Dico d = new Dico();
		d.is_dicd = true;
		d.false_key = true;
		d.setKey(co);
		d.countD = co;
//io.print(co);
		for (String key : db.keySet()) {
			String keyo = key;
			if (key.charAt(0)=='_') {
				keyo = key.substring(1, key.length());
			}
			d.addD(keyo, db.get(key).toString());
			
			
		}
		//io.print(d.false_key);
		this.AddParam(d);
//		}
		return this;
	}
	
	/**
	 * 
	 * @param p Un paramètre
	 * @param key Une chaine de caractère
	 * @return Notre paramètre
	 */
	
	public Parameters AddParam(Parameters p, String key){
//		if (dico.getValue().length() > 0) {
		this.parameters.add(p.getDico(key));
//		}
		return this;
	}
	
	/**
	 * 
	 * @param keys Un ensemble d'objet
	 * @return Notre paramètre
	 * @throws LucasException
	 */
	
	public Parameters AddParam(Object...keys) throws LucasException{
//		if (dico.getValue().length() > 0) {
		this.parameters.addAll(Dico.toP(keys).parameters);
//		}
		return this;
	}
	
	/**
	 * 
	 * @param p Un paramètre
	 * @param keys Un ensemble de chaine de caractère
	 * @return Notre paramètre
	 */
	
	public Parameters AddParam(Parameters p, String...keys){
//		if (dico.getValue().length() > 0) {
		if (keys.length==0) {
			this.parameters.addAll(p.parameters);

		}else{
		this.parameters.addAll(p.getDicos(keys));
		}
//		}
		return this;
	}
	
	/**
	 * 
	 * @param keys Un ensemble de chaine de caractère
	 * @return this.AddParam(Dico.toP(keys[0],d))
	 * @throws LucasException
	 */

	public Parameters AddParamR(String...keys) throws LucasException{
		List<String> s = new ArrayList<String>(Arrays.asList(keys));
//		
		if (keys.length < 2) {
			throw new LucasException("AddParamR deux params min");
		}
		
		Parameters d = this.PS(s.subList(1,s.size()).toArray(new String[s.size()-1]));
		return this.AddParam(Dico.toP(keys[0],d));
	}
	
	/**
	 * 
	 * @param keys Un ensemble de chaine de caractère
	 * @return Notre paramètre
	 * @throws LucasException
	 */
	
	public Parameters AddParamRIn(String...keys) throws LucasException{
		List<String> s = new ArrayList<String>(Arrays.asList(keys));
		
		if (keys.length < 2) {
			throw new LucasException("AddParamRIN deux params min");
		}
		
		this.getDico(keys[0]).addD(this.PS(s.subList(1,s.size()).toArray(new String[s.size()-1])));
		return this;
	}
	
	/**
	 * ADD in local param the dico with key String[1] to a new DIco with the key String[0] 
	 * @param keys Un ensemble de chaine de caractère
	 * @return this.AddParam(Dico.toP(keys[0],d)).kill("messages",false)
	 * @throws LucasException
	 */
	
	public Parameters AddParamRIN(String...keys) throws LucasException{
		List<String> s = new ArrayList<String>(Arrays.asList(keys));
	
		if (keys.length < 2) {
			throw new LucasException("AddParamRIN deux params min");
		}
		Parameters d = this.PS(s.subList(1,s.size()).toArray(new String[0]));

		if (getDicosOK(keys[0])) {
			 getDico(keys[0]).addD(d);
			return this.kill(keys[1],false);
		}
		return this.AddParam(Dico.toP(keys[0],d)).kill(keys[1],false);

	}

	public Parameters AddParamResponse(String...keys) throws LucasException {
		
		return AddParamRIN(Dico.combine("response",keys));
		
		
	}
	/**
	 * Ajoute dans le parameters actuel la clé key et la valeur id en fonction de sont type
	 * @param key Un string
	 * @param id Un objet
	 * @return this
	 */
	public Parameters AddParam(String key,Object id){

		if (id instanceof Dico) {
			return this.AddParam(key, (Dico) id);
		}else if (id instanceof String) {
			return this.AddParam(key, (String) id);

		}else if (id instanceof Parameters) {
			return this.AddParam(key, (Parameters) id);

		}else{
			return this.AddParam(key, id.toString());
		}
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @param id Un dico
	 * @return un objet paramètres et ajoute des paramètres
	 */
	
	public Parameters AddParam(String key,Dico id){

		return AddParam(key,id.toPa());
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @param id Une chaine de caractère
	 * @return AddParam(Dico.kv(key, id)
	 */
	
	public Parameters AddParam(String key,String id){

		return AddParam(Dico.kv(key, id));
	}
	
	/**
	 * 
	 * @param key Une chaine de caractère
	 * @param id Un paramètres
	 * @return AddParam(Dico.kvsd(key, id.parameters))
	 */
	
	public Parameters AddParam(String key,Parameters id){

		return AddParam(Dico.kvsd(key, id.parameters));
	}
	
	/**
	 * CHECK si les getEntry{@link services.utils.Service#getEntry} obligatoire sont present dans les params {@link services.utils.Service#params}
	 * @param getEntry Liste de chaine de caractère
	 * @return true si erreur (les getEntry ne sont pas present dans les params), true sinon
	 */
	
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
	
	/**
	 * 
	 * @param a Une chaine de caractère
	 * @param b Une chaine de caractère
	 * @return Ajoute un paramètre
	 * @throws LucasException
	 */
	
	public Parameters cp(String a, String b) throws LucasException {
		Dico d = this.getDico(a);
		Dico d2 = this.getDico(b);
		if (!d2.is_null()) {
			throw new LucasException("Parameters cp vers b, clé deja dans params");
		}
		return this.AddParam(d.copy().setKey("b"));
	}
	
	/**
	 * Affiche nos paramètres
	 */
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String d = "";
		for (Dico dico : parameters) {
			d += dico.toString();
		}
		return d;
	}
	
	/**
	 * 
	 * @param d Une liste de dico
	 * @return un paramètre
	 */
	
	public static Parameters fromDico(List<Dico> d) {
		return new Parameters(d);
	}
	
	/**
	 * 
	 * @param p Un paramètre
	 * @param strings Des chaines de caractères
	 * @return fromDico(Dico.ps(p, strings))
	 */
	
	public static Parameters fromDicoPS(Parameters p,String...strings) {
		return fromDico(Dico.ps(p, strings));
	}
	
	/**
	 * Crée un nouveau {@link Parameters} avec comme données celle qui correspondent avec les noms de clés donnée en strings
	 * @param strings Des chaines de caractères
	 * @return fromDico(Dico.ps(this, strings))
	 */
	
	public Parameters PS(String...strings) {
		return fromDico(Dico.ps(this, strings));
	}
	
	/**
	 * 
	 * @param strings Des chaines de caractères
	 * @return  fromDico(Dico.psn(this, strings))
	 */
	
	public Parameters PSN(String...strings) {
		return fromDico(Dico.psn(this, strings));
	}

	/**
	 *Ajoute dans le parameters actuel une clé response et une valeur p {@link Parameters#AddParam(String, Object)}
	 * @param p
	 * @return
	 */
	public Parameters response(Object p) {
		// TODO Auto-generated method stub
		this.AddParam("response",p);
		return this;
	}

	/** 
	 * CHECK if exists params (dicos) with key string
	 * @param string
	 * @return true if exist dicos with key string
	 */
	public boolean getDicosOK(String string) {
		// TODO Auto-generated method stub
		return getDicos(string).parameters.size() > 0;
	}

	/**
	 * Ajoute dans le parameters actuel une clé response et une valeur "OK" {@link Parameters#response(Object)
	 * @return this
	 */
	public Parameters AddParamRespOK() {
		// TODO Auto-generated method stub
		this.response("OK");
		return this;
	}

	/**
	 * add in the actual parameters, key = "response" et value = params.getDico("id") {@link Parameters#getDico(String)}
	 * {@link Parameters#response(Object)}
	 * @param params
	 * @return this
	 */
	public Parameters responseID(Parameters params) {
		// TODO Auto-generated method stub
		//io.print(params);
		Dico id = params.getDico("id");
		this.response(id);
		return this;
	}

	/**
	 * add in actual parameters  string: friends  and add response: string {@link Parameters#AddParamResponse(String...)}
	 * @param string
	 * @param friends
	 * @return Parameters
	 * @throws LucasException
	 */
	public Parameters AddParamResponse(String string, Parameters friends) throws LucasException {
		// TODO Auto-generated method stub
		this.AddParam(string, friends);
		this.AddParamResponse(string);
		return this;
	}
}