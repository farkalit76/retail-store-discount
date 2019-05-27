/**
 * 
 */
package com.farkalit.retailstore.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farkalit.retailstore.dao.RetailStoreDAO;
import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;
import com.farkalit.retailstore.exception.RetailStoreException;

/**
 * @File name: RetailStoreService.java
 * This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
@Service
public class RetailStoreService implements IRetailStoreService {

	@Autowired
	private RetailStoreDAO retailStoreDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(RetailStoreService.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.farkalit.restailstore.service.RetailStoreServiceIntf#getUser(java.lang.
	 * String)
	 */
	public StoreUser getUser(String userId) throws RetailStoreException {
		LOG.info("Service getUser started...");
		return retailStoreDao.getUser(userId);
	}

	/* (non-Javadoc)
	 * @see com.farkalit.restailstore.service.RetailStoreServiceIntf#getProduct()
	 */
	public List<Product> getProducts() throws RetailStoreException {
		LOG.info("Service getProduct started...");
		return retailStoreDao.getProducts();
	}
}
