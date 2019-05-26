/**
 * 
 */
package com.farkalit.retailstore.entity;

/**
 * @File name: Product.java
 * This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
public class Product {

	private String prodId;

	private String name;

	private String type; // electronics, clothes, groceries, other

	private double price;

	public Product() {
	}

	public Product(String prodId, String name, String type, double price) {
		super();
		this.prodId = prodId;
		this.name = name;
		this.type = type;
		this.price = price;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", name=" + name + ", type=" + type + ", price=" + price + "]";
	}
}
