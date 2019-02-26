/**
 * 
 */
package com.test.hibernate.xml.transaction;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * @author dinesh.joshi
 *
 */
public class TestReadData implements Runnable {

	@Override
	public void run() {

		for (int j = 0; j < 4; j++)
			for (int i = 0; i < 100; i++)
				System.out.print("*");

		for (int i = 0; i < 10; i++) {
			Session session = ManageEmployee.factory.openSession();
			Transaction tx = null;

			try {

				tx = session.beginTransaction();
				System.out.println(Thread.currentThread() + " Begin transaction....");
				Query query = session.createQuery(" FROM com.test.hibernate.xml.transaction.Employee");
				List<Employee> employee = query.list();
				System.out.println(this.getClass() + " listEmployees.............................................. "
						+ employee.size());

				tx.commit();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				System.out.println(Thread.currentThread() + " Closing session.");
				session.close();
			}

		}
	}
}
