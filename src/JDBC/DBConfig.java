package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

		private static final String USERNAME = "Ahdieh";
		private static final String PASSWORD = "12345";
		private static final String CONN_STRING = "jdbc:mysql://localhost/java_assignment_5";
		
		// UTILTITY METHOD that connects us to the mySql database
		public static Connection getConnection() throws SQLException {
			return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
		}
		
		// UTILITY METHOD that displays our errors in more detail
		public static void displayException(SQLException exception) {
			System.err.println("Error Message: " + exception.getMessage());
			System.err.println("Error Code: " + exception.getErrorCode());
			System.err.println("SQL State: " + exception.getSQLState());
	}
}
