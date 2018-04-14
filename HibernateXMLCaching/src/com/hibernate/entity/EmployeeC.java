package com.hibernate.entity;

public class EmployeeC {

	private int id;
	private String firstName;
	private String lastName;
	private int salary;

	public EmployeeC() {
		System.out.println(EmployeeC.class.getSimpleName()
				+ " instantiated... ");
	}

	public EmployeeC(String fname, String lname, int salary) {
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
		
		this.id = id;
	}

	public String getFirstName() {
		
		return firstName;
	}

	public void setFirstName(String firstName) {
		
		this.firstName = firstName;
	}

	public String getLastName() {
		
		return lastName;
	}

	public void setLastName(String lastName) {
		
		this.lastName = lastName;
	}

	public int getSalary() {
		
		return salary;
	}

	public void setSalary(int salary) {
		
		this.salary = salary;
	}

	public String toString() {
		return id + " " + firstName + " " + lastName + " " + salary;
	}
}
