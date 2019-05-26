/**
 * 
 */
package com.farkalit.retailstore.service;

import java.util.List;

import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;
import com.farkalit.retailstore.exception.RetailStoreException;

/**
 * @File name: RetailStoreServiceIntf.java
 * This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
public interface IRetailStoreService {
	
	public StoreUser getUser(String userId) throws RetailStoreException;
	public List<Product> getProducts() throws RetailStoreException;
}
