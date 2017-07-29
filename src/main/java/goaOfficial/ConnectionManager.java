package goaOfficial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static Connection connection;
	static String DRIVER="com.mysql.cj.jdbc.Driver";  
	static String CONNECTION_URL="jdbc:mysql://localhost:3306/goa";  
	static String USERNAME="root";  
	static String PASSWORD="root"; 

	public static Connection getConnection() {
		//System.out.println("Registering Database Driver");
		try {
			Class.forName(DRIVER);
		 
		//System.out.println("Database Driver Registered!");
		//System.out.println("onnecting to Database");
		connection=DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);
		//System.out.println("Successfully connected to Database");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return connection;
	}
	
	public static void closeConnection() throws SQLException{
		connection.close();
		
	}

}
