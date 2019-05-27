/**
 * 
 */
package com.farkalit.retailstore.helper;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farkalit.retailstore.constant.StoreConstants;
import com.farkalit.retailstore.dto.OrderResponse;
import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;

/**
 * @File name: OrderHelper.java
 * This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 26 May 2019
 */
public class OrderHelper {

	private static final Logger LOG = LoggerFactory.getLogger(OrderHelper.class);
	
	private OrderHelper() {}
	/**
	 * 
	 * @param response
	 */
	public static void applyDiscount(OrderResponse response) {

		double totalAmount = 0;
		double discountAmount = 0;
		double discountApplyAmount = 0;
		StoreUser user = response.getStoreUser();
		List<Product> products = response.getProducts();

		for (Product product : products) {
			totalAmount += product.getPrice();
			// 5. The percentage based discounts do not apply on groceries.
			// So Exclude the groceries item from the discount
			if (!product.getType().equals(StoreConstants.PROD_GROCERIES)) {
				discountApplyAmount += product.getPrice();
			}
		}

		double nonDiscountedAmount = totalAmount - discountApplyAmount;
		/**
		 * Assume discount applied in the same order as given in the requirement
		 * document.
		 */
		String type = user.getType();
		// 1. If the user is an employee of the store, he gets a 30% discount
		if (type.equals(StoreConstants.USER_EMPLOYEE)) {
			discountAmount = discountApplyAmount * StoreConstants.EMPLOYEE_DISCOUNT / 100;
			response.setDiscountPercentage(StoreConstants.EMPLOYEE_DISCOUNT);
		}
		// 2. If the user is an affiliate of the store, he gets a 10% discount
		else if (type.equals(StoreConstants.USER_AFFILIATE)) {
			discountAmount = discountApplyAmount * StoreConstants.AFFILIATE_DISCOUNT / 100;
			response.setDiscountPercentage(StoreConstants.AFFILIATE_DISCOUNT);
		}
		// 3. If the user has been a customer for over 2 years, he gets a 5% discount.
		else if (type.equals(StoreConstants.USER_CUSTOMER) && !validateCustomerDuration(user.getCreationDate())) {
			discountAmount = discountApplyAmount * StoreConstants.CUSTOMER_DISCOUNT / 100;
			response.setDiscountPercentage(StoreConstants.CUSTOMER_DISCOUNT);
		}
		// Otherwise
		// 6. A user can get only one of the percentage based discounts on a bill.
		// 4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990,
		// you get $ 45 as a discount).
		else if (discountApplyAmount >= 100) {
			discountAmount = ((int) discountApplyAmount / 100) * StoreConstants.COMMON_DISCOUNT;
			response.setDiscountPercentage(StoreConstants.COMMON_DISCOUNT);
		}
		// set the discount and grand total amount
		response.setNonDiscountedAmount(nonDiscountedAmount);
		response.setDiscountAmount(discountAmount);
		response.setGrandTotal(totalAmount - discountAmount);

	}

	/**
	 * 
	 * @param userDate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static boolean validateCustomerDuration(Date userDate) {
		LocalDate today = LocalDate.now(); // Today's date
		LocalDate userCreateDate = LocalDate.of(userDate.getYear() + 1900, userDate.getMonth() + 1, userDate.getDate());

		Period period = Period.between(userCreateDate, today);
		LOG.info("Period: Year: {}  month:{}  days:{}", period.getYears(), period.getMonths(), period.getDays());
		return (period.getYears() < 2);
	}
}
