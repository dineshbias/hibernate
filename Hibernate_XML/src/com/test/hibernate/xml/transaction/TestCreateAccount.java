/**
 * 
 */
package com.test.hibernate.xml.transaction;

import java.util.ArrayList;
import java.util.List;


/**
 * @author dinesh.joshi
 *
 */
public class TestCreateAccount implements Runnable {

	@Override
	public void run() {

		List<String> names = new ArrayList<>();
		names.add("Dinesh Joshi");
		names.add("Ramesh Joshi");
		names.add("Mukesh Joshi");
		names.add("Anand Joshi");

		BankingService bankingService = BankingService.getInstance();

		for (String name : names) {

			bankingService.createAccount(name);
			
		}

	}
}
