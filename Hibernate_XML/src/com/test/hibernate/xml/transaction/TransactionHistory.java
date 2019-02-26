/**
 * 
 */
package com.test.hibernate.xml.transaction;

import java.util.Date;

/**
 * @author dinesh.joshi
 *
 */
public class TransactionHistory {
	
	private int id;
	private int accountId;
	private int balanceBefore;
	private int balanceAfter;
	private int transactionAmount;
	private String type;
	private Date transactionDate;
	

	/**
	 * 
	 */
	public TransactionHistory() {
		// TODO Auto-generated constructor stub
	}

	public TransactionHistory(int accountId, int balanceBefore, int balanceAfter, int transactionAmount,
			String type, Date transactionDate) {
		super();
		this.accountId = accountId;
		this.balanceBefore = balanceBefore;
		this.balanceAfter = balanceAfter;
		this.transactionAmount = transactionAmount;
		this.type = type;
		this.transactionDate = transactionDate;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public int getBalanceBefore() {
		return balanceBefore;
	}


	public void setBalanceBefore(int balanceBefore) {
		this.balanceBefore = balanceBefore;
	}


	public int getBalanceAfter() {
		return balanceAfter;
	}


	public void setBalanceAfter(int balanceAfter) {
		this.balanceAfter = balanceAfter;
	}


	public int getTransactionAmount() {
		return transactionAmount;
	}


	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Date getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}


	
	
}
