/**
 * 
 */
package com.test.hibernate.xml.pojo.mapping.association.oto.nfka;


/**
 * @author edinjos
 *
 */
public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private int salary;

	private Address addr;

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public Employee() {
		System.out.println(this + " instantiated...");
	}

	public Employee(String fname, String lname, int salary) {
		System.out.println(this + " instantiated... " + fname + " " + lastName
				+ " " + salary);
		this.firstName = fname;
		this.lastName = lname;
		this.salary = salary;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		System.out.println(this + " setId... " + id);
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		System.out.println(this + " setFirstName... " + firstName);
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		System.out.println(this + " setLastName... " + lastName);
		this.lastName = lastName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		System.out.println(this + " setSalary... " + salary);
		this.salary = salary;
	}

	

	/*
	 * @Override public String toString() { return "id:" + id + " firstName:" +
	 * firstName + " lastName:" + lastName + " salary:" + salary + certificates;
	 * }
	 */
}
