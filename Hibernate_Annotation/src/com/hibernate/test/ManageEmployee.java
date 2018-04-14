package com.hibernate.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.metadata.Employee;
import com.hibernate.metadata.EmployeeTemp;

public class ManageEmployee {

	private static SessionFactory factory;
	private static List<Employee> empList;
	private static List<Integer> empIdList;

	static {
		System.out.println(ManageEmployee.class + " static block loading...");

		Configuration config = new Configuration();
		
		config.addAnnotatedClass(Employee.class);
		config.addAnnotatedClass(EmployeeTemp.class);

		Properties configProperties = new Properties();
		configProperties.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
		configProperties.put("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
		configProperties.put("hibernate.connection.url", "jdbc:derby:testAnnotationDB;user=dinesh;password=joshi;create=true");
		configProperties.put("hibernate.connection.username", "dinesh");
		configProperties.put("hibernate.connection.password", "joshi");
		configProperties.put("hibernate.default_schema", "dinesh");
		configProperties.put("hibernate.connection.pool_size", "10");
		configProperties.put("hibernate.show_sql", "true");
		configProperties.put("hibernate.format_sql", "true");
		
		config.setProperties(configProperties);
		
		factory = config.buildSessionFactory();
		System.out.println(ManageEmployee.class + " SessionFactory initialized...");

		empList = new ArrayList<Employee>();
		empList.add(new Employee("Zara1", "Ali1", 5000));
		empList.add(new Employee("Daisy1", "Das1", 10000));
		empList.add(new Employee("John1", "Paul1", 1000));

	}

	public ManageEmployee() {
		System.out.println(this.getClass() + "********* Instantiated **********");
	}

	public static void main(String... args) throws IOException {
		System.out.println("\n" + ManageEmployee.class + " main enter...");
		testEmployeeDB();
		System.out.println(ManageEmployee.class + " main exit...");
		factory.close();
	}

	public static void testEmployeeDB() {
		System.out.println("\n" + ManageEmployee.class + " testEmployeeDB enter...");
		ManageEmployee me = new ManageEmployee();

		me.listEmployees();

		int count = 0;

		while (count < empList.size()) {

			me.addEmployee(empList.get(count));

			count++;
		}

		me.listEmployees();

		me.updateEmployee(311, 9999);

		me.listEmployees();

		// TestHQL.testALL(factory);
		// TestCriteria.testALL(factory);
		TestNativeSQL.testALL(factory);

		for (int id : empIdList) {
			me.deleteEmployee(id);
		}

		me.listEmployees();

		System.out.println(ManageEmployee.class + " testEmployeeDB exit...");
	}

	// Method to read all the employees
	public void listEmployees() {

		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " listEmployees.... " + session);
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");

			Query query = session.createQuery("FROM com.hibernate.metadata.Employee");
			System.out.println("Query Created....");

			List<Employee> employees = query.list();
			System.out.println(this.getClass() + " listEmployees.... " + employees.size());
			empIdList = new ArrayList<>();

			for (Employee employee : employees) {
				empIdList.add(employee.getId());
				System.out.print(" Id: " + employee.getId());
				System.out.print(" First Name: " + employee.getFirstName());
				System.out.print(" Last Name: " + employee.getLastName());
				System.out.println(" Salary: " + employee.getSalary());
				// employee.setFirstName("Chaman");
				// employee.setLastName("Chutiya");
				// employee.setSalary(0);

			}
			System.out.println("commiting transaction....");
			tx.commit();
		} finally {
			session.close();
		}
	}

	// Method to create an employee in database
	public Integer addEmployee(Employee employee) {

		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " addEmployee.. " + session);
		Transaction tx = null;
		Integer employeeId = null;

		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			session.save(employee);
			System.out.println("commiting transaction....");
			tx.commit();
		} finally {
			session.close();
		}
		return employeeId;
	}

	// Method to update Salary of employee
	public void updateEmployee(int id, int salary) {
		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " updateEmployee.... " + session);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			Employee employee = (Employee) session.get(Employee.class, id);
			// Employee employee = (Employee) session.load(Employee.class, id);
			if (employee == null) {
				System.out.println(this.getClass() + "**** employee not found with Id " + id);
				return;
			}
			System.out.println(this.getClass() + " updateEmployee.... " + employee);
			employee.setSalary(salary);
			session.update(employee);
			System.out.println("commiting transaction....");
			tx.commit();
		} finally {
			session.close();
		}
	}

	// Method to delete the employee record based on id
	public void deleteEmployee(int id) {
		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " deleteEmployee.... " + id + " " + session);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			Employee employee = session.get(Employee.class, id);
			System.out.println(this.getClass() + " deleteEmployee.... " + employee);
			session.delete(employee);
			System.out.println("commiting transaction....");
			tx.commit();

		} finally {
			session.close();
		}
	}

}
