package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Classe Database 
 */

public class Database {
static Database database;
private DataSource dataSource;

/**
 * Constructeur Database
 * @param jndiname Une chaine de caractère
 * @throws SQLException
 */

public Database(String jndiname) throws SQLException {
try
{
dataSource = (DataSource) new InitialContext().lookup("java:comp/env/"+ jndiname);

}
catch
(NamingException e) {
// Handle error that it’s not configured in JNDI.

throw new SQLException(jndiname +" is missing in JNDI! : "+e.getMessage());

}

}

/**
 * 
 * @return Notre connexion SQL
 * @throws SQLException
 */

public Connection getConnection() throws SQLException {
return dataSource.getConnection();

}

/**
 * 
 * @return la connexion à MySql
 * @throws SQLException
 * @throws ClassNotFoundException
 */

public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");

	if (!DBStatic.mysql_pooling) {
		return (DriverManager.getConnection("jdbc:mysql://"+ DBStatic.mysql_host + "/" + DBStatic.mysql_db, DBStatic.mysql_username, DBStatic.mysql_password));
			
		}else
		{
			
			if (database==null) {
			database=new Database("jdbc/db");
			
			}
			
			return (database.getConnection());
			
			}
}
}
