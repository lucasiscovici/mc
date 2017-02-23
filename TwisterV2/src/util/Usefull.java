package util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 public static Date addMinutesToDate(int minutes, Date beforeTime){
		    final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

		    long curTimeInMs = beforeTime.getTime();
		    Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
		    return afterAddingMins;
		}
	 /**
	     * Méthode utilitaire gérant la récupération de la valeur d'un cookie donné
	     * depuis la requête HTTP.
	     */
	    public static String getCookieValue( HttpServletRequest request, String nom ) {
	        Cookie[] cookies = request.getCookies();
	        if ( cookies != null ) {
	            for ( Cookie cookie : cookies ) {
	                if ( cookie != null && nom.equals( cookie.getName() ) ) {
	                    return cookie.getValue();
	                }
	            }
	        }
	        return null;
	    }
	    /*
	     * Méthode utilitaire gérant la création d'un cookie et son ajout à la
	     * réponse HTTP.
	     */
	    public static void setCookie( HttpServletResponse response, String nom, String valeur, int maxAge ) {
	        Cookie cookie = new Cookie( nom, valeur );
	        cookie.setMaxAge( maxAge );
	        response.addCookie( cookie );
	    }
	    public static void deleteCookie( HttpServletResponse response, String nom) {
	    	setCookie(response,nom,"",0);
	    }
}
