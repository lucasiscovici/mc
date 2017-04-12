package db;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.bson.types.ObjectId;

import util.Dico;
import util.Error;
import util.LucasException;
import util.Parameters;
import util.RespS;
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

import services.utils.Service;

/**
 * Classe db_Helper
 */

public class db_Helper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * @return (Statement) Database.getMySQLConnection().createStatement()
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static Statement giveMeAnStatement() throws SQLException, ClassNotFoundException {
		return (Statement) Database.getMySQLConnection().createStatement();
	}
	
	public static void closeMySQLConnection() throws ClassNotFoundException, SQLException {
		//Database.getMySQLConnection().close();
	}
	/**
	 * 
	 * @param rs Un ResultSet
	 * @return La liste de nos colonne
	 * @throws SQLException
	 */
	
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
	
	/**
	 * 
	 * @param resultSet Un ResultSet
	 * @return Le nombre de ligne de notre résultat
	 */
	
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
	
	/**
	 * 
	 * @param query Une requête
	 * @return Un paramètre
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static Parameters select(String query) throws SQLException, ClassNotFoundException {
		try {
			List<Dico> liste = new ArrayList<Dico>();
			//io.print(query);
			Statement s = giveMeAnStatement();
			//io.print(query);
			ResultSet r = s.executeQuery(query);
			List<String> columns = getColumnsNames(r);

			int c = 0;
			int o = getRowCount(r);
			while (r.next()) {
				if (o == 1) {
					for (String string : columns) {
						liste.add(Dico.kv(string, r.getString(string)));
					}
				} else {
					Dico l = new Dico();
					l.countD = c;
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
		} finally {
			closeMySQLConnection();
		}
	}
	
	/**
	 * 
	 * @param dico Un Dico
	 * @return Une requête
	 */
	
	public static String multipleAnd(Parameters dico) {
		String query = "";
		if (dico.parameters.size()==0) {
			return "";
		}
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
	
	/**
	 * 
	 * @param dico Un Dico
	 * @return Une requête
	 */
	
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
	
	/**
	 * 
	 * @param dico Un Dico
	 * @return Une requête
	 */
	
	public static String where(Parameters dico) {
		if (dico==null || dico.parameters.size()==0) {
			return "";
		}
		return " WHERE "+multipleAnd(dico);
	}
	
	/**
	 * 
	 * @param query Une requête
	 * @param dico Un paramètre
	 * @return select(query)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static Parameters select(String query,Parameters dico) throws SQLException, ClassNotFoundException {
		query += where(dico);
		return select(query);
	}
	
	/**
	 * 
	 * @param query Une requête
	 * @param dico Un paramètre
	 * @return select(query, dico)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static Parameters selectAndWhered(String query,Parameters dico) throws SQLException, ClassNotFoundException {
		return select(query, dico);
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param dico Un paramètre
	 * @return select(table, dico)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static Parameters selectAndWhere(String table,Parameters dico) throws SQLException, ClassNotFoundException {
		return select(table, dico);
	}
	
	public static Parameters selectAndWhereAll(String table,Parameters dico) throws SQLException, ClassNotFoundException {
		return select("SELECT * FROM "+table, dico);
	}
	/**
	 * 
	 * @param select Une chaine de caractère
	 * @param table Une table
	 * @param dico Un Paramètre
	 * @return select(query, dico)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static Parameters selectAndWhere(String select,String table,Parameters dico) throws SQLException, ClassNotFoundException {
		String query = CreateSelectFrom(Dico.fv(select), table);
		return select(query, dico);
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param dico Un paramètre
	 * @return select(query, dico).getValueInt("id")
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static Integer selectAndWhereID(String table,Parameters dico) throws SQLException, ClassNotFoundException {
	try
	    {
	        Integer.parseInt(dico.getValue("id"));
	    } catch (NumberFormatException ex)
	    {
	       // return RespS.cl(dico.myService, Error.NumberFormatException.detail("id not an int"));
	    }
		String query = CreateSelectFrom(Dico.fv("id"), table);
		return select(query, dico).getValueInt("id");
	}
	
	/**
	 * 
	 * @param table Une talbe
	 * @param dico Un paramètre
	 * @param selects Un ensemble de chaine de caractère
	 * @return select(query, dico)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public static Parameters selectAndWhere(String table,Parameters dico,String...selects) throws SQLException, ClassNotFoundException {
		String query = CreateSelectFrom(Dico.fv(selects), table);
		return select(query, dico);
	}
	
	/**
	 * 
	 * @param param Un paramètre
	 * @return Une requête
	 */
	
	public static String PrepareVirgule(Parameters param) {
		String query = "";
		 for (Dico dico: param.parameters) {
			query += " `"+dico.getValue()+"`,";
		}
		query = query.substring(0, query.length() - 1);
		return query;
	}

	public static String PrepareVirgule2(Parameters param) {
		String query = "";
		 for (Dico dico: param.parameters) {
			query += "`"+dico.getKey()+"`"+" = \""+dico.getValue()+"\",";
		}
		query = query.substring(0, query.length() - 1);
		return query;
	}
	/**
	 * 
	 * @param param Un paramètre
	 * @param table Une table
	 * @return Une requête
	 */
	
	public static String CreateSelectFrom(Parameters param,String table) {
		String query = "SELECT ";
		query+=PrepareVirgule(param);
		query+= " FROM "+table+" ";
		return query;
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param sets Un paramètre
	 * @param where Un paramètre
	 * @return Une exécution de modification
	 * @throws LucasException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public static int update(String table,Parameters sets,Parameters where) throws LucasException, ClassNotFoundException, SQLException {
		try {
			String query = "UPDATE " + table;
			if (sets == null || sets.parameters.size() == 0) {
				throw new LucasException("db_Helper sets pb update");
			}
			query += " SET ";
			query += PrepareVirgule2(sets);
			query += where(where);
			//io.print(query);
			Statement s = giveMeAnStatement();
			return s.executeUpdate(query);
		} finally {
			closeMySQLConnection();
		}

	}
	
	/**
	 * 
	 * @param table Une table
	 * @param sets Un paramètre
	 * @param where Un paramètre
	 * @return Une modification Mongo
	 * @throws LucasException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws UnknownHostException
	 */
	
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
		return w.getError() == null;
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param sets Un paramètre
	 * @param where Un paramètre
	 * @return update(table,sets,where) > 0 true, false sinon
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws LucasException
	 */
	
	public static boolean updateOK(String table,Parameters sets,Parameters where) throws SQLException, ClassNotFoundException, LucasException {
		return update(table,sets,where) > 0;
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param sets Un paramètre
	 * @param where Un paramètre
	 * @return updateMongo(table,sets,where)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws LucasException
	 * @throws UnknownHostException
	 */
	
	public static boolean updateMongoOK(String table,Parameters sets,Parameters where) throws SQLException, ClassNotFoundException, LucasException, UnknownHostException {
		return updateMongo(table,sets,where);
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param dico Un paramètre
	 * @return Compte le nombre de ligne du résultat d'un select and where
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static int selectAndWhere_Count(String table,Parameters dico) throws SQLException, ClassNotFoundException {
		String query = "SELECT COUNT(`id`) as \"count\" FROM "+table+"";
		//io.print(dico);
		Parameters result = selectAndWhere(query, dico);
		//io.print(result);
		return result.getValueInt("count");
	}
	
	/**
	 * 
	 * @param query Une requête
	 * @return une insertion
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public static int insert(String query) throws SQLException, ClassNotFoundException {
		//io.print(query);
		try {
			Statement s = giveMeAnStatement();
			return s.executeUpdate(query);
		} finally {
			closeMySQLConnection();
		}
	}
	
	/**
	 * 
	 * @param d Une liste de Chaine de caractère
	 * @return Une requête
	 */

	public static String stringMe(List<String> d) {
		String queryn = "";
		for (String string : d) {
			queryn+=" \""+string+"\",";
		}
		queryn = queryn.substring(0,queryn.length()-1);
		return queryn;
	}
	
	/**
	 * 
	 * @param d Une liste de Chaine de caractère
	 * @return Une requête
	 */
	
	public static String keysMe(List<String> d) {
		String queryn = "";
		for (String string : d) {
			queryn+=" `"+string+"`,";
		}
		queryn = queryn.substring(0,queryn.length()-1);
		return queryn;
	}
	
	/**
	 * 
	 * @param query Une requête
	 * @param dd Un paramètre
	 * @return Une insertion de valeur
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static int insertValues(String query,Parameters dd) throws SQLException, ClassNotFoundException {
		try {
			List<String> d = dd.getOnlyValues();
			query += " VALUES( ";
			query += stringMe(d);
			query += ")";
			//io.print(query);
			Statement s = giveMeAnStatement();
			int sf = s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = s.getGeneratedKeys();
			if (rs.next()) {
				dd.AddParam("id", rs.getInt(1));
			}
			return sf;
		} finally {
			closeMySQLConnection();
		}
	}
	/**
	 * 
	 * @param table Une table
	 * @param d Un paramètre
	 * @return insertValues(query, d)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static int insert(String table,Parameters d) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO "+table+"(";
		query += keysMe(d.getOnlyKeys())+")";
		return insertValues(query, d);
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param d Un paramètre
	 * @return s.executeUpdate(query)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static int delete(String table,Parameters d) throws SQLException, ClassNotFoundException {
		try {

		String query = "DELETE FROM "+table+"";
		query += where(d);
		Statement s = giveMeAnStatement();
		//io.print(query);
		return s.executeUpdate(query);	
			
		} finally {
			closeMySQLConnection();
		}
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param d Un paramètre
	 * @return selectMongo(table, d).parameters.size() > 0 true, false sinon
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public static boolean selectMongoOK(String table,Parameters d) throws UnknownHostException, LucasException {
		return selectMongo(table, d).parameters.size() > 0;

	}
	
	/**
	 * 
	 * @param table Une table
	 * @param d Un paramètre
	 * @return insert(table,d) > 0 true, false sinon
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static boolean insertOK(String table,Parameters d) throws SQLException, ClassNotFoundException {
		return insert(table,d) > 0;
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param d Un paramètre
	 * @return delete(table,d) > 0 true, false sinon
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static boolean deleteOK(String table,Parameters d) throws SQLException, ClassNotFoundException {
		return delete(table,d) != -1;
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param d Un paramètre
	 * @return selectAndWhere_Count(table, d) > 0 true, false sinon
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public static boolean selectOK(String table,Parameters d) throws SQLException, ClassNotFoundException {
		return selectAndWhere_Count(table, d) > 0;
	}
	
	/**
	 * 
	 * @return new Mongo(DBStatic.mongo_host, DBStatic.mongo_port)
	 * @throws UnknownHostException
	 */
	
	public static Mongo getMyMongo() throws UnknownHostException {
		return new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
		
	}
	
	/**
	 * 
	 * @return getMyMongo().getDB(DBStatic.mongo_db)
	 * @throws UnknownHostException
	 */
	
	public static DB getMyDB() throws UnknownHostException {
		return getMyMongo().getDB(DBStatic.mongo_db);
	}
	
	/**
	 * 
	 * @param table
	 * @return getMyDB().getCollection(table)
	 * @throws UnknownHostException
	 */
	
	public static DBCollection getMyCollection(String table) throws UnknownHostException {
		return getMyDB().getCollection(table);
	}
	
	/**
	 * 
	 * @return new BasicDBObject()
	 */
	
	public static BasicDBObject CreateRequest() {
		return new BasicDBObject();
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param p Un paramètre
	 * @return w.getError()==null true, false sinon
	 * @throws UnknownHostException
	 */
	
	public static boolean insertMongo(String table,Parameters p) throws UnknownHostException {
		BasicDBObject r= CreateRequest();
		for (Dico d : p.parameters) {
			if (d.getKey().indexOf("date")!=-1) {
				  SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy",Locale.ENGLISH);
				  try {
					Date date = parser.parse(d.getValue());
					r.put(d.getKey(), date);

				  }catch (Exception e) {
					// TODO: handle exception
				}

			}else{
				r.put(d.getKey(), d.getValue());
			}
		}
		 WriteResult w =  getMyCollection(table).insert(r);
		 String id = r.get( "_id" ).toString();
		 p.AddParam("id", id);
		 //io.print(id);
		return w.getError()==null;
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param p Un paramètre
	 * @return w.getError()==null true, false sinon
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public static boolean deleteMongo(String table,Parameters p) throws UnknownHostException, LucasException {
		BasicDBObject r= CreateRequest();
		whereMongo(r, p);
		 WriteResult w = getMyCollection(table).remove(r);
		return w.getError()==null;

	}
	
	/**
	 * 
	 * @param table Un table
	 * @param p Un paramètre
	 * @return insertMongo(table,p)
	 * @throws UnknownHostException
	 */
	
	public static boolean insertMongoOK(String table,Parameters p) throws UnknownHostException {
		return insertMongo(table,p);

	}
	
	/**
	 * 
	 * @param table Une table
	 * @param p Un paramètre
	 * @return deleteMongo(table,p)
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public static boolean deleteMongoOK(String table,Parameters p) throws UnknownHostException, LucasException {
		return deleteMongo(table,p);

	}
	
	/**
	 * 
	 * @param r Un BasicDBObject
	 * @param p Un paramètre
	 * @throws LucasException 
	 */
	
	public static void whereMongo(BasicDBObject r, Parameters p) throws LucasException {
		if (p==null) {
			return;
		}
		for (Dico d : p.parameters) {
			if (d.getKey().equals("_id")) {
				try{
				r.put(d.getKey(), new ObjectId(d.getValue()));
				}catch (Exception e) {
					throw RespS.cl(p.myService, Error.ErrArgs.detail("mets un id_post valide !!"));
				}
				
			}else if (d.getKey().indexOf("date_") != -1) {
					Date date = new java.util.Date(Long.parseLong(d.valuesd.get(0).getValue()));
					r.put(d.valuesd.get(0).getKey(), new BasicDBObject(d.getKey().replace("date_", ""), date));
				
				  
			}
			else{
			r.put(d.getKey(), d.getValue());
			}
		}
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param p Un paramètre
	 * @return Un paramètre
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public static Parameters selectMongo(String table, Parameters p) throws UnknownHostException, LucasException {
		DBCollection dc = getMyCollection(table);
		BasicDBObject r = CreateRequest();
	
		whereMongo(r, p);
		DBCursor dcu = dc.find(r).sort(new BasicDBObject("date",-1));
		Parameters pn = new Parameters();
		int c = 0;
		while (dcu.hasNext()) {
			DBObject dbObject = (DBObject) dcu.next();
			pn.co=c++;
			pn.AddParam(dbObject);
			
		}
		return pn;
	}
	
	/**
	 * 
	 * @param select Une chaine de caractère
	 * @param table Une table
	 * @param p Un paramètre
	 * @return Un paramètre
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public static Parameters selectMongo(String select,String table, Parameters p) throws UnknownHostException, LucasException {
		DBCollection dc = getMyCollection(table);
		BasicDBObject r = CreateRequest();
		whereMongo(r, p);
		BasicDBObject fields = new BasicDBObject();
		fields.put(select, 1);
		DBCursor dcu = dc.find(r,fields).sort(new BasicDBObject("date",-1));
		Parameters pn = new Parameters();
		int c = 0;
		while (dcu.hasNext()) {
			DBObject dbObject = (DBObject) dcu.next();
			
			pn.co=c++;
			pn.AddParam(dbObject);
			
		}
		return pn;
	}
	
	/**
	 * 
	 * @param table Une table
	 * @param p Un paramètre
	 * @param select Un ensemble de chaine de caractère
	 * @return Un paramètre
	 * @throws UnknownHostException
	 * @throws LucasException 
	 */
	
	public static Parameters selectMongo(String table, Parameters p,String...select) throws UnknownHostException, LucasException {
		DBCollection dc = getMyCollection(table);
		BasicDBObject r = CreateRequest();
		whereMongo(r, p);
		BasicDBObject fields = new BasicDBObject();
		for (String string : select) {
			
		
		fields.put(string, 1);
		}
		DBCursor dcu = dc.find(r,fields).sort(new BasicDBObject("date",-1));
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
	
	/**
	 * 
	 * @param table Une table
	 * @param quoi Une chaine de caractère
	 * @param p Un paramètre
	 * @return Un paramètre
	 * @throws UnknownHostException
	 */
	
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
		DBCursor dcu = dc.find(r).sort(new BasicDBObject("date",-1));
		
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
	public static Parameters selectMongoIn(String table,String quoi, @SuppressWarnings("rawtypes") List p,Parameters params) throws UnknownHostException, LucasException {
		DBCollection dc = getMyCollection(table);
		Integer limit=null;
		if (params.getDicosOK("limit")) {
			limit = params.getValueInt("limit");
			params = params.PSN("limit");
		}
		if (params.getDicosOK("$gt")) {
			
		}
		//BasicDBObject r = CreateRequest();
		
		//io.print(p);
		 BasicDBList docIds = new BasicDBList();
		 docIds.addAll(p);
		 DBObject inClause = new BasicDBObject("$in", docIds);
		 BasicDBObject r = new BasicDBObject(quoi, inClause);
		 whereMongo(r, params);
		 //io.print(r);
		DBCursor dcu = dc.find(r).sort(new BasicDBObject("date",-1));
		io.print(dcu);
		if (limit != null) {
			dcu = dcu.limit(limit);
		}
		
		Parameters pn = new Parameters();
		int c = 0;
		while (dcu.hasNext()) {
			DBObject dbObject = (DBObject) dcu.next();
			io.print(dbObject);
			pn.co=c++;
			pn.AddParam(dbObject);
			
		}
		return pn;
	}
}
