/**
 * 
 */
package com.test.hibernate.xml.transaction;

/**
 * @author dinesh.joshi
 *
 */
public class TestTransfer implements Runnable {

	@Override
	public void run() {

		BankingService bankingService = BankingService.getInstance();
		int fromAccount = 0;
		int toAccount = 1;
		int amount = 100;
		bankingService.debitAccount(fromAccount, amount);
		bankingService.creditAccount(toAccount, amount);
		System.out.print(bankingService.getBalance(fromAccount));
		bankingService.getBalance(toAccount);

	}
}
