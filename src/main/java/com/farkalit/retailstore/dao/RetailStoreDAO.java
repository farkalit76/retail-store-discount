/**
 * 
 */
package com.farkalit.retailstore.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.farkalit.retailstore.constant.StoreConstants;
import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;
import com.farkalit.retailstore.exception.RetailStoreException;
import com.farkalit.retailstore.helper.DateHelper;

/**
 * @File name: RetailStoreDAO.java
 * This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
@Repository
public class RetailStoreDAO implements IRetailStoreDAO{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.farkalit.restailstore.dao.RetailStoreDAOIntf#getUser(java.lang.String)
	 */
	public StoreUser getUser(String userId) throws RetailStoreException {
		List<StoreUser> users = getUsers();
		for (StoreUser user : users) {
			if(user.getUserId().equals(userId))
			{
				System.out.println("User found for Id:"+userId);
				return user;
			}
		}
		System.out.println("User NOT found for Id:"+userId);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.farkalit.restailstore.dao.RetailStoreDAOIntf#getProduct()
	 */
	public List<Product> getProducts() throws RetailStoreException {
		
		System.out.println("DAO getProduct started...");
		
		List<Product> products = new ArrayList<>();
		
		Product prod1 = new Product("pr101", "Laptop", StoreConstants.PROD_ELECTRONICS, 2000.00);
		Product prod2 = new Product("pr102", "AirCondition", StoreConstants.PROD_ELECTRONICS, 3500.00);
		Product prod3 = new Product("pr103", "Fan", StoreConstants.PROD_ELECTRONICS, 500.00);
		Product prod4 = new Product("pr104", "Apple", StoreConstants.PROD_GROCERIES, 50.00);
		Product prod5 = new Product("pr105", "Shirt", StoreConstants.PROD_CLOTHES, 250.00);
		Product prod6 = new Product("pr106", "Blanket", StoreConstants.PROD_CLOTHES, 1000.00);
		
		products.add(prod1);products.add(prod2);products.add(prod3);
		products.add(prod4);products.add(prod5);products.add(prod6);
		
		return products;
	}

	
	private static List<StoreUser> getUsers() {
		
		List<StoreUser> users = new ArrayList<>();
		
		StoreUser user1 = new StoreUser("ABC101", "Usman", StoreConstants.USER_EMPLOYEE, DateHelper.getDate(2005, 10, 20));
		StoreUser user2 = new StoreUser("ABC102", "Harsh", StoreConstants.USER_AFFILIATE, DateHelper.getDate(2005, 10, 20));
		StoreUser user3 = new StoreUser("ABC103", "Fasin", StoreConstants.USER_CUSTOMER, DateHelper.getDate(2017, 05, 05));
		StoreUser user4 = new StoreUser("ABC104", "Monika", StoreConstants.USER_CUSTOMER, DateHelper.getDate(2015, 05, 05));
		StoreUser user5 = new StoreUser("ABC105", "Masroor", StoreConstants.USER_OTHER, DateHelper.getDate(2017, 05, 05));
		StoreUser user6 = new StoreUser("ABC105", "Mixed", StoreConstants.USER_OTHER, DateHelper.getDate(2017, 05, 05));
		
		users.add(user1); users.add(user2);users.add(user3);
		users.add(user4);users.add(user5);users.add(user6);
		
		return users;
	}
}
