package database;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class DBHelper {
	/**
	 * DBHelper to check for the database
	 * Create new tables if needed
	 */
	
	Connection c;
	public DBHelper(){
		c = null;
	}
	
	/**
	 * Open connection to the database
	 */
	public void openConnectionToDB(){
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hw2.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}		
	}
	
	/**
	 * Close connection to the database
	 */
	public void closeConnectionToDB(){
		try {
			if ((c != null) && c.isValid(0))
				c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Check for the database If it doesn't exist, create one
	 * http://www.tutorialspoint.com/sqlite/sqlite_java.htm
	 */
	public void checkDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hw2.db");
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Opened database successfully");
	}
	
	/**
	 * Create new tables in database
	 * 
	 */
	public void createTables(){
		List<String> statements = Arrays.asList("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, username TEXT NOT NULL UNIQUE, password TEXT NOT NULL, is_online INTEGER NOT NULL);",
				"CREATE TABLE  IF NOT EXISTS character_type(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT NOT NULL, model TEXT NOT NULL);",
				"CREATE TABLE  IF NOT EXISTS character("
						+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
				        + "user_id INTEGER NOT NULL,"
				        + "char_type_id INTEGER NOT NULL,"
				        + "char_active INTEGER NOT NULL,"
				        + "char_x FLOAT NOT NULL, char_y FLOAT NOT NULL, char_z FLOAT NOT NULL,"
				        + "char_h FLOAT NOT NULL, char_p FLOAT NOT NULL, char_r FLOAT NOT NULL,"
				        + "FOREIGN KEY(user_id) REFERENCES user(id),"
				        + "FOREIGN KEY(char_type_id) REFERENCES character_type(id));"
				);

		
		// Open the connection
		openConnectionToDB();
		try {
			Statement stmt = c.createStatement();
			for(int index=0; index < statements.size(); index++){
				stmt.execute(statements.get(index));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnectionToDB();		
	}
}
