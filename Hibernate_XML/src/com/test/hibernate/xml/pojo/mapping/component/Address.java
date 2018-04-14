/**
 * 
 */
package com.test.hibernate.xml.pojo.mapping.component;

/**
 * @author edinjos
 *
 */
public class Address {

	private String streetName;
	private String cityName;
	private String stateName;
	private String zipcode;

	public Address() {
		System.out.println(this + " instantiated....");
	}

	public Address(String streetName, String cityName, String stateName,
			String zipcode) {
		this.streetName = streetName;
		this.cityName = cityName;
		this.stateName = stateName;
		this.zipcode = zipcode;
		System.out.println(this + " instantiated.... " + " " + stateName + " "
				+ cityName + " " + stateName + " " + zipcode);
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
		return " stateName:" + stateName + " cityName:" + cityName
				+ " stateName:" + stateName + " zipcode:" + zipcode;
	}
}
