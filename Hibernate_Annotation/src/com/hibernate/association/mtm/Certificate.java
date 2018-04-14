/**
 * 
 */
package com.hibernate.association.mtm;

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
@Table(name = "CERTIFICATE_MTM")
public class Certificate {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "certificate_name")
	private String name;

	/**
	 * 
	 */
	public Certificate() {
		System.out.println(this + " Instantiated....");
	}

	public Certificate(String name) {
		System.out.println(this + " Instantiated.... " + name);
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		System.out.println(this + " setId.... " + id);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println(this + " setName.... " + name);
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println(this + " equals.... " + obj);
		if (null == obj) {
			return false;
		}
		if (!(obj instanceof Certificate)) {
			return false;
		}
		Certificate obj2 = (Certificate) obj;

		if (this.getName().equals(obj2.getName()))
			return true;

		return false;
	}

	@Override
	public int hashCode() {
		System.out.println(" hashCode.... ");
		return name == null ? 0 : name.hashCode();
	}
}
