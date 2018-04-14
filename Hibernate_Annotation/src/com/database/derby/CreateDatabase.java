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

	private final static String CREATE_EMPLOYEE_ANNOTATION = "create table EMPLOYEE_ANNOTATION"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1), "
			+ "first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "PRIMARY KEY (id) )";

	private final static String CREATE_EMPLOYEE_TEMP = "create table EMPLOYEE_TEMP"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1), "
			+ "first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "PRIMARY KEY (id) )";

	private final static String CREATE_EMPLOYEE_OTM = "create table EMPLOYEE_OTM"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)"
			+ " , first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, PRIMARY KEY (id) )";

	private final static String CREATE_CERTIFICATE_OTM = "create table CERTIFICATE_OTM"
			+ "( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 10000,INCREMENT BY 1), "
			+ "certificate_name VARCHAR(30) default NULL," + "employee_id INT default NULL, PRIMARY KEY (id))";

	private final static String CREATE_EMPLOYEE_OTM_INTERMEDIATE_TABLE = "create table EMPLOYEE_OTM_3"
			+ " ( employee_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)"
			+ " , first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, PRIMARY KEY (employee_id) )";

	private final static String CREATE_CERTIFICATE_OTM_INTERMEDIATE_TABLE = "create table CERTIFICATE_OTM_3"
			+ "( certificate_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 10000,INCREMENT BY 1), "
			+ "certificate_name VARCHAR(30) default NULL," + "PRIMARY KEY (certificate_id))";

	private final static String CREATE_EMPLOYEE_CERTIFICATE_OTM = "create table EMPLOYEE_CERTIFICATE_OTM_3"
			+ "( employee_id INT, certificate_id INT)";

	private final static String CREATE_EMPLOYEE_MTO = "create table EMPLOYEE_MTO"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1000,INCREMENT BY 1)"
			+ " , first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "address INT NOT NULL, " + "PRIMARY KEY (id) )";

	private final static String CREATE_ADDRESS_MTO = "create table ADDRESS_MTO"
			+ "( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 5000,INCREMENT BY 1), "
			+ "street_name VARCHAR(30) default NULL," + "city_name VARCHAR(30) default NULL,"
			+ "state_name VARCHAR(30) default NULL, " + "zipcode VARCHAR(8) default NULL, " + "PRIMARY KEY (id))";

	private final static String CREATE_EMPLOYEE_OTO = "create table EMPLOYEE_OTO"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 21000,INCREMENT BY 1), "
			+ " first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "address INT NOT NULL, " // Foreign
																		// key
																		// to
																		// CREATE_ADDRESS_OTO
																		// Table.
			+ "PRIMARY KEY (id) )";

	private final static String CREATE_ADDRESS_OTO = "create table ADDRESS_OTO"
			+ "( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 15000,INCREMENT BY 1), "
			+ "street_name VARCHAR(30) default NULL," + "city_name VARCHAR(30) default NULL,"
			+ "state_name VARCHAR(30) default NULL, " + "zipcode VARCHAR(8) default NULL, " + "PRIMARY KEY (id))";

	private final static String CREATE_EMPLOYEE_OTO_NFKA = "create table EMPLOYEE_OTO_NFKA (" + "id INT NOT NULL, "
			+ "first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "PRIMARY KEY (id) )";

	private final static String CREATE_ADDRESS_OTO_NFKA = "create table ADDRESS_OTO_NFKA (" + "id INT NOT NULL, "
			+ "street_name VARCHAR(30) default NULL, " + "city_name VARCHAR(30) default NULL, "
			+ "state_name VARCHAR(30) default NULL, " + "zipcode VARCHAR(8) default NULL, " + "PRIMARY KEY (id))";

	private final static String CREATE_EMPLOYEE_MTM = "create table EMPLOYEE_MTM"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)"
			+ " , first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "PRIMARY KEY (id) )";

	private final static String CREATE_CERTIFICATE_MTM = "create table CERTIFICATE_MTM"
			+ "( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 100,INCREMENT BY 1), "
			+ "certificate_name VARCHAR(30) default NULL," + " PRIMARY KEY (id))";

	private final static String CREATE_EMPLOYEE_CERTIFICATE_MTM = "create table EMPLOYEE_CERTIFICATE_MTM ("
			+ "employee_id INT NOT NULL, " + "certificate_id INT NOT NULL, "
			+ "PRIMARY KEY (employee_id, certificate_id))";

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

		// String url = "jdbc:derby:zoo;create=true";
		// String url = "jdbc:derby:application;create=true";
		String url = "jdbc:derby:testAnnotationDB;user=dinesh;password=joshi;create=true";
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			createTables(stmt);

			fetchDataEmployeeAnnotation(stmt);
			fetchDataEmployeeTemp(stmt);

			fetchDataEmployeeOTM(stmt);
			fetchDataCertificateOTM(stmt);
			fetchDataAddressMTO(stmt);
			fetchDataEmployeeMTO(stmt);
			fetchDataAddressOTO(stmt);
			fetchDataEmployeeOTO(stmt);
			fetchDataEmployeeOTONFKA(stmt);
			fetchDataAddressOTONFKA(stmt);
			fetchDataEmployeeMTM(stmt);
			fetchDataCertificateMTM(stmt);
			fetchDataEmployeeCertificateMTM(stmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTables(Statement stmt) throws SQLException {
		System.out.println("Creating DB");
		stmt.executeUpdate(CREATE_EMPLOYEE_ANNOTATION);
		stmt.executeUpdate(CREATE_EMPLOYEE_TEMP);

		stmt.executeUpdate(CREATE_EMPLOYEE_OTM);
		stmt.executeUpdate(CREATE_CERTIFICATE_OTM);
		stmt.executeUpdate(CREATE_EMPLOYEE_MTO);
		stmt.executeUpdate(CREATE_ADDRESS_MTO);
		stmt.executeUpdate(CREATE_EMPLOYEE_OTO);
		stmt.executeUpdate(CREATE_ADDRESS_OTO);
		stmt.executeUpdate(CREATE_EMPLOYEE_OTO_NFKA);
		stmt.executeUpdate(CREATE_ADDRESS_OTO_NFKA);
		stmt.executeUpdate(CREATE_EMPLOYEE_MTM);
		stmt.executeUpdate(CREATE_CERTIFICATE_MTM);
		stmt.executeUpdate(CREATE_EMPLOYEE_CERTIFICATE_MTM);
		stmt.executeUpdate(CREATE_EMPLOYEE_OTM_INTERMEDIATE_TABLE);
		stmt.executeUpdate(CREATE_CERTIFICATE_OTM_INTERMEDIATE_TABLE);
		stmt.executeUpdate(CREATE_EMPLOYEE_CERTIFICATE_OTM);
	}

	public static void fetchDataEmployeeAnnotation(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_ANNOTATION");
		System.out.println("Fetch Data from EMPLOYEE_ANNOTATION");
		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			System.out.println(
					" id:" + id + " last_name:" + last_name + " first_name:" + first_name + " salary:" + salary);
		}

	}

	public static void fetchDataEmployeeTemp(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_TEMP");
		System.out.println("Fetch Data from EMPLOYEE_TEMP");
		while (rs.next()) {

			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			System.out.println(" last_name:" + last_name + " first_name:" + first_name + " salary:" + salary);
		}

	}

	public static void fetchDataCertificateOTM(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from CERTIFICATE_OTM");
		System.out.println("Fetch Data from CERTIFICATE_OTM");
		while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("certificate_name");
			String idx = rs.getString("idx");
			String empId = rs.getString("employee_id");
			System.out.println(" id:" + id + " idx:" + idx + " name:" + name + " empId:" + empId);
		}

	}

	public static void fetchDataEmployeeOTM(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_OTM");
		System.out.println("Fetch Data from EMPLOYEE_SET");
		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			System.out.println(
					" id:" + id + " last_name:" + last_name + " first_name:" + first_name + " salary:" + salary);
		}

	}

	public static void fetchDataAddressMTO(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from ADDRESS_MTO");
		System.out.println("Fetch Data from ADDRESS_MTO");
		while (rs.next()) {

			String id = rs.getString("id");
			String streetName = rs.getString("street_name");
			String cityName = rs.getString("city_name");
			String stateName = rs.getString("state_name");
			String zipcode = rs.getString("zipcode");
			System.out.println(" id:" + id + " streetName:" + streetName + " cityName:" + cityName + " stateName:"
					+ stateName + " zipcode:" + zipcode);
		}

	}

	public static void fetchDataEmployeeMTO(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_MTO");
		System.out.println("Fetch Data from EMPLOYEE_MTO");

		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			String address = rs.getString("address");
			System.out.println(" id:" + id + " last_name:" + last_name + " first_name:" + first_name + " salary:"
					+ salary + " address:" + address);
		}

	}

	public static void fetchDataAddressOTO(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from ADDRESS_OTO");
		System.out.println("Fetch Data from ADDRESS_OTO");
		while (rs.next()) {

			String id = rs.getString("id");
			String streetName = rs.getString("street_name");
			String cityName = rs.getString("city_name");
			String stateName = rs.getString("state_name");
			String zipcode = rs.getString("zipcode");
			System.out.println(" id:" + id + " streetName:" + streetName + " cityName:" + cityName + " stateName:"
					+ stateName + " zipcode:" + zipcode);
		}

	}

	public static void fetchDataEmployeeOTO(Statement stmt) throws SQLException {

		// stmt.executeUpdate("Delete FROM EMPLOYEE_OTO");
		// stmt.executeUpdate("Delete FROM ADDRESS_OTO");

		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_OTO");
		System.out.println("Fetch Data from EMPLOYEE_OTO");

		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			String address = rs.getString("address");
			System.out.println(" id:" + id + " last_name:" + last_name + " first_name:" + first_name + " salary:"
					+ salary + " address:" + address);
		}

	}

	public static void fetchDataAddressOTONFKA(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from ADDRESS_OTO_NFKA");
		System.out.println("Fetch Data from ADDRESS_OTO_NFKA");
		while (rs.next()) {

			String id = rs.getString("id");
			String streetName = rs.getString("street_name");
			String cityName = rs.getString("city_name");
			String stateName = rs.getString("state_name");
			String zipcode = rs.getString("zipcode");
			System.out.println(" id:" + id + " streetName:" + streetName + " cityName:" + cityName + " stateName:"
					+ stateName + " zipcode:" + zipcode);
		}

	}

	public static void fetchDataEmployeeOTONFKA(Statement stmt) throws SQLException {

		// stmt.executeUpdate("DROP TABLE ADDRESS_OTO_NFKA");
		// stmt.executeUpdate("DROP TABLE EMPLOYEE_OTO_NFKA");

		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_OTO_NFKA");
		System.out.println("Fetch Data from EMPLOYEE_OTO_NFKA");

		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");

			System.out.println(
					" id:" + id + " last_name:" + last_name + " first_name:" + first_name + " salary:" + salary);
		}

	}

	public static void fetchDataEmployeeCertificateMTM(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_CERTIFICATE_MTM");
		System.out.println("Fetch Data from EMPLOYEE_CERTIFICATE_MTM");
		while (rs.next()) {
			String employeeId = rs.getString("employee_id");
			String certificateId = rs.getString("certificate_id");
			System.out.println(" employeeId:" + employeeId + " certificateId:" + certificateId);
		}

	}

	public static void fetchDataCertificateMTM(Statement stmt) throws SQLException {

		ResultSet rs = stmt.executeQuery("SELECT * from CERTIFICATE_MTM");
		System.out.println("Fetch Data from CERTIFICATE_MTM");
		while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("certificate_name");
			System.out.println(" id:" + id + " name:" + name);
		}

	}

	public static void fetchDataEmployeeMTM(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_MTM");
		System.out.println("Fetch Data from EMPLOYEE_MTM");
		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			System.out.println(
					" id:" + id + " last_name:" + last_name + " first_name:" + first_name + " salary:" + salary);
		}

	}
}
