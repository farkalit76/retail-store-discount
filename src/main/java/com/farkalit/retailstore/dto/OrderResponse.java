/**
 * 
 */
package com.farkalit.retailstore.dto;

import java.util.List;

import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;

/**
 * @File name: OrderResponse.java
 * This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
public class OrderResponse {

private StoreUser storeUser;
	
	private List<Product> products;
	
	private double discountPercentage;
	
	private double discountAmount;
	
	private double grandTotal;
	
	private double nonDiscountedAmount;

	
	public StoreUser getStoreUser() {
		return storeUser;
	}

	public void setStoreUser(StoreUser storeUser) {
		this.storeUser = storeUser;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public double getNonDiscountedAmount() {
		return nonDiscountedAmount;
	}

	public void setNonDiscountedAmount(double nonDiscountedAmount) {
		this.nonDiscountedAmount = nonDiscountedAmount;
	}

	@Override
	public String toString() {
		return "OrderResponse [storeUser=" + storeUser + ", products=" + products + ", discountPercentage=" + discountPercentage + ", discountAmount=" + discountAmount
				+ ", grandTotal=" + grandTotal + ", nonDiscountedAmount=" + nonDiscountedAmount + "]";
	}
}
