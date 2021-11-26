package com.mobileapp.testcases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mobileapp.exceptions.EmptyCartException;
import com.mobileapp.exceptions.MobileNotFoundException;
import com.mobileapp.model.Mobile;
import com.mobileapp.service.CartDetails;
import com.mobileapp.service.ICartService;

@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
class CartDetailsTest {

	@Mock
	ICartService cartService;
	
	@InjectMocks
	CartDetails  cartDetails;
	Mobile mobile1, mobile2, mobile3, mobile4, mobile5;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		mobile1 = new Mobile(1, "Samsung", "M32", 15000);
		mobile2 = new Mobile(2, "Apple", "11 mini", 23000);
		mobile3 = new Mobile(3, "Samsung", "A32", 34000);
		mobile4 = new Mobile(4, "Samsung", "F52", 42000);
		mobile5 = new Mobile(5, "Samsung", "M50", 52000);

		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
 @Test
  void testAddCart() throws MobileNotFoundException {
	 //passing the object inside when
	 //calling caertService using mock
	 doNothing().when(cartService).addtoCart(mobile1); //this return nothing
	 String actual=cartDetails.addtoCart(mobile1);
	 String expected="added succesfully";
	 assertEquals(expected,actual,"invalid");
	 
 }
 
 @Test
 void testAddCartException() throws MobileNotFoundException {
	 //passing the object inside when
	 //calling caertService using mock
	 doThrow(new MobileNotFoundException("invalid")).when(cartService).addtoCart(mobile1); //this return nothing
	
	 
	 assertThrows(MobileNotFoundException.class,()->cartDetails.addtoCart(mobile1));
	 
}
 @Test
	void testShowCart() throws EmptyCartException {
		List<Mobile> expectedMobile = Arrays.asList(mobile2, mobile3, mobile1);
		doReturn(Arrays.asList(mobile1, mobile2, mobile3)).when(cartService).showCart();
		List<Mobile> actualMobiles = cartDetails.showCart();
		assertEquals(expectedMobile, actualMobiles, "Not equal");
	}
 

 @Test
 void testShowCartEmpty() throws EmptyCartException {
	
	 doThrow(new EmptyCartException()).when(cartService).showCart();
	 assertThrows(EmptyCartException.class,()->cartDetails.showCart());
 }
 
 @Test
	void testShowCartNull() throws MobileNotFoundException, EmptyCartException {
	    doReturn(null).when(cartService).showCart();
	    assertNull(cartDetails.showCart());
		
	}

}
