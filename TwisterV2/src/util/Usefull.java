package util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Classe de méthode utile
 */

public class Usefull {
	 public static String uniqueID(){
		 return UUID.randomUUID().toString();
	 }
	 
	 /**
	  * 
	  * @return la date courante
	  */
	 
	 public static Date currentDate() {
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		return calendar.getTime();
	 }
}
