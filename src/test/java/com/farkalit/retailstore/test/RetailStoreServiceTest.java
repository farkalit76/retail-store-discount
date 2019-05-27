/**
 * 
 */
package com.farkalit.retailstore.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.farkalit.retailstore.dao.RetailStoreDAO;
import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;
import com.farkalit.retailstore.exception.RetailStoreException;
import com.farkalit.retailstore.helper.DateHelper;
import com.farkalit.retailstore.service.RetailStoreService;

/**
 * @File name: RetailStoreServiceTest.java This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailStoreServiceTest {

	@Autowired
	private RetailStoreService retailStoreService;

	@MockBean
	private RetailStoreDAO retailStoreDao;

	@Test
	public void testGetProduct() {
		try {
			RetailStoreService service = new RetailStoreService();
			Mockito.when(retailStoreDao.getProducts()).thenReturn(getProducts());
			List<Product> products = retailStoreService.getProducts();
			assertEquals(1, products.size());
		} catch (RetailStoreException e) {
			fail();
		}
	}

	@Test
	public void testGetUser() {
		try {
			Mockito.when(retailStoreDao.getUser("ABC101")).thenReturn(getUser());
			StoreUser user = retailStoreService.getUser("ABC101");
			assertEquals("Usman", user.getName());
		} catch (RetailStoreException e) {
			fail();
		}
	}

	private List<Product> getProducts() {

		List<Product> products = new ArrayList<>();
		Product prod = new Product();
		prod.setProdId("pr101");
		prod.setName("Laptop");
		prod.setType("ELECTRONICS");
		prod.setPrice(2000);

		products.add(prod);
		return products;
	}

	private StoreUser getUser() {

		StoreUser user = new StoreUser("ABC101", "Usman", "EMPLOYEE", DateHelper.getDate(2005, 10, 19));
		return user;
	}
}
