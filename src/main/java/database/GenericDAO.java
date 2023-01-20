package database;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class GenericDAO extends DatabaseConnection{
	Field[] fields = this.getClass().getDeclaredFields();
	Method tmp = null;
	String str = null;
	int nbrA = 0;
	
	
	public void query(String sql) {
		try {
			open_connection_psql();
			stmt.executeQuery(sql);
			close_connection_psql();
		} catch (Exception e) {
			close_connection_psql();
		}
	}
	
	public String[][] querySelect(String sql){
		try {
			open_connection_psql();
			ResultSet res = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = res.getMetaData();
			int nbr = 0;
			while (res.next()) nbr++;
			String[][] data = new String[nbr][rsmd.getColumnCount()];
			res = stmt.executeQuery(sql);
			for (int j = 0;res.next();j++) {
			    for (int i = 0; i < data[0].length; i++) {
                    data[j][i] = res.getObject(i+1)+"";
                }
			}
			close_connection_psql();
			return data;
		} catch (Exception e) {
			close_connection_psql();
			e.printStackTrace();
			return null;
		}
	}
	

//	public Object[][] select(){    
//        try {
//            open_connection_psql();
//            ResultSet res = stmt.executeQuery("select * from "+ this.getClass().getSimpleName());
//            ResultSetMetaData rsmd = res.getMetaData();
//            int nbr = 0;
//            while (res.next()) nbr++;
//            Object[][] data = new Object[nbr][rsmd.getColumnCount()];
//            res = stmt.executeQuery("select * from "+ this.getClass().getSimpleName());
//            for (int j = 0;res.next();j++) {
//                for (int i = 0; i < data[0].length; i++) {
//                    data[j][i] = res.getObject(i+1);
//                }
//            }
//            close_connection_psql();
//            return data;
//        } catch (Exception e) {
//            close_connection_psql();
//            return null;
//        }
//        
//    }

	
}
