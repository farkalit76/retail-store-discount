package com.farkalit.retailstore.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.farkalit.retailstore.constant.StoreConstants;
import com.farkalit.retailstore.dao.RetailStoreDAO;
import com.farkalit.retailstore.dto.OrderRequest;
import com.farkalit.retailstore.dto.OrderResponse;
import com.farkalit.retailstore.entity.Product;
import com.farkalit.retailstore.entity.StoreUser;
import com.farkalit.retailstore.helper.DateHelper;
import com.farkalit.retailstore.service.RetailStoreService;
import com.farkalit.retailstore.web.controller.RetailStoreDiscountController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RetailStoreDiscountController.class, secure = false)
public class RetailStoreDiscountControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RetailStoreService retailStoreService;

	@MockBean
	private RetailStoreDAO retailStoreDao;

	@Test
	public void testGetUser() {
		try {
			Mockito.when(retailStoreService.getUser("ABC101")).thenReturn(getUser());

			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/retail/user").param("userId", "ABC101").accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();
			System.out.println("User Response: " + response.getContentAsString());

			String expected = "{userId:ABC101,name:Usman,type:EMPLOYEE, creationDate:2005-10-18T20:00:00.000+0000}";
			// In case of success
			assertEquals(HttpStatus.OK.value(), response.getStatus());
			// JSONAssert.assertEquals(expected, response.getContentAsString(), false);

		} catch (Exception e) {
			fail();
		}
		assertTrue(true);
	}

	@Test
	public void testGetProduct() {
		try {

			Mockito.when(retailStoreService.getProducts()).thenReturn(getProducts());

			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/retail/product").accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();
			System.out.println("Product Response: " + response.getContentAsString());

			String expected = "[{prodId:pr101,name:Laptop,type:ELECTRONICS,price:2000.0}]";

			assertEquals(HttpStatus.OK.value(), response.getStatus());
			JSONAssert.assertEquals(expected, response.getContentAsString(), false);

		} catch (Exception e) {
			fail();
		}
		assertTrue(true);
	}

	//1. If the user is an employee of the store, he gets a 30% discount
	@Test
	public void testCreateOrderUsman() {
		try {

			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/retail/order").contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsBytes(getOrderRequestUsman())).accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();
			System.out.println("Create Order Response: " + response.getContentAsString());

			assertEquals(HttpStatus.OK.value(), response.getStatus());

			String content = response.getContentAsString();
			OrderResponse responseObject = this.mapFromJson(content, OrderResponse.class);
			System.out.println("Create Order responseObject: " + responseObject.toString());
			printBill(responseObject, "If the user is an employee of the store, he gets a 30% discount.");
			assertEquals(1400.00, responseObject.getGrandTotal(), 0.0001);
		} catch (Exception e) {
			fail();
		}
	}
	
	//2. If the user is an affiliate of the store, he gets a 10% discount
	@Test
	public void testCreateOrderHarsh() {
		try {

			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/retail/order").contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsBytes(getOrderRequestHarsh())).accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();
			System.out.println("Create Order Response: " + response.getContentAsString());

			assertEquals(HttpStatus.OK.value(), response.getStatus());

			String content = response.getContentAsString();
			OrderResponse responseObject = this.mapFromJson(content, OrderResponse.class);
			System.out.println("Create Order responseObject: " + responseObject.toString());
			printBill(responseObject, "If the user is an affiliate of the store, he gets a 10% discount.");
			assertEquals(3150.00, responseObject.getGrandTotal(), 0.0001);
		} catch (Exception e) {
			fail();
		}
	}
	
	//3. If the user has been a customer for over 2 years, he gets a 5% discount.
	@Test
	public void testCreateOrderFasin() {
		try {

			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/retail/order").contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsBytes(getOrderRequestFasin())).accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();
			System.out.println("Create Order Response: " + response.getContentAsString());

			assertEquals(HttpStatus.OK.value(), response.getStatus());

			String content = response.getContentAsString();
			OrderResponse responseObject = this.mapFromJson(content, OrderResponse.class);
			System.out.println("Create Order responseObject: " + responseObject.toString());
			printBill(responseObject, "If the user has been a customer for over 2 years, he gets a 5% discount.");
			assertEquals(475.00, responseObject.getGrandTotal(), 0.0001);
		} catch (Exception e) {
			fail();
		}
	}

	//4. The percentage based discounts do not apply on groceries.
	@Test
	public void testCreateOrderMonika() {
		try {

			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/retail/order").contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsBytes(getOrderRequestMonika())).accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();
			System.out.println("Create Order Response: " + response.getContentAsString());

			assertEquals(HttpStatus.OK.value(), response.getStatus());

			String content = response.getContentAsString();
			OrderResponse responseObject = this.mapFromJson(content, OrderResponse.class);
			System.out.println("Create Order responseObject: " + responseObject.toString());
			printBill(responseObject, "The percentage based discounts do not apply on groceries.");
			assertEquals(50.00, responseObject.getGrandTotal(), 0.0001);
		} catch (Exception e) {
			fail();
		}
	}
	
	//5. A user can get only one of the percentage based discounts on a bill. If
	// * USER is not EMPLOYEE, AFFILIATE, CUSTOMER of 2 Year then $5 discount will
	// * given for every $100.
	@Test
	public void testCreateOrderMasroor() {
		try {

			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/retail/order").contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsBytes(getOrderRequestMasroor())).accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();
			System.out.println("Create Order Response: " + response.getContentAsString());

			assertEquals(HttpStatus.OK.value(), response.getStatus());

			String content = response.getContentAsString();
			OrderResponse responseObject = this.mapFromJson(content, OrderResponse.class);
			System.out.println("Create Order responseObject: " + responseObject.toString());
			printBill(responseObject, "A user can get only one of the percentage based discounts on a bill.");
			assertEquals(1900.00, responseObject.getGrandTotal(), 0.0001);
		} catch (Exception e) {
			fail();
		}
	}
	//6. For user ordered multiple products.
	@Test
	public void testCreateOrderMixed() {
		try {

			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/retail/order").contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsBytes(getOrderRequestMixed())).accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();
			System.out.println("Create Order Response: " + response.getContentAsString());

			assertEquals(HttpStatus.OK.value(), response.getStatus());

			String content = response.getContentAsString();
			OrderResponse responseObject = this.mapFromJson(content, OrderResponse.class);
			System.out.println("Create Order responseObject: " + responseObject.toString());
			printBill(responseObject, "For user ordered multiple products");
			assertEquals(6940.00, responseObject.getGrandTotal(), 0.0001);
		} catch (Exception e) {
			fail();
		}
	}
	
	///////////////////////////////Define User and Products/////////////////////

	/**
	 * 1. If the user is an employee of the store, he gets a 30% discount
	 * @return
	 */
	private OrderRequest getOrderRequestUsman() {

		StoreUser user = this.getUser("ABC101");
		List<Product> products = new ArrayList<>();
		products.add(this.getProductList().get(0));
		OrderRequest request = new OrderRequest();
		request.setStoreUser(user);
		request.setProducts(products);
		return request;
	}

	/**
	 * 2. If the user is an affiliate of the store, he gets a 10% discount
	 * @return
	 */
	private OrderRequest getOrderRequestHarsh() {
		StoreUser user = this.getUser("ABC102");
		List<Product> products = new ArrayList<>();
		products.add(this.getProductList().get(1));

		OrderRequest request = new OrderRequest();
		request.setStoreUser(user);
		request.setProducts(products);
		return request;
	}

	/**
	 * 3. If the user has been a customer for over 2 years, he gets a 5% discount.
	 * @return
	 */
	private OrderRequest getOrderRequestFasin() {
		StoreUser user = this.getUser("ABC103");
		List<Product> products = new ArrayList<>();
		products.add(this.getProductList().get(2));

		OrderRequest request = new OrderRequest();
		request.setStoreUser(user);
		request.setProducts(products);
		return request;
	}
	
	/**
	 * 4. The percentage based discounts do not apply on groceries.
	 * @return
	 */
	private OrderRequest getOrderRequestMonika() {
		StoreUser user = this.getUser("ABC104");
		List<Product> products = new ArrayList<>();
		products.add(this.getProductList().get(3));

		OrderRequest request = new OrderRequest();
		request.setStoreUser(user);
		request.setProducts(products);
		return request;
	}
	
	/**
	 * 5. A user can get only one of the percentage based discounts on a bill. If
	 * USER is not EMPLOYEE, AFFILIATE, CUSTOMER of 2 Year then $5 discount will
	 * given for every $100.
	 */
	private OrderRequest getOrderRequestMasroor() {
		StoreUser user = this.getUser("ABC105");
		List<Product> products = new ArrayList<>();
		products.add(this.getProductList().get(0));

		OrderRequest request = new OrderRequest();
		request.setStoreUser(user);
		request.setProducts(products);
		return request;
	}
	
	/**
	 * 6. For user ordered multiple products.
	 * @return
	 */
	private OrderRequest getOrderRequestMixed() {
		StoreUser user = this.getUser("ABC105");
		List<Product> products = this.getProductList();

		OrderRequest request = new OrderRequest();
		request.setStoreUser(user);
		request.setProducts(products);
		return request;
	}
	
	/**
	 * 
	 * @return
	 */
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
	
	private List<Product> getProductList() {

		System.out.println("DAO getProduct started...");

		List<Product> products = new ArrayList<>();

		Product prod1 = new Product("pr101", "Laptop", StoreConstants.PROD_ELECTRONICS, 2000.00);
		Product prod2 = new Product("pr102", "AirCondition", StoreConstants.PROD_ELECTRONICS, 3500.00);
		Product prod3 = new Product("pr103", "Fan", StoreConstants.PROD_ELECTRONICS, 500.00);
		Product prod4 = new Product("pr104", "Apple", StoreConstants.PROD_GROCERIES, 50.00);
		Product prod5 = new Product("pr105", "Shirt", StoreConstants.PROD_CLOTHES, 250.00);
		Product prod6 = new Product("pr106", "Blanket", StoreConstants.PROD_CLOTHES, 1000.00);

		products.add(prod1);
		products.add(prod2);
		products.add(prod3);
		products.add(prod4);
		products.add(prod5);
		products.add(prod6);

		return products;
	}

	private List<StoreUser> getUserList() {

		List<StoreUser> users = new ArrayList<>();

		StoreUser user1 = new StoreUser("ABC101", "Usman", StoreConstants.USER_EMPLOYEE, DateHelper.getDate(2005, 10, 20));
		StoreUser user2 = new StoreUser("ABC102", "Harsh", StoreConstants.USER_AFFILIATE, DateHelper.getDate(2005, 10, 20));
		StoreUser user3 = new StoreUser("ABC103", "Fasin", StoreConstants.USER_CUSTOMER, DateHelper.getDate(2017, 05, 05));
		StoreUser user4 = new StoreUser("ABC104", "Monika", StoreConstants.USER_CUSTOMER, DateHelper.getDate(2015, 05, 05));
		StoreUser user5 = new StoreUser("ABC105", "Masroor", StoreConstants.USER_OTHER, DateHelper.getDate(2017, 05, 05));
		StoreUser user6 = new StoreUser("ABC105", "Mixed", StoreConstants.USER_OTHER, DateHelper.getDate(2017, 05, 05));

		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);

		return users;
	}

	public StoreUser getUser(String userId) {
		List<StoreUser> users = getUserList();
		for (StoreUser user : users) {
			if (user.getUserId().equals(userId)) {
				System.out.println("User found for Id:" + userId);
				return user;
			}
		}
		System.out.println("User NOT found for Id:" + userId);
		return null;
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
	
	private void printBill(OrderResponse response, String message) {
		System.out.println("\n\n*****************************");
		System.out.println("*****Start bill printing****");
		System.out.println("*****For: "+message +"****");
		System.out.println("*****************************");
		System.out.println(response.getStoreUser().toString());
		List<Product> products = response.getProducts();
		for (Product product : products) {
			System.out.println(product.toString());
		}
		System.out.println("Discount " + response.getDiscountPercentage() + "% for non groceries Items only: $" + response.getDiscountAmount());
		System.out.println(" ***Grand Total: $" + response.getGrandTotal());

		if (response.getNonDiscountedAmount() > 0) {
			System.out.println("****************************");
			System.out.println("Non Discounted Amount : $" + response.getNonDiscountedAmount());
		}
		System.out.println("********Bill End***********");
		System.out.println("\n\n");
	}
}
