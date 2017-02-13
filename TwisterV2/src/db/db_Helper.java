package db;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Dico;
import util.LucasException;
import util.Parameters;
//import util.io;
import util.io;

//import util.io;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;
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
			l.false_key = true;
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
			if (dico2.is_dicd) {
				query+=multipleAnd(dico2);
			}else{
			query+=" `"+dico2.getKey()+"` = "+"\""+dico2.getValue()+"\" AND";
			}
		}
		query = query.substring(0, query.length() - 3);
		return query;
	}
	public static String multipleAnd(Dico dico) {
		String query = "";
		for (Dico dico2 : dico.valuesd) {
			if (dico2.is_dicd) {
				query+=multipleAnd(dico2);
			}else{
			query+=" `"+dico2.getKey()+"` = "+"\""+dico2.getValue()+"\" AND";
			}
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
	public static Parameters selectAndWhered(String query,Parameters dico) throws SQLException, ClassNotFoundException {
		return select(query, dico);
	}
	
	public static Parameters selectAndWhere(String table,Parameters dico) throws SQLException, ClassNotFoundException {
		return selectAndWhere(table, dico,"");
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
	public static String PrepareVirgule(Parameters param) {
		String query = "";
		 for (Dico dico: param.parameters) {
			query += " `"+dico.getValue()+"`,";
		}
		query = query.substring(0, query.length() - 1);
		return query;
	}
	public static String CreateSelectFrom(Parameters param,String table) {
		String query = "SELECT ";
		query+=PrepareVirgule(param);
		query+= " FROM "+table+" ";
		return query;
	}
	public static int update(String table,Parameters sets,Parameters where) throws LucasException, ClassNotFoundException, SQLException {
		String query = "UPDATE "+table;
		if (sets==null || sets.parameters.size()==0) {
			throw new LucasException("db_Helper sets pb update");
		}
		query+="SET ";
		query+=PrepareVirgule(sets);
		query+=where(where);
		Statement s = giveMeAnStatement();
		return s.executeUpdate(query);

	}
	public static boolean updateMongo(String table,Parameters sets,Parameters where) throws LucasException, ClassNotFoundException, SQLException, UnknownHostException {
		DBObject jo = CreateRequest();
		if (sets==null || sets.parameters.size()==0) {
			throw new LucasException("db_Helper sets pb updataMongo");
		}
		for (Dico d : sets.parameters) {
		jo.put(d.getKey(), d.getValue()); 
		}
		BasicDBObject r= CreateRequest();
		if (where != null) {
			
		
		for (Dico d : where.parameters) {
			r.put(d.getKey(), d.getValue());
			
		}
		}
		WriteResult w = getMyCollection(table).update(r,jo);
		return w.getError() != null;
	}
	public static boolean updateOK(String table,Parameters sets,Parameters where) throws SQLException, ClassNotFoundException, LucasException {
		return update(table,sets,where) > 0;
	}
	public static boolean updateMongoOK(String table,Parameters sets,Parameters where) throws SQLException, ClassNotFoundException, LucasException, UnknownHostException {
		return updateMongo(table,sets,where);
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
	public static int insertValues(String query,Parameters dd) throws SQLException, ClassNotFoundException {
		List<String> d = dd.getOnlyValues();
		query += " VALUES( ";
		query +=stringMe(d); 
		query += ")";
		//io.print(query);
		Statement s = giveMeAnStatement();
		int sf =  s.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = s.getGeneratedKeys();
        if (rs.next()){
            dd.AddParam("id", rs.getInt(1));
        }
        return sf;
	}
	public static int insert(String table,Parameters d) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO "+table+"(";
		query += keysMe(d.getOnlyKeys())+")";
		return insertValues(query, d);
	}
	public static int delete(String table,Parameters d) throws SQLException, ClassNotFoundException {
		String query = "DELETE FROM "+table+"";
		query += where(d);
		Statement s = giveMeAnStatement();
		//io.print(query);
		return s.executeUpdate(query);	
	}
	public static boolean selectMongoOK(String table,Parameters d) throws UnknownHostException {
		return selectMongo(table, d).parameters.size() > 0;

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
	
	public static boolean insertMongo(String table,Parameters p) throws UnknownHostException {
		BasicDBObject r= CreateRequest();
		for (Dico d : p.parameters) {
			r.put(d.getKey(), d.getValue());
		}
		 WriteResult w =  getMyCollection(table).insert(r);
		 String id = r.get( "_id" ).toString();
		 p.AddParam("id", id);
		 io.print(id);
		return w.getError()!=null;
	}
	public static boolean deleteMongo(String table,Parameters p) throws UnknownHostException {
		BasicDBObject r= CreateRequest();
		if (p!=null) {
			
		
		for (Dico d : p.parameters) {
			r.put(d.getKey(), d.getValue());
			
		}
		}
		 WriteResult w = getMyCollection(table).remove(r);

		return w.getError()!=null;

	}
	public static boolean insertMongoOK(String table,Parameters p) throws UnknownHostException {
		return insertMongo(table,p);

	}
	public static boolean deleteMongoOK(String table,Parameters p) throws UnknownHostException {
		return deleteMongo(table,p);

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
			//io.print(dbObject);
			pn.co=c++;
			pn.AddParam(dbObject);
			
		}
		return pn;
	}
	public static Parameters selectMongo(String select,String table, Parameters p) throws UnknownHostException {
		DBCollection dc = getMyCollection(table);
		BasicDBObject r = CreateRequest();
		whereMongo(r, p);
		BasicDBObject fields = new BasicDBObject();
		fields.put(select, 1);
		DBCursor dcu = dc.find(r,fields);
		Parameters pn = new Parameters();
		int c = 0;
		while (dcu.hasNext()) {
			DBObject dbObject = (DBObject) dcu.next();
			//io.print(dbObject);
			pn.co=c++;
			pn.AddParam(dbObject);
			
		}
		return pn;
	}
	public static Parameters selectMongo(String table, Parameters p,String...select) throws UnknownHostException {
		DBCollection dc = getMyCollection(table);
		BasicDBObject r = CreateRequest();
		whereMongo(r, p);
		BasicDBObject fields = new BasicDBObject();
		for (String string : select) {
			
		
		fields.put(string, 1);
		}
		DBCursor dcu = dc.find(r,fields);
		Parameters pn = new Parameters();
		int c = 0;
		while (dcu.hasNext()) {
			DBObject dbObject = (DBObject) dcu.next();
			//io.print(dbObject);
			pn.co=c++;
			pn.AddParam(dbObject);
			
		}
		return pn;
	}
	@SuppressWarnings("unchecked")
	public static Parameters selectMongoIn(String table,String quoi, @SuppressWarnings("rawtypes") List p) throws UnknownHostException {
		DBCollection dc = getMyCollection(table);
		//BasicDBObject r = CreateRequest();
		//whereMongo(r, p);
		//io.print(p);
		 BasicDBList docIds = new BasicDBList();
		 docIds.addAll(p);
		 DBObject inClause = new BasicDBObject("$in", docIds);
         DBObject r = new BasicDBObject(quoi, inClause);
		DBCursor dcu = dc.find(r);
		
		Parameters pn = new Parameters();
		int c = 0;
		while (dcu.hasNext()) {
			DBObject dbObject = (DBObject) dcu.next();
			//io.print(dbObject);
			pn.co=c++;
			pn.AddParam(dbObject);
			
		}
		return pn;
	}
}
