package layer_artifact;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main_class {
	
public static void main(String args[]) throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root"); 
		
		DatabaseMetaData dbm = con.getMetaData();
		ResultSet tables = dbm.getTables(null, null, "layer", null);
		if (tables.next()) {
			System.out.println("Table exist");
			Home h = new Home();
			h.dis();
		}
		else {
			System.out.println("Table does not exist");
			CreateSSD s = new CreateSSD();
			s.dis();
		}
	}

}
