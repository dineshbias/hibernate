/**
 * 
 */
package com.hibernate.association.mtm;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author edinjos
 *
 */
@Entity
@Table(name="EMPLOYEE_MTM")
public class Employee {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "salary")
	private int salary;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="EMPLOYEE_CERTIFICATE_MTM" , joinColumns = { @JoinColumn(name="employee_id")}, inverseJoinColumns={@JoinColumn(name="certificate_id")} )
	private Set<Certificate> certificates;

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

	public Collection<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(Set<Certificate> certificates) {
		this.certificates = certificates;
		System.out.println(this + " setCertificates... " + certificates);
	}

	/*
	 * @Override public String toString() { return "id:" + id + " firstName:" +
	 * firstName + " lastName:" + lastName + " salary:" + salary + certificates;
	 * }
	 */
}
