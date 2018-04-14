package com.test.hibernate.xml.pojo.mapping.component.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.test.hibernate.xml.pojo.mapping.component.Address;
import com.test.hibernate.xml.pojo.mapping.component.Employee;

public class ManageEmployee {

	private static SessionFactory factory;
	private static List<Employee> empList;
	private static List<Integer> empIdList;

	private static Address address;
	

	static {
		System.out.println(ManageEmployee.class + " static block loading...");
		Configuration config  = new Configuration().configure("hibernate.cfg.xml");
				
		factory = config.buildSessionFactory();
		System.out.println(ManageEmployee.class
				+ " SessionFactory initialized...");

		empList = new ArrayList<Employee>();
		empList.add(new Employee("Zara1", "Ali1", 1000));
		empList.add(new Employee("Daisy1", "Das1", 5000));
		empList.add(new Employee("John1", "Paul1", 10000));

		address = new Address("Subhash Nagar", "Roorkee", "UTTARAKHAND",
				"247667");

	}

	public ManageEmployee() {
		System.out.println(this.getClass()
				+ "********* Instantiated **********");
	}

	public static void main(String... args) throws IOException {
		System.out.println("\n" + ManageEmployee.class + " main enter...");
		testEmployeeDB();
		System.out.println(ManageEmployee.class + " main exit...");
	}

	public static void testEmployeeDB() {
		System.out.println("\n" + ManageEmployee.class
				+ " testEmployeeDB enter...");
		ManageEmployee me = new ManageEmployee();

		me.listEmployees();
		

		int count = 0;

		
		while (count < empList.size()) {

			me.addEmployee(empList.get(count), address);

			count++;
		}

		/*
		 * for (Employee emp : empList) { me.addEmployee(emp); }
		 */

		me.listEmployees();
		

		me.updateEmployee(44, 9999);

		me.listEmployees();
		

		
		for (int id : empIdList) {
			me.deleteEmployee(id);
		}
		
		me.testDeleteEmployee("er");
		
		me.listEmployees();
		System.out.println(ManageEmployee.class + " testEmployeeDB exit...");
	}

	// Method to read all the employees
	public void listEmployees() {
		
		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " listEmployees.... "
				+ session);
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			
			Query query = session
					.createQuery("FROM com.test.hibernate.xml.pojo.mapping.component.Employee");
			System.out.println("Query Created....");
			
			List<Employee> employees = query.list();
			System.out.println("********");
			System.out.println(this.getClass() + " listEmployees.... "
					+ employees.size());
			System.out.println("********");
			empIdList = new ArrayList<>();

			for (Employee employee : employees) {
				empIdList.add(employee.getId());
				System.out.print(" Id: " + employee.getId());
				System.out.print(" First Name: " + employee.getFirstName());
				System.out.print(" Last Name: " + employee.getLastName());
				System.out.println(" Salary: " + employee.getSalary());
				System.out.println(" Address: " + employee.getAddress());
			}
			System.out.println("commiting transaction....");
			tx.commit();
		} finally {
			session.close();
		}
	}

	

	

	// Method to create an employee in database
	public Integer addEmployee(Employee employee, Address address) {

		Session session = factory.openSession();
		System.out
				.println("\n" + this.getClass() + " addEmployee.. " + session.hashCode());
		Transaction tx = null;
		Integer employeeId = null;
		employee.setAddress(address);
		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			session.save(employee);
			System.out.println("commiting transaction....");
			tx.commit();
			
			System.out.println(tx.hashCode());
		} finally {
			session.close();
		}
		return employeeId;
	}

	// Method to update Salary of employee
	public void updateEmployee(int id, int salary) {
		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " updateEmployee.... "
				+ session);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			Employee employee = (Employee) session.get(Employee.class, id);
			Employee employee2 = (Employee) session.get(Employee.class, id+1);
			if (employee == null) {
				System.out.println(this.getClass()
						+ "**** employee not found with Id " + id);
				return;
			}
			System.out.println(this.getClass() + " updateEmployee.... "
					+ employee);
			employee.setSalary(salary);
			employee2.setSalary(salary);
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
		System.out.println("\n" + this.getClass() + " deleteEmployee.... " + id
				+ " " + session);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			Employee employee = session.get(Employee.class, id);
			Employee employee2 = session.get(Employee.class, id+1);
			System.out.println(this.getClass() + " deleteEmployee.... "
					+ employee);
			session.delete(employee);
			System.out.println("commiting transaction....");
			tx.commit();

		} finally {
			session.close();
		}
	}

	public void testDeleteEmployee(String name) {
		System.out.println("testDeleteEmployee Enter....");
		
		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " testDeleteEmployee.... " + name
				+ " " + session);
		Transaction tx = session.beginTransaction();
		
		String hql = "DELETE from com.test.hibernate.xml.pojo.mapping.component.Employee e where e.firstName=:employeeName";
		Query query = session.createQuery(hql);
		query.setParameter("employeeName", name);

		int rowsDeleted = query.executeUpdate();

		System.out.println("Rows Deleted.... " + rowsDeleted);
		
		tx.commit();
		session.close();
		System.out.println("testUpdateSalary Exit ....");
	}
}
