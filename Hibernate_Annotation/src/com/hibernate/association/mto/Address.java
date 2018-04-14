/**
 * 
 */
package com.hibernate.association.mto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author edinjos
 *
 */
@Entity
@Table(name = "ADDRESS_MTO")
public class Address {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "street_name")
	private String streetName;

	@Column(name = "city_name")
	private String cityName;

	@Column(name = "state_name")
	private String stateName;

	@Column(name = "zipcode")
	private String zipcode;

	public Address() {
		System.out.println(this + " instantiated....");
	}

	public Address(String streetName, String cityName, String stateName, String zipcode) {
		this.streetName = streetName;
		this.cityName = cityName;
		this.stateName = stateName;
		this.zipcode = zipcode;
		System.out.println(
				this + " instantiated.... " + id + " " + stateName + " " + cityName + " " + stateName + " " + zipcode);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return id + " stateName:" + stateName + " cityName:" + cityName + " stateName:" + stateName + " zipcode:"
				+ zipcode;
	}
}
