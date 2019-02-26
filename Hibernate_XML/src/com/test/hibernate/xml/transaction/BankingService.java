/**
 * 
 */
package com.test.hibernate.xml.transaction;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author dinesh.joshi
 *
 */
public class BankingService {

	private static volatile BankingService bankingService;

	private BankingService() {

	}

	public static BankingService getInstance() {
		if (null == bankingService) {
			synchronized (BankingService.class) {
				if (null == bankingService) {
					bankingService = new BankingService();
				}
			}

		}
		return bankingService;
	}

	private enum trsType {
		DEBIT, CREDIT
	};

	public Account createAccount(String name) {
		Session session = ManageEmployee.factory.openSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();
			System.out.println(Thread.currentThread() + " Begin transaction....");

			Account account = new Account();
			account.setCurrentBalance(1000000);
			account.setName(name);

			System.out.println(
					Thread.currentThread() + " Account.............................................. " + account);

			session.save(account);
			tx.commit();
			System.out.println(Thread.currentThread()
					+ " Account Created.............................................. " + account);
			return account;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			System.out.println(Thread.currentThread() + " Closing session.");
			session.close();
		}
		throw new RuntimeException();
	}

	public void debitAccount(int accountId, int amount) {
		Session session = ManageEmployee.factory.openSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();
			System.out.println(Thread.currentThread() + " Begin transaction....");

			Account account = session.get(Account.class, accountId);

			System.out.println(
					Thread.currentThread() + " Account.............................................. " + account);

			int currentBalance = account.getCurrentBalance();
			int newBalance = currentBalance - amount;
			account.setCurrentBalance(newBalance);

			TransactionHistory trs = new TransactionHistory(accountId, currentBalance, newBalance, amount,
					trsType.DEBIT.name(), new Date());
			session.save(trs);
			session.update(account);

			tx.commit();
			System.out.println(Thread.currentThread()
					+ " Commited DEBIT Transaction for Account.............................................. "
					+ account);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			System.out.println(Thread.currentThread() + " Closing session.");
			session.close();
		}
	}

	public void creditAccount(int accountId, int amount) {
		Session session = ManageEmployee.factory.openSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();
			System.out.println(Thread.currentThread() + " Begin transaction....");

			Account account = session.get(Account.class, accountId);

			System.out.println(
					Thread.currentThread() + " Account.............................................. " + account);

			int currentBalance = account.getCurrentBalance();
			int newBalance = currentBalance + amount;
			account.setCurrentBalance(newBalance);

			TransactionHistory trs = new TransactionHistory(accountId, currentBalance, newBalance, amount,
					trsType.CREDIT.name(), new Date());
			session.save(trs);
			session.update(account);

			tx.commit();
			System.out.println(Thread.currentThread()
					+ " Commited CREDIT Transaction for Account.............................................. "
					+ account);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			System.out.println(Thread.currentThread() + " Closing session.");
			session.close();
		}
	}

	public int getBalance(int accountId) {
		Session session = ManageEmployee.factory.openSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();
			System.out.println(Thread.currentThread() + " Begin transaction....");

			Account account = session.get(Account.class, accountId);

			System.out.println(
					Thread.currentThread() + " Account.............................................. " + account);

			tx.commit();

			account.getCurrentBalance();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread() + " Closing session.");
			session.close();
		}
		throw new RuntimeException();
	}

}
