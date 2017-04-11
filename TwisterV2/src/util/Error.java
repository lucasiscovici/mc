package util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Enumération des différentes erreurs possible
 */

public enum Error {
	
	  ErrArgs(-1, "Args Error"),
	  NAUTH(401, "Auth Error"),
	  SqlError(1000, "BD Error"),
	  JavaError(10000, "Java Error"),
	  JsonError(100, "pb JSON"),
	  //USER
		  //LOGIN
		  LoginExist(1,"User already exist with this login"),
		  LoginNotExist(1,"Unknown User"),
	  	  BadPassword(2,"Bad password"),
	  //MONGO
	  MongoError(200, "pb mango"),
	  //Catch Error
	  SQLException(30, "SQLException Error"),
	  UnknownHostException(31, "UnknownHostException Error"),
	  ClassNotFoundException(32, "ClassNotFoundException Error"),
	  JSONException(33, "JSONException Error"),
	  LucasException(34, "LucasException Error"),
	  IOException(35, "IOException Error"),
	  NumberFormatException(36, "NumberFormatException Error"),
	  ParseException(37, "ParseException Error");


	  private int code;
	  private String description;
	  private String description2 = "";
	  
	  /**
	   * Construit une erreur
	   * @param code Code d'erreur
	   * @param description La description du code d'erreur
	   */

	  Error(int code, String description) {
	    this.code = code;
	    this.description = description;
	  }
	  
	  /**
	   * 
	   * @return la description de l'erreur
	   */

	  public String getDescription() {
	     return toString();
	  }
	  
	  /**
	   * 
	   * @param d Une chaine de caractère
	   * @return Le détail de notre erreur
	   */
	  
	  public Error detail(String d) {
		this.description2 += " | Detail: "+d;
		  return this;
	  }
	  
	  /**
	   * 
	   * @param d Une chaine de caractère
	   * @param dd Un objet
	   * @return Notre erreur
	   */
	  
	  public Error detail(String d,Object dd) {
			this.description2 += " | Detail: "+d;
			this.depuis(dd);
			  return this;
		}
	  
	  /**
	   * 
	   * @param d Un objet
	   * @return l'emplacement de l'erreur
	   */
	  
	  public Error depuis(Object d) {
			  return this.depuis(d.getClass().getName());
		}
	  
	  /**
	   * 
	   * @param d Une chaine de caractère
	   * @return L'emplacement de l'erreur
	   */
	  
	  public Error depuis(String d) {
			this.description2 += " | Depuis: "+d;
			  return this;
		}
	  
	  /**
	   * 
	   * @param t Un objet
	   * @param d une chaine de caractère
	   * @return le détail de notre erreur
	   */
	  
	  public Error detailT(Object t,String d) {
			  return this.detail(d).depuis(t);
		}
	  
	  /**
	   * 
	   * @return notre code d'erreur
	   */
      
	  public int getCode() {
	     return code;
	  }
	  
	  /**
	   * 
	   * @param d Une chaine de caractère
	   * @return notre Erreur
	   */
	  
	  public Error setDescription(String d){
		  this.description = d;
		  return this;
	  }
	  
	  /**
	   * 
	   * @param d Une chaine de caractère
	   * @param t Un objet
	   * @return Notre erreur
	   */
	  
	  public Error setDescription(String d,Object t){
		  this.description = d;
		  this.detail(d).depuis(t);
		  return this;
	  }
	  
	  /**
	   * Retourne le détail de notre erreur
	   */
	  
	  @Override
	  public String toString() {
	    String dh = code + " : " + description + " " + description2;
	    description2 = "";
	    return dh;
	  }
	  
	  /**
	   * 
	   * @param args Liste d'argument
	   */
	  
	 public static void main(String[] args) {
		  
		 //Error.DATABASE.setDescription("pb database").toJSON();
				  
	} 
	 
	 /**
	  * 
	  * @return un JSONObject
	  * @throws JSONException
	  */
	 
	 public String to_JSON() throws JSONException {
		String d = new JSONObject().put("code", this.code).put("description", this.description+" "+this.description2).toString();
		 this.description2="";
		 return d;
	 }

	public Error setCode(int i) {
		// TODO Auto-generated method stub
		this.code=i;
		return this;
	}
	  
	}
