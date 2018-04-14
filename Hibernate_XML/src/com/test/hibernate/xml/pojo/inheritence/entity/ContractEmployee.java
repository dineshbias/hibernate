/**
 * 
 */
package com.test.hibernate.xml.pojo.inheritence.entity;

/**
 * @author edinjos
 *
 */
public class ContractEmployee extends Employee {

	private int payPerHour;  
    private String contractDuration;
    
	public ContractEmployee() {
		// TODO Auto-generated constructor stub
	}

	public int getPayPerHour() {
		return payPerHour;
	}

	public void setPayPerHour(int payPerHour) {
		this.payPerHour = payPerHour;
	}

	public String getContractDuration() {
		return contractDuration;
	}

	public void setContractDuration(String contractDuration) {
		this.contractDuration = contractDuration;
	}
	
	
}
