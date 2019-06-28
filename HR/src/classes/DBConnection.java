package classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	
	private static Connection con;
	
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
	
	public static void main(String[] args) {
		DBManager manager = new DBManager(DBConnection.getCon());
//		Account account = new Account(0, new Date(System.currentTimeMillis()), "gega", "12345", Account.EMPLOYEE_ACCOUNT_TYPE);
//		manager.addAccount(account);
//		account.setPassHash("123");
//		manager.updateAccount(account);
//		manager.deleteAccount(account);
		
		
//		 System.out.println(manager.getCompanyVacancies(8).size());
//		 System.out.println(manager.getCompanyVacancies(8).get(0).getEndDate());
//		 System.out.println(manager.getCompanyVacancies(8).get(1).getEndDate());
//		  
//		 System.out.println(manager.getCompanyVacancies(8).size());
//		 System.out.println(manager.getCompanyVacancies(9).size());
		 
		
		/*
		 * Vacancy vacancy = new Vacancy(2, "ChangedHeading", "ChangedDesc", 7,
		 * "Teacher", "Batumi", null, new Date(123151523), new Date(351523824),
		 * "Part Time"); manager.updateVacancy(vacancy);
		 */
		
		//manager.deleteVacancy(2);
		
		//manager.addFollower(7, 4);
		//manager.unFollow(7, 4);
		
		
		Date date1 = new Date(21414123);
		Date date2 = new Date(21414123);
		
		System.out.println(date1.equals(date2));
	}
}
