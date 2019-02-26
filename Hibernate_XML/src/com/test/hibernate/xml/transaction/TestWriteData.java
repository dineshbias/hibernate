/**
 * 
 */
package com.test.hibernate.xml.transaction;

import java.util.ArrayList;
import java.util.List;

import com.test.hibernate.xml.transaction.ManageEmployee;
import com.test.hibernate.xml.transaction.Employee;

import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * @author dinesh.joshi
 *
 */
public class TestWriteData implements Runnable {

	@Override
	public void run() {

		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee("Zara1", "Ali1", 1000));
		empList.add(new Employee("Daisy1", "Das1", 5000));
		empList.add(new Employee("John1", "Paul1", 10000));
		empList.add(new Employee("Ramesh", "yyy", 100));

		for (int i = 0; i < 4; i++) {
			Session session = ManageEmployee.factory.openSession();
			try {

				System.out.println(Thread.currentThread() +" \n" + this.getClass() + " addEmployee.. " + session);
				Transaction tx = null;
				Integer employeeId = null;

				tx = session.beginTransaction();
				Employee e = empList.get(i);
				System.out.println(Thread.currentThread() +" Begin transaction.... " + e.getId());
				session.save(e);
				System.out.println(Thread.currentThread() +" commiting transaction.... " + e.getId());
				tx.commit();

				System.out.println(e.getId());

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				System.out.println(Thread.currentThread() +" Closing session.");
				session.close();
			}

		}
	}
}
