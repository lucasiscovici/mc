package db;

/**
 * Interface DBStatic
 */

public interface DBStatic {
	public static String mysql_host = "li328.lip6.fr:33306"; 
	public static String mysql_db = "gr3_isco_goug"; 
	public static String mysql_username = mysql_db; 
	public static String mysql_password = "iscogoug"; 
	public static boolean mysql_pooling = true;
	
	public static String mongo_host = "li328.lip6.fr";
	public static int mongo_port = 27130;
	public static String mongo_db = "gr3_isco_goug"; 
	
	
}
