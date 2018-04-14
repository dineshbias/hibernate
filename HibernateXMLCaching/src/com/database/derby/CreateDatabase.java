/**
 * 
 */
package com.database.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author edinjos
 *
 */
public class CreateDatabase {

	private final static String CREATE_EMPLOYEE_SECOND_LEVEL_CACHED = "create table EMPLOYEE_CACHED"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1), "
			+ "first_name VARCHAR(20) default NULL, "
			+ "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "PRIMARY KEY (id) )";

	private final static String CREATE_EMPLOYEE_DEFAULT_CACHING = "create table EMPLOYEE"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1), "
			+ "first_name VARCHAR(20) default NULL, "
			+ "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "PRIMARY KEY (id) )";

	private final static String CREATE_EMPLOYEE_SECOND_LEVEL_QueryCACHED = "create table EMPLOYEE_QUERY_CACHED"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1), "
			+ "first_name VARCHAR(20) default NULL, "
			+ "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "PRIMARY KEY (id) )";

	/**
	 * 
	 */
	public CreateDatabase() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) {

		String url = "jdbc:derby:testCachingDB;user=dinesh;password=joshi;create=true";
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement()) {
			createTables(stmt);
			fetchDataEmployeeCached(stmt);
			fetchDataEmployeeNOTCached(stmt);
			fetchDataEmployeeQueryCached(stmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTables(Statement stmt) throws SQLException {
		System.out.println("Creating DB");
		// stmt.executeUpdate(CREATE_EMPLOYEE_DEFAULT_CACHING);
		// stmt.executeUpdate(CREATE_EMPLOYEE_SECOND_LEVEL_CACHED);
		//stmt.executeUpdate(CREATE_EMPLOYEE_SECOND_LEVEL_QueryCACHED);
		//System.out.println("Tables Created : ");
	}

	public static void fetchDataEmployeeCached(Statement stmt)
			throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_CACHED");
		System.out.println("Fetch Data from EMPLOYEE_CACHED");
		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			System.out.println(" id:" + id + " last_name:" + last_name
					+ " first_name:" + first_name + " salary:" + salary);
		}

	}

	public static void fetchDataEmployeeNOTCached(Statement stmt)
			throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE");
		System.out.println("Fetch Data from EMPLOYEE");
		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			System.out.println(" id:" + id + " last_name:" + last_name
					+ " first_name:" + first_name + " salary:" + salary);
		}

	}
	
	public static void fetchDataEmployeeQueryCached(Statement stmt)
			throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_QUERY_CACHED");
		System.out.println("Fetch Data from EMPLOYEE_QUERY_CACHED");
		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			System.out.println(" id:" + id + " last_name:" + last_name
					+ " first_name:" + first_name + " salary:" + salary);
		}

	}

}
