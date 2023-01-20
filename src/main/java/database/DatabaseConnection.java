package database;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
	
	public Statement stmt;
	public Connection con;
	
	public void open_connection_psql(){
		try{
			Class.forName("org.postgresql.Driver");
			
			this.con = DriverManager.getConnection("jdbc:postgresql://localhost/rencontre","postgres","root");

			this.stmt = this.con.createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void close_connection_psql(){
		try{
			con.commit();
			con.close();
		}
		catch(Exception e){}
	}

	public static boolean isGetter(Method method) {
		boolean result = method.getName().startsWith("get")
	        && (method.getParameterTypes().length == 0)
	        && (!Void.class.equals(method.getReturnType()));
		return result;
	}

	public static boolean isSetter(Method method) {
		boolean result = (method.getName().startsWith("set"))
	        && (method.getParameterTypes().length == 1);
		return result;
	}

	public void open_connection(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
	      
	       this.con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:newbase","system","root");

	       this.stmt = this.con.createStatement();
		}
		catch(Exception e){
			System.out.println("open");
			e.printStackTrace();
		}
	}

	public void close_connection(){
		try{
			con.close();
		}
		catch(Exception e){System.out.println(e);}
	}
}
