/**
 * 
 */
package com.test.hibernate.xml.pojo.mapping.collection.set;

/**
 * @author edinjos
 *
 */
public class Certificate {

	private int id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		System.out.println(" setId.... " + id);
		this.id = id;
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
}
