/**
 * 
 */
package com.farkalit.retailstore.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.farkalit.retailstore.dao.RetailStoreDAO;
import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;
import com.farkalit.retailstore.exception.RetailStoreException;

/**
 * @File name: RetailStoreDAOTest.java This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 27 May 2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailStoreDAOTest {

	@Autowired
	private RetailStoreDAO retailStoreDao;

	@Test
	public void testGetProduct() {
		try {
			List<Product> products = retailStoreDao.getProducts();
			assertEquals(6, products.size());
		} catch (RetailStoreException e) {
			fail();
		}
	}

	@Test
	public void testGetUser() {
		try {
			StoreUser user = retailStoreDao.getUser("ABC101");
			assertEquals("Usman", user.getName());
		} catch (RetailStoreException e) {
			fail();
		}
	}
}
