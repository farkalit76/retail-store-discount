/**
 * 
 */
package com.farkalit.retailstore.dto;

import java.util.List;

import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;

/**
 * @File name: OrderRequest.java
 * This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
public class OrderRequest {

	private StoreUser storeUser;

	private List<Product> products;

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

	@Override
	public String toString() {
		return "OrderRequest [storeUser=" + storeUser + ", products=" + products + "]";
	}

}
