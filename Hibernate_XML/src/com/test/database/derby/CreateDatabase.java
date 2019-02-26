/**
 * 
 */
package com.test.database.derby;

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

	private final static String CREATE_ACCOUNTS = "create table Accounts"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)"
			+ " , name VARCHAR(40) default NULL, current_balance INT default NULL, PRIMARY KEY (id) )";

	private final static String CREATE_TRANSACTION_HISTORY = "CREATE TABLE Transaction_History(id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)"
			+ ", account_id integer references Accounts(id), balance_before integer, balance_after integer , transaction_amount integer , type VARCHAR(255), transaction_date TIMESTAMP)";

	private final static String CREATE_SPECIES = "CREATE TABLE species( " + "id INTEGER PRIMARY KEY,"
			+ " name VARCHAR(255) , " + "num_acres DECIMAL)";
	private final static String CREATE_ANIMAL = "CREATE TABLE animal(id INTEGER PRIMARY KEY, species_id integer , name VARCHAR(255), date_born TIMESTAMP)";

	private final static String CREATE_EMPLOYEE = "create table EMPLOYEE_SIMPLE"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)"
			+ " , first_name VARCHAR(20) default NULL, last_name VARCHAR(20) default NULL, salary INT default NULL, PRIMARY KEY (id) )";

	private final static String CREATE_EMPLOYEE_LIST = "create table EMPLOYEE"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)"
			+ " , first_name VARCHAR(20) default NULL, last_name VARCHAR(20) default NULL, salary INT default NULL, PRIMARY KEY (id) )";

	private final static String CREATE_CERTIFICATE_LIST = "create table CERTIFICATE"
			+ "( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 10000,INCREMENT BY 1), "
			+ "certificate_name VARCHAR(30) default NULL,"
			+ "idx INT default NULL, employee_id INT default NULL, PRIMARY KEY (id))";

	private final static String CREATE_EMPLOYEE_SET = "create table EMPLOYEE_SET"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)"
			+ " , first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, PRIMARY KEY (id) )";

	// It is not required to create a column with idx INT. This has been done to
	// test the difference when idx column is created for both list and set
	// mappings.
	private final static String CREATE_CERTIFICATE_SET = "create table CERTIFICATE_SET"
			+ "( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 10000,INCREMENT BY 1), "
			+ "certificate_name VARCHAR(30) default NULL," + "idx INT default NULL, "
			+ "employee_id INT default NULL, PRIMARY KEY (id))";

	private final static String CREATE_EMPLOYEE_BAG = "create table EMPLOYEE_BAG"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)"
			+ " , first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "PRIMARY KEY (id) )";

	private final static String CREATE_CERTIFICATE_BAG = "create table CERTIFICATE_BAG"
			+ "( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 10000,INCREMENT BY 1), "
			+ "certificate_name VARCHAR(30) default NULL," + "employee_id INT default NULL, " + "PRIMARY KEY (id))";

	private final static String CREATE_EMPLOYEE_MAP = "create table EMPLOYEE_MAP"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)"
			+ " , first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "PRIMARY KEY (id) )";

	private final static String CREATE_CERTIFICATE_MAP = "create table CERTIFICATE_MAP"
			+ "( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 10000,INCREMENT BY 1), "
			+ "certificate_name VARCHAR(30) default NULL," + "certificate_type VARCHAR(30) default NULL,"
			+ "employee_id INT default NULL, " + "PRIMARY KEY (id))";

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

	private final static String CREATE_EMPLOYEE_COMPONENT = "create table EMPLOYEE_COMPONENT"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)"
			+ " , first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "street_name VARCHAR(30) default NULL, "
			+ "city_name VARCHAR(30) default NULL, " + "state_name VARCHAR(30) default NULL, "
			+ "zipcode VARCHAR(8) default NULL, " + "PRIMARY KEY (id) )";

	private final static String CREATE_EMPLOYEE_BATCH = "create table EMPLOYEE_BATCH"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)"
			+ " , first_name VARCHAR(20) default NULL, " + "last_name VARCHAR(20) default NULL, "
			+ "salary INT default NULL, " + "street_name VARCHAR(30) default NULL, "
			+ "city_name VARCHAR(30) default NULL, " + "state_name VARCHAR(30) default NULL, "
			+ "zipcode VARCHAR(8) default NULL, " + "PRIMARY KEY (id) )";

	private final static String CREATE_EMPLOYEE_INHERITENCE_TPH = "create table EMPLOYEE_INHERITENCE_TPH"
			+ " ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1)" + " , type VARCHAR(20), "
			+ "name VARCHAR(20) default NULL, " + "salary INT default NULL, " + "bonus INT default NULL, "
			+ "pay_per_hour INT default NULL, " + "contract_duration VARCHAR(30) default NULL, " + "PRIMARY KEY (id) )";

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
		String url = "jdbc:derby:testDB;user=dinesh;password=joshi;create=true";
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			createTables(stmt);
			// insertData(stmt);
			fetchDataCertificateList(stmt);
			fetchDataEmployeeList(stmt);
			fetchDataCertificateSet(stmt);
			fetchDataEmployeeSet(stmt);
			fetchDataCertificateBag(stmt);
			fetchDataEmployeeBag(stmt);
			fetchDataCertificateMap(stmt);
			fetchDataEmployeeMap(stmt);
			fetchDataAddressMTO(stmt);
			fetchDataEmployeeMTO(stmt);
			fetchDataAddressOTO(stmt);
			fetchDataEmployeeOTO(stmt);
			fetchDataEmployeeOTONFKA(stmt);
			fetchDataAddressOTONFKA(stmt);
			fetchDataEmployeeMTM(stmt);
			fetchDataCertificateMTM(stmt);
			fetchDataEmployeeCertificateMTM(stmt);
			fetchDataEmployeeComponent(stmt);
			fetchDataEmployeeBatch(stmt);
			fetchDataEmployeeInheritenceTablePerHierarchy(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createTables(Statement stmt) throws SQLException {
		
		System.out.println("Creating DB");
		stmt.executeUpdate(CREATE_ACCOUNTS);
		stmt.executeUpdate(CREATE_TRANSACTION_HISTORY);
		
		/*
		stmt.executeUpdate(CREATE_EMPLOYEE);

		stmt.executeUpdate(CREATE_SPECIES);
		stmt.executeUpdate(CREATE_ANIMAL);
		stmt.executeUpdate(CREATE_EMPLOYEE_LIST);
		stmt.executeUpdate(CREATE_CERTIFICATE_LIST);
		stmt.executeUpdate(CREATE_EMPLOYEE_SET);
		stmt.executeUpdate(CREATE_CERTIFICATE_SET);
		stmt.executeUpdate(CREATE_EMPLOYEE_BAG);
		stmt.executeUpdate(CREATE_CERTIFICATE_BAG);
		stmt.executeUpdate(CREATE_EMPLOYEE_MAP);
		stmt.executeUpdate(CREATE_CERTIFICATE_MAP);
		stmt.executeUpdate(CREATE_EMPLOYEE_MTO);
		stmt.executeUpdate(CREATE_ADDRESS_MTO);
		stmt.executeUpdate(CREATE_EMPLOYEE_OTO);
		stmt.executeUpdate(CREATE_ADDRESS_OTO);
		stmt.executeUpdate(CREATE_EMPLOYEE_OTO_NFKA);
		stmt.executeUpdate(CREATE_ADDRESS_OTO_NFKA);
		stmt.executeUpdate(CREATE_EMPLOYEE_MTM);
		stmt.executeUpdate(CREATE_CERTIFICATE_MTM);
		stmt.executeUpdate(CREATE_EMPLOYEE_CERTIFICATE_MTM);
		stmt.executeUpdate(CREATE_EMPLOYEE_COMPONENT);
		stmt.executeUpdate(CREATE_EMPLOYEE_BATCH);
		stmt.executeUpdate(CREATE_EMPLOYEE_INHERITENCE_TPH);
		*/
	}

	public static void insertData(Statement stmt) throws SQLException {
		System.out.println("Inserting Data");
		
		stmt.execute("INSERT into species values(1,'African Elephant',7.5)");
		stmt.execute("INSERT into species values(2,'Zebra',1.2)");

		stmt.execute("INSERT into animal values(1,1,'Elsa','2001-05-06 02:15:00')");
		stmt.execute("INSERT into animal values(2,2,'Zelda','2002-08-15 09:15:00')");
		stmt.execute("INSERT into animal values(3,1,'Ester','2002-05-06 03:15:00')");
		stmt.execute("INSERT into animal values(4,1,'Eddie','2010-05-06 07:15:00')");
		stmt.execute("INSERT into animal values(5,2,'Zoe','2005-05-06 08:15:00')");
	
	}

	public static void fetchDataCertificateList(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from CERTIFICATE");
		System.out.println("Fetch Data from CERTIFICATE");
		while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("certificate_name");
			String idx = rs.getString("idx");
			String empId = rs.getString("employee_id");
			System.out.println(" id:" + id + " idx:" + idx + " name:" + name + " empId:" + empId);
		}

	}

	public static void fetchDataEmployeeList(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from Employee");
		System.out.println("Fetch Data from Employee");
		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			System.out.println(
					" id:" + id + " last_name:" + last_name + " first_name:" + first_name + " salary:" + salary);
		}

	}

	public static void fetchDataCertificateSet(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from CERTIFICATE_SET");
		System.out.println("Fetch Data from CERTIFICATE_SET");
		while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("certificate_name");
			String idx = rs.getString("idx");
			String empId = rs.getString("employee_id");
			System.out.println(" id:" + id + " idx:" + idx + " name:" + name + " empId:" + empId);
		}

	}

	public static void fetchDataEmployeeSet(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_SET");
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

	public static void fetchDataCertificateBag(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from CERTIFICATE_BAG");
		System.out.println("Fetch Data from CERTIFICATE_BAG");
		while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("certificate_name");

			String empId = rs.getString("employee_id");
			System.out.println(" id:" + id + " name:" + name + " empId:" + empId);
		}

	}

	public static void fetchDataEmployeeBag(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_BAG");
		System.out.println("Fetch Data from EMPLOYEE_BAG");
		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			System.out.println(
					" id:" + id + " last_name:" + last_name + " first_name:" + first_name + " salary:" + salary);
		}

	}

	public static void fetchDataCertificateMap(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from CERTIFICATE_MAP");
		System.out.println("Fetch Data from CERTIFICATE_MAP");
		while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("certificate_name");
			String type = rs.getString("certificate_type");
			String empId = rs.getString("employee_id");
			System.out.println(" id:" + id + " type:" + type + " name:" + name + " empId:" + empId);
		}

	}

	public static void fetchDataEmployeeMap(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_MAP");
		System.out.println("Fetch Data from EMPLOYEE_MAP");
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

	public static void fetchDataEmployeeComponent(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_COMPONENT");
		System.out.println("Fetch Data from EMPLOYEE_COMPONENT");
		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			String streetName = rs.getString("street_name");
			String cityName = rs.getString("city_name");
			String stateName = rs.getString("state_name");
			String zipcode = rs.getString("zipcode");

			System.out.println(" id:" + id + " last_name:" + last_name + " first_name:" + first_name + " salary:"
					+ salary + " streetName:" + streetName + " cityName:" + cityName + " stateName:" + stateName
					+ " zipcode:" + zipcode);
		}

	}

	public static void fetchDataEmployeeBatch(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_BATCH");
		System.out.println("Fetch Data from EMPLOYEE_BATCH");
		while (rs.next()) {
			String id = rs.getString("id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String salary = rs.getString("salary");
			String streetName = rs.getString("street_name");
			String cityName = rs.getString("city_name");
			String stateName = rs.getString("state_name");
			String zipcode = rs.getString("zipcode");

			System.out.println(" id:" + id + " last_name:" + last_name + " first_name:" + first_name + " salary:"
					+ salary + " streetName:" + streetName + " cityName:" + cityName + " stateName:" + stateName
					+ " zipcode:" + zipcode);
		}

	}

	public static void fetchDataEmployeeInheritenceTablePerHierarchy(Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * from EMPLOYEE_INHERITENCE_TPH");
		System.out.println("Fetch Data from EMPLOYEE_INHERITENCE_TPH");
		while (rs.next()) {
			String id = rs.getString("id");
			String type = rs.getString("type");
			String name = rs.getString("name");
			String salary = rs.getString("salary");
			String bonus = rs.getString("bonus");
			String payPerHour = rs.getString("pay_per_hour");
			String contractDuration = rs.getString("contract_duration");

			System.out.println(" id:" + id + " type:" + type + " name:" + name + " salary:" + salary + " bonus:" + bonus
					+ " payPerHour:" + payPerHour + " contractDuration:" + contractDuration);
		}

	}
}
