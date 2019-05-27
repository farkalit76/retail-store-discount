/**
 * 
 */
package com.farkalit.retailstore.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farkalit.retailstore.dto.OrderRequest;
import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;

/**
 * @File name: OrderValidator.java This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
public class OrderValidator {

	private static final Logger LOG = LoggerFactory.getLogger(OrderValidator.class);

	private OrderValidator(){}
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static boolean validateUser(OrderRequest request) {

		boolean isValid = true;

		if (request.getStoreUser() != null) {
			StoreUser user = request.getStoreUser();
			if (user.getName() == null || user.getType() == null || user.getCreationDate() == null) {
				LOG.info("Please enter the user name, type and date.");
				isValid = false;
			}
			if (request.getProducts() != null) {
				isValid = getProductValidation(request, isValid);
			}
		}
		return isValid;
	}
	
	/**
	 * 
	 * @param request
	 * @param isValid
	 * @return
	 */
	private static boolean getProductValidation(OrderRequest request, boolean isValid) {
		List<Product> products = request.getProducts();
		if (products.isEmpty()) {
			LOG.info("Please select at least one product.");
			isValid = false;
		} else {
			for (Product product : products) {
				if (product.getName() == null || product.getType() == null) {
					LOG.info("Selected Product detail is incomplete.");
					isValid = false;
				}
			}
		}
		return isValid;
	}
}
