/**
 * 
 */
package com.test.hibernate.xml.pojo.batch.entity;

/**
 * @author edinjos
 *
 */
public class EmployeeB {

	private int id;
	private String firstName;
	private String lastName;
	private int salary;
	private String streetName;
	private String cityName;
	private String stateName;
	private String zipcode;

	/**
	 * 
	 */
	public EmployeeB() {
		System.out.println(EmployeeB.class + " instantiated default...");
	}

	public EmployeeB(String firstName, String lastName, int salary,
			String streetName, String cityName, String stateName, String zipcode) {
		System.out.println(EmployeeB.class + " instantiated...");
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.streetName = streetName;
		this.cityName = cityName;
		this.stateName = stateName;
		this.zipcode = zipcode;
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

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return " " + id + firstName + " " + lastName + " " + salary + " "
				+ streetName + " " + cityName + " " + stateName + " " + zipcode;
	}
}
