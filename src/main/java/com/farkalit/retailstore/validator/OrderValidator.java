/**
 * 
 */
package com.farkalit.retailstore.validator;

import java.util.List;

import com.farkalit.retailstore.dto.OrderRequest;
import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;

/**
 * @File name: OrderValidator.java
 * This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
public class OrderValidator {

	public static boolean validateUser(OrderRequest request) {

		boolean isValid = true;

		if (request.getStoreUser() != null ) {
			StoreUser user = request.getStoreUser();
			if (user.getName() == null || user.getType() == null || user.getCreationDate() == null) {
				System.out.println("Please enter the user name, type and date.");
				isValid = false;
			} 
			if( request.getProducts() != null )
			{
				List<Product> products = request.getProducts();
				if(products.isEmpty() )
				{
					System.out.println("Please select at least one product.");
					isValid = false;
				}else {
					for (Product product : products) {
						if( product.getName() == null || product.getType() == null) {
							System.out.println("Selected Product detail is incomplete.");
							isValid = false;
						}
					}
				}
			}
		}
		return isValid;
	}
}
