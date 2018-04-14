package com.hibernate.metadata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_TEMP")
public class EmployeeTemp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "salary")
	private int salary;

	public EmployeeTemp() {
		System.out.println(this + " instantiated.... ");
	}

	public EmployeeTemp(String fname, String lname, int salary) {

		this.firstName = fname;
		this.lastName = lname;
		this.salary = salary;
		System.out.println(this + " instantiated... " + " " + firstName + " "
				+ lastName + " " + salary);
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

	@Override
	public String toString() {

		return firstName + " " + lastName + " " + salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
