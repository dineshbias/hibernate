package com.test.hibernate.xml.pojo.inheritence;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.test.hibernate.xml.pojo.inheritence.entity.ContractEmployee;
import com.test.hibernate.xml.pojo.inheritence.entity.Employee;
import com.test.hibernate.xml.pojo.inheritence.entity.RegularEmployee;

public class TestInheritence {

	private static SessionFactory factory;

	static {
		Configuration config = new Configuration().configure("hibernateConfBatch.cfg.xml");
		factory = config.buildSessionFactory();
		System.out.println(
				Thread.currentThread().getName() + " " + TestInheritence.class + " SessionFactory initialized...");
	}

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " " + TestInheritence.class + " main started ");
		printNumberOfRows();
		testInserts();
		printNumberOfRows();
		printAllRows();
		deleteRecords();
		printNumberOfRows();
		System.out.println(Thread.currentThread().getName() + " " + TestInheritence.class + " closing factory ");
		factory.close();
		System.out.println(Thread.currentThread().getName() + " " + TestInheritence.class + " main exit ");
	}

	public static void printNumberOfRows() {
		

		Session session = factory.openSession();
		try {
			Query query = session
					.createQuery("SELECT count(*) FROM com.test.hibernate.xml.pojo.inheritence.entity.Employee");
			List<Object> count = query.list();
			System.out
			.println(Thread.currentThread().getName() + " " + TestInheritence.class + " printNumberOfRows .. " + count);

		} finally {
			System.out
					.println(Thread.currentThread().getName() + " " + TestInheritence.class + " Closing session ");
			session.close();
		}

	}
	
	public static void printAllRows() {
		

		Session session = factory.openSession();
		try {
			Query query = session
					.createQuery("FROM com.test.hibernate.xml.pojo.inheritence.entity.Employee");
			List<Employee> employees = query.list();
			
			
			System.out
			.println(Thread.currentThread().getName() + " " + TestInheritence.class + " employees .. " + employees);

		} finally {
			System.out
					.println(Thread.currentThread().getName() + " " + TestInheritence.class + " Closing session ");
			session.close();
		}

	}
	public static void testInserts() {
		System.out.println(Thread.currentThread().getName() + " " + TestInheritence.class + " insertRecords .. "
				);

		System.out.println(Thread.currentThread().getName() + " " + "Opening Session");
		Session session = factory.openSession();
		System.out.println(Thread.currentThread().getName() + " " + "Begin Transaction");
		Transaction trs = session.beginTransaction();
		
		Employee e1 = new Employee();
		//e1.setId(1);
		e1.setName("Raja");
		e1.setType("Owner");
		
		session.persist(e1);
		
		ContractEmployee cEmployee = new ContractEmployee();
		cEmployee.setContractDuration("18");
		cEmployee.setName("Ashish");
		cEmployee.setPayPerHour(50);
		cEmployee.setType("Contract");
		
		session.persist(cEmployee);
		
		RegularEmployee rEmployee = new RegularEmployee();
		rEmployee.setBonus(5000);
		rEmployee.setName("Ambani");
		rEmployee.setSalary(4000);
		rEmployee.setType("Regular");
		
		session.persist(rEmployee);
		
		System.out.println(Thread.currentThread().getName() + " " + "Commiting Transaction");
		trs.commit();
		System.out.println(Thread.currentThread().getName() + " " + "Closing Session");
		session.close();
	}

	public static void deleteRecords() {
		System.out.println(Thread.currentThread().getName() + " " + TestInheritence.class + " deleteRecords .. ");

		System.out.println(Thread.currentThread().getName() + " " + "Opening Session");
		Session session = factory.openSession();
		System.out.println(Thread.currentThread().getName() + " " + "Begin Transaction");
		Transaction trs = session.beginTransaction();

		Query query = session.createQuery("DELETE FROM com.test.hibernate.xml.pojo.inheritence.entity.Employee");
		int rowsCount = query.executeUpdate();

		System.out.println(Thread.currentThread().getName() + " " + "rowsCount : " + rowsCount);
		System.out.println(Thread.currentThread().getName() + " " + "Commiting Transaction");
		trs.commit();
		System.out.println(Thread.currentThread().getName() + " " + "Closing Session");
		session.close();
	}
}
