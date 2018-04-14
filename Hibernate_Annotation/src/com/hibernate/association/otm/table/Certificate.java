/**
 * 
 */
package com.hibernate.association.otm.table;

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
@Table(name = "CERTIFICATE_OTM_3")
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="certificate_id")
	private int certificateId;

	@Column(name = "certificate_name")
	private String name;

	/**
	 * 
	 */
	public Certificate() {
		System.out.println(" Instantiated....");
	}

	public Certificate(String name) {
		System.out.println(" Instantiated.... " + name);
		this.name = name;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println(" setName.... " + name);
		this.name = name;
	}

	public boolean equals(Object obj) {
		System.out.println(" equals.... " + obj);
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

	public int hashCode() {
		System.out.println(" hashCode.... ");
		return name.hashCode();
	}

	public int getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(int certificateId) {
		this.certificateId = certificateId;
	}

	
}
