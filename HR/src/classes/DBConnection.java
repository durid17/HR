package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Class DBConnection.
 */
public class DBConnection {
	
	/** The account. */
	static String account = MyDBInfo.MYSQL_USERNAME;
	
	/** The password. */
	static String password = MyDBInfo.MYSQL_PASSWORD;
	
	/** The server. */
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	
	/** The database. */
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	
	/** The test database. */
	static String testDatabase = MyDBInfo.MYSQL_TEST_DATABASE_NAME;
	
	
	
	/** The Connections. */
	private static Connection con;
	private static Connection testCon;
	
	/**
	 * Gets the Connection.
	 *
	 * @return the Connection
	 */
	public static Connection getCon() {
		try { 
			if(con == null) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://" + server, account, password);		
				Statement statement = con.createStatement();
				statement.executeQuery("USE " + database);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	/**
	 * Gets the test Connection.
	 *
	 * @return the test Connection
	 */
	public static Connection getTestCon() {
		try { 
			if(testCon == null) {
				Class.forName("com.mysql.jdbc.Driver");
				testCon = DriverManager.getConnection("jdbc:mysql://" + server, account, password);		
				Statement statement = testCon.createStatement();
				statement.executeQuery("USE " + testDatabase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return testCon;
	}
	
	/**
	 * Close connection.
	 */
	// closes connection
	public static void closeCon() {
		try {
			if(con != null) {
				con.close();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
