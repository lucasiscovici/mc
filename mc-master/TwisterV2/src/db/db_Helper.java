package db;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Dico;
import util.Parameters;
//import util.io;








import util.io;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class db_Helper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static Statement giveMeAnStatement() throws SQLException, ClassNotFoundException {
		return (Statement) Database.getMySQLConnection().createStatement();
	}
	public static List<String> getColumnsNames(ResultSet rs) throws SQLException {
		List<String> liste = new ArrayList<String>();
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		// The column count starts from 1
		for (int i = 1; i <= columnCount; i++ ) {
			liste.add(rsmd.getColumnName(i));
		}
		return liste;
	}
	private static int getRowCount(ResultSet resultSet) {
	    if (resultSet == null) {
	        return 0;
	    }
	    try {
	        resultSet.last();
	        return resultSet.getRow();
	    } catch (SQLException exp) {
	        exp.printStackTrace();
	    } finally {
	        try {
	            resultSet.beforeFirst();
	        } catch (SQLException exp) {
	            exp.printStackTrace();
	        }
	    }
	    return 0;
	}
	public static Parameters select(String query) throws SQLException, ClassNotFoundException {
		List<Dico> liste = new ArrayList<Dico>();
		//io.print(query);
		Statement s =  giveMeAnStatement();
		//io.print(query);
		ResultSet r = s.executeQuery(query);
		List<String> columns = getColumnsNames(r);
		
		int c = 0;
		int o = getRowCount(r);
		while (r.next()) {
			if (o==1) {
				for (String string : columns) {
					liste.add(Dico.kv(string, r.getString(string)));
				}
			}else{
			Dico l = new Dico();
			l.countD=c;
			for (String string : columns) {
				l.addD(string, r.getString(string));
			}
			liste.add(l);
			c++;
			}
		}
		r.close();
		s.close();	

		return new Parameters(liste);
	}
	public static String multipleAnd(Parameters dico) {
		String query = "";
		for (Dico dico2 : dico.parameters) {
			query+=" `"+dico2.getKey()+"` = "+"\""+dico2.getValue()+"\" AND";
		}
		query = query.substring(0, query.length() - 3);
		return query;
	}
	public static String where(Parameters dico) {
		if (dico==null) {
			return "";
		}
		return " WHERE "+multipleAnd(dico);
	}
	public static Parameters select(String query,Parameters dico) throws SQLException, ClassNotFoundException {
		query += where(dico);
		return select(query);
	}
	public static Parameters selectAndWhere(String query,Parameters dico) throws SQLException, ClassNotFoundException {
		return select(query, dico);
	}


	public static Parameters selectAndWhere(String select,String table,Parameters dico) throws SQLException, ClassNotFoundException {
		String query = CreateSelectFrom(Dico.fv(select), table);
		return select(query, dico);
	}
	public static Integer selectAndWhereID(String table,Parameters dico) throws SQLException, ClassNotFoundException {
		String query = CreateSelectFrom(Dico.fv("id"), table);
		return select(query, dico).getValueInt("id");
	}

	public static Parameters selectAndWhere(String table,Parameters dico,String...selects) throws SQLException, ClassNotFoundException {
		String query = CreateSelectFrom(Dico.fv(selects), table);
		return select(query, dico);
	}
	public static String CreateSelectFrom(Parameters param,String table) {
		String query = "SELECT ";
		for (Dico dico: param.parameters) {
			query += " "+dico.getValue()+",";
		}
		query = query.substring(0, query.length() - 1);
		query+= " FROM "+table+" ";
		return query;
	}
	public static int selectAndWhere_Count(String table,Parameters dico) throws SQLException, ClassNotFoundException {
		String query = "SELECT COUNT(`id`) as \"count\" FROM "+table+"";
		//io.print(dico);
		Parameters result = selectAndWhere(query, dico);
		//io.print(result);
		return result.getValueInt("count");
	}

	public static int insert(String query) throws SQLException, ClassNotFoundException {
		//io.print(query);
		Statement s = giveMeAnStatement();
		return s.executeUpdate(query);
	}

	public static String stringMe(List<String> d) {
		String queryn = "";
		for (String string : d) {
			queryn+=" \""+string+"\",";
		}
		queryn = queryn.substring(0,queryn.length()-1);
		return queryn;
	}
	public static String keysMe(List<String> d) {
		String queryn = "";
		for (String string : d) {
			queryn+=" `"+string+"`,";
		}
		queryn = queryn.substring(0,queryn.length()-1);
		return queryn;
	}
	public static int insertValues(String query,List<String> d) throws SQLException, ClassNotFoundException {
		query += " VALUES( ";
		query +=stringMe(d); 
		query += ")";
		//io.print(query);
		Statement s = giveMeAnStatement();
		return s.executeUpdate(query);
	}
	public static int insert(String table,Parameters d) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO "+table+"(";
		query += keysMe(d.getOnlyKeys())+")";
		return insertValues(query, d.getOnlyValues());
	}
	public static int delete(String table,Parameters d) throws SQLException, ClassNotFoundException {
		String query = "DELETE FROM "+table+"";
		query += where(d);
		Statement s = giveMeAnStatement();
		return s.executeUpdate(query);	
	}
	public static boolean insertOK(String table,Parameters d) throws SQLException, ClassNotFoundException {
		return insert(table,d) > 0;
	}
	public static boolean deleteOK(String table,Parameters d) throws SQLException, ClassNotFoundException {
		return delete(table,d) > 0;
	}
	public static boolean selectOK(String table,Parameters d) throws SQLException, ClassNotFoundException {
		return selectAndWhere_Count(table, d) > 0;
	}
	public static Mongo getMyMongo() throws UnknownHostException {
		return new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
		
	}
	public static DB getMyDB() throws UnknownHostException {
		return getMyMongo().getDB(DBStatic.mongo_db);
	}
	public static DBCollection getMyCollection(String table) throws UnknownHostException {
		return getMyDB().getCollection(table);
	}
	public static BasicDBObject CreateRequest() {
		return new BasicDBObject();
	}
	public static int insertMongo(String table,Parameters p) throws UnknownHostException {
		BasicDBObject r= CreateRequest();
		for (Dico d : p.parameters) {
			r.put(d.getKey(), d.getValue());
		}
		return getMyCollection(table).insert(r).getN();
	}
	public static boolean insertMongoOK(String table,Parameters p) throws UnknownHostException {
		return insertMongo(table,p) > 0;

	}
	public static void whereMongo(BasicDBObject r, Parameters p) {
		if (p==null) {
			return;
		}
		for (Dico d : p.parameters) {
			r.put(d.getKey(), d.getValue());
		}
	}
	
	public static Parameters selectMongo(String table, Parameters p) throws UnknownHostException {
		DBCollection dc = getMyCollection(table);
		BasicDBObject r = CreateRequest();
		whereMongo(r, p);
		DBCursor dcu = dc.find(r);
		Parameters pn = new Parameters();
		int c = 0;
		while (dcu.hasNext()) {
			DBObject dbObject = (DBObject) dcu.next();
			io.print(dbObject);
			pn.AddParam(dbObject);
		}
		return pn;
	}
}
