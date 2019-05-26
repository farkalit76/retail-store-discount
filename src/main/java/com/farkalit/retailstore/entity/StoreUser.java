/**
 * 
 */
package com.farkalit.retailstore.entity;

import java.util.Date;

/**
 * @File name: StoreUser.java
 * This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
public class StoreUser {

	private String userId;
	
	private String name;
	
	private String type; // employee, affiliate, customer, other
	
	private Date creationDate;

	public StoreUser() {}
	
	public StoreUser(String userId, String name, String type, Date creationDate) {
		super();
		this.userId = userId;
		this.name = name;
		this.type = type;
		this.creationDate = creationDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", type=" + type + ", creationDate=" + creationDate + "]";
	}

}
