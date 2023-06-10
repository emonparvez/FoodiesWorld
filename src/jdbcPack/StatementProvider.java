package jdbcPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class StatementProvider {
	
	public static Statement getStatement() throws Exception{
		String url = "jdbc:mysql://localhost/foodiesworld";
		String uname = "root";
		String pass = "root";
		Connection conn;
		Statement stmt = null;
		
		    Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection(url,uname,pass);
		    
			stmt = conn.createStatement();
			
		return stmt;
		
	}

}
