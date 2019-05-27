/**
 * 
 */
package com.farkalit.retailstore.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.farkalit.retailstore.dto.OrderRequest;
import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;
import com.farkalit.retailstore.validator.OrderValidator;

/**
 * @File name: OrderValidatorTest.java This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 27 May 2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderValidatorTest {

	@Test
	public void testValidateUser() {
		boolean isVlaid = OrderValidator.validateUser(getOrderRequest());
		assertEquals(false, isVlaid);
	}

	private OrderRequest getOrderRequest() {
		StoreUser user = new StoreUser();
		List<Product> products = new ArrayList<>();

		OrderRequest request = new OrderRequest();
		request.setStoreUser(user);
		request.setProducts(products);
		return request;
	}

}
