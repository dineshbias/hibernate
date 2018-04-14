/**
 * 
 */
package com.test.hibernate.xml.pojo.mapping.collection.bag;

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
}
