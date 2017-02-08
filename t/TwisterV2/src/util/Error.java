package util;

import org.json.JSONException;
import org.json.JSONObject;

public enum Error {
	  ErrArgs(-1, "Args Error"),
	  NAUTH(401, "Auth Error"),
	  SqlError(1000, "BD Error"),
	  JavaError(10000, "Java Error"),
	  JsonError(100, "pb JSON");

	  private final int code;
	  private String description;

	  Error(int code, String description) {
	    this.code = code;
	    this.description = description;
	  }

	  public String getDescription() {
	     return description;
	  }
      
	  public int getCode() {
	     return code;
	  }
	  public void setDescription(String d){
		  this.description = d;
	  }
	  @Override
	  public String toString() {
	    return code + ": " + description;
	  }
	 public static void main(String[] args) {
		  
		 //Error.DATABASE.setDescription("pb database").toJSON();
				  
	} 
	 public String to_JSON() throws JSONException {
		return new JSONObject().put("code", this.code).put("description", this.description).toString();
	}
	  
	}
