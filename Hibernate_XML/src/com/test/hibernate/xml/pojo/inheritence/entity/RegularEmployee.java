package com.test.hibernate.xml.pojo.inheritence.entity;

public class RegularEmployee extends Employee {

	private int salary;  
	private int bonus;  
	
	public RegularEmployee() {
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	
}
