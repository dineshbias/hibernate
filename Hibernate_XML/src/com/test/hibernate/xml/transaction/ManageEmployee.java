package com.test.hibernate.xml.transaction;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {

	protected static SessionFactory factory;

	static {
		System.out.println(ManageEmployee.class + " static block loading...");
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		System.out.println(ManageEmployee.class + " SessionFactory initialized...");

	}

	public ManageEmployee() {
		System.out.println(this.getClass() + "********* Instantiated **********");
	}

	public static void main(String... args) throws IOException {
		System.out.println("\n" + ManageEmployee.class + " main enter...");

		ExecutorService execuService = Executors.newFixedThreadPool(5);

		try {

			execuService.execute(new TestReadData());
			execuService.execute(new TestWriteData());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			execuService.shutdown();
		}
		System.out.println(ManageEmployee.class + " main exit...");
	}

}
