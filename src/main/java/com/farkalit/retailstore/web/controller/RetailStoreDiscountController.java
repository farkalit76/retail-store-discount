/**
 * 
 */
package com.farkalit.retailstore.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farkalit.retailstore.dto.OrderRequest;
import com.farkalit.retailstore.dto.OrderResponse;
import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;
import com.farkalit.retailstore.exception.RetailStoreException;
import com.farkalit.retailstore.helper.OrderHelper;
import com.farkalit.retailstore.service.RetailStoreService;
import com.farkalit.retailstore.validator.OrderValidator;

/**
 * @File name: RetailStoreDiscountController.java This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
@RestController
@RequestMapping("/api/retail")
public class RetailStoreDiscountController {

	private static final Logger LOG = LoggerFactory.getLogger(RetailStoreDiscountController.class);

	@Autowired
	private RetailStoreService retailStoreService;

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws RetailStoreException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user", produces = "application/json")
	public StoreUser getUser(String userId) throws RetailStoreException {
		LOG.info("getUser started...");
		return retailStoreService.getUser(userId);
	}

	/**
	 * 
	 * @return
	 * @throws RetailStoreException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/product", produces = "application/json")
	public List<Product> getProduct() throws RetailStoreException {
		LOG.info("getProduct started...");
		return retailStoreService.getProducts();
	}

	/**
	 * Apply the Order logic here
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/order", produces = "application/json")
	public OrderResponse createOrder(@RequestBody OrderRequest request) throws RetailStoreException {
		LOG.info("createOrder started...");
		OrderResponse response = new OrderResponse();
		if (request != null) {
			if (OrderValidator.validateUser(request)) {
				response.setStoreUser(request.getStoreUser());
				response.setProducts(request.getProducts());
				OrderHelper.applyDiscount(response);
			}
		} else {
			LOG.info("Please enter the Order details.");
		}
		return response;
	}

}
