/**
 * 
 */
package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDataSource;

/**
 * @author edinjos
 *
 */
public class TestJDBC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String sql = "select name from animal order by id";
		
		
		selectDataFromDataSource();

	}

	public static void selectDataFromDataSource() {
		try {
			DataSource ds = makeDataSource("zoo", false);

			try (Connection conn = ds.getConnection()) {

				System.out.println(conn);
				//testStatementWithDefaultParameterValues(conn);
				System.out.println();
				testStatementWithSpecificParameterValues(conn);
				System.out.println();
				//fetchDateAndObjectData(conn);

			} catch (SQLException e) {

				e.printStackTrace();
			}

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void selectDataDriverManager() {
		System.out.println("Looking for Data using DriverManager");
		final String jdbcURL = "jdbc:derby:zoo";
		
		try (Connection conn = DriverManager.getConnection(jdbcURL);) {
			System.out.println(conn);
			testStatementWithDefaultParameterValues(conn);
			System.out.println();
			testStatementWithSpecificParameterValues(conn);
			System.out.println();
			fetchDateAndObjectData(conn);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void testStatementWithDefaultParameterValues(Connection conn)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select * from species");

			System.out.println(stmt);
			System.out.println(rs);
			
			while (rs.next()) {
				String id = rs.getString("id");
				String area = rs.getString("num_acres");
				String name = rs.getString("name");
				
				System.out.println("id:" + id + " area:" + area + " name:"
						+ name );
			}
			System.out.println();
		} finally {
			if (null != stmt)
				stmt.close();
			if (null != rs)
				rs.close();
		}
	}

	public static void testStatementWithSpecificParameterValues(Connection conn)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn.createStatement(0,1);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("Select * from species");
			System.out.println(stmt);
			System.out.println(rs);
			System.out.println(rs.next());
			System.out.println("Row:" + rs.getRow());
			System.out.println(rs.previous());
			System.out.println(rs.previous());
			System.out.println(rs.previous());
			System.out.println(rs.previous());
			System.out.println("Row:" + rs.getRow());
			System.out.println("ABS:" + rs.absolute(-200));
			System.out.println(rs.next());
			System.out.println("Row:" + rs.getRow());
			
			
			while (rs.next()) {
				String id = rs.getString("id");
				String area = rs.getString("num_acres");
				String name = rs.getString("name");
				System.out.println("id:" + id + " area:" + area + " name:"
						+ name);
			}
			rs.beforeFirst();
			System.out.println("");
			
			while (rs.next()) {
				String id = rs.getString("id");
				String area = rs.getString("num_acres");
				String name = rs.getString("name");
				System.out.println("row:" + rs.getRow() + " id:" + id
						+ " area:" + area + " name:" + name);
			}

		} finally {
			if (null != stmt)
				stmt.close();
			if (null != rs)
				rs.close();
		}
	}

	public static void fetchDateAndObjectData(Connection conn)
			throws SQLException {
		Statement stm = null;
		ResultSet rs = null;
		String query = "Select * from animal where id=1";
		try {
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			boolean resultSet = stm.execute(query);
			if (resultSet) {
				rs = stm.getResultSet();
				rs.next();

				String id = rs.getString("id");
				String species_id = rs.getString("species_id");
				Object name = rs.getObject("name");
				
				java.sql.Date date = rs.getDate("date_born");
				Time time = rs.getTime("date_born");
				Timestamp timestamp = rs.getTimestamp("date_born");
				
				System.out.println("************************");
				System.out.println("date:"+date+" time:"+time+" timestamp:"+timestamp);
				System.out.println("************************");
				
				LocalDateTime localDateTime = LocalDateTime.of(
						date.toLocalDate(), time.toLocalTime());
				

				System.out.println(timestamp.toLocalDateTime());
				System.out.println(date + " " + time);
				System.out.println(rs.getRow() + "***" + id + " " + species_id
						+ " " + name + " " + localDateTime);
			}

		} finally {
			if (null != stm)
				stm.close();
			if (null != rs)
				rs.close();
		}

	}

	public static javax.sql.DataSource makeDataSource(String dbname,
			boolean create) throws Throwable {
		EmbeddedDataSource ds = new EmbeddedDataSource();
		ds.setDatabaseName(dbname); // It internally uses jdbc URL
									// jdbc:derby:dbname
		
		if (create)
			ds.setCreateDatabase("create");

		return ds;
	}
}
