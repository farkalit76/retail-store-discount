/**
 * 
 */
package com.farkalit.retailstore.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.farkalit.retailstore.dto.OrderRequest;
import com.farkalit.retailstore.dto.OrderResponse;
import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;
import com.farkalit.retailstore.exception.RetailStoreException;
import com.farkalit.retailstore.helper.DateHelper;
import com.farkalit.retailstore.helper.OrderHelper;

/**
 * @File name: OrderHelperTest.java This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderHelperTest {

	@Test
	public void testOrderResponse() {
		try {
			OrderHelper.applyDiscount(getOrderResponse());
			assertTrue(true);
		} catch (RetailStoreException e) {
			fail();
		}

	}
	
	private OrderResponse getOrderResponse()
	{
		OrderResponse response = new OrderResponse();
		response.setStoreUser(getUser());
		response.setProducts(getProducts());

		return response;
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

		StoreUser user = new StoreUser();
		user.setUserId("ABC101");
		user.setName("Usman");
		user.setType("EMPLOYEE");
		user.setCreationDate(DateHelper.getDate(2005, 10, 19));
		return user;
	}
	
}
