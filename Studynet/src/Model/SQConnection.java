package Model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQConnection {
	static String host;
	public static Connection con;

	public static Connection getCon() {
		return con;
	}
	
	public static void setConnection() {
		System.out.println("- Getting Host name...");
		System.out.println("1");
		getComputerName();
		System.out.println("- Establishing connection...");
		try {buildConnection();} catch (SQLException e) {e.printStackTrace();}
		
		
	}
	
	public static void getComputerName() {
		try
		{
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    host = addr.getHostName();
		    System.out.println("   Host name is: " + host);
		}
		catch (UnknownHostException ex)
		{
		    System.out.println("~Hostname can not be resolved~");
		}
	}
	
	public static void buildConnection() throws SQLException {
		/* Prepare url for connection*/
		String connectionUrl =  "jdbc:sqlserver://";
		connectionUrl += host;
		connectionUrl += "\\SQLEXPRESS;database=StudyNet-Sol;integratedSecurity=true;";
		/*Create the connection, and update attribute in class*/
		con = DriverManager.getConnection(connectionUrl);
		//Statement stmt = con.createStatement();
		System.out.println("  Connected to SQL-SERVER");
	}
	
}
