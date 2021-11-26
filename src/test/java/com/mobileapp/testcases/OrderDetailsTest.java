package com.mobileapp.testcases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mobileapp.exceptions.MobileNotFoundException;
import com.mobileapp.model.Mobile;
import com.mobileapp.service.IMobileService;

import com.mobileapp.service.OrderDetails;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class OrderDetailsTest {

	// Mock the object

	@Mock
	IMobileService mobileService; // this is a proxy of Imobileservice
	// create an object of orderDetails

	@InjectMocks
	OrderDetails orderDetails;
	Mobile mobile1, mobile2, mobile3, mobile4, mobile5;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		orderDetails = new OrderDetails();
		orderDetails.setMobileService(mobileService);

		mobile1 = new Mobile(1, "Samsung", "M32", 1);
		mobile2 = new Mobile(2, "Apple", "11 mini", 2);
		mobile3 = new Mobile(3, "Samsung", "A32", 3);
		mobile4 = new Mobile(4, "Samsung", "F52", 4);
		mobile5 = new Mobile(5, "Samsung", "M50", 5);

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testShowMobilesByBrand() throws MobileNotFoundException {
		String brand = "Samsung";
		List<Mobile> expectedMobile = Arrays.asList(mobile1, mobile3, mobile4, mobile5);
		Mockito.when(mobileService.getByBrand(brand))
				.thenReturn(Arrays.asList(mobile1, mobile3, mobile4, mobile5));

		List<Mobile> actualMobileList = orderDetails.showMobiles(brand);
		assertEquals(expectedMobile, actualMobileList, "list is not equal");

	}

	@Test
	void testShowMobilesEmpty() throws MobileNotFoundException {
		String brand = "Moto";

		Mockito.when(mobileService.getByBrand("Moto")).thenReturn(new ArrayList<>());

		List<Mobile> actualMobileList = orderDetails.showMobiles(brand);
		assertEquals(0, actualMobileList.size(), "List should be empty");

	}

	@Test
	void testShowMobilesNull() throws MobileNotFoundException {

		String brand = "LG";
		Mockito.when(mobileService.getByBrand("LG")).thenReturn(null);

		List<Mobile> actualMobileList = orderDetails.showMobiles(brand);
		assertNull(actualMobileList);
	}

	@Test
	void testshowMobilesInvalid() throws MobileNotFoundException {
		Mockito.when(mobileService.getByBrand("vivo")).thenThrow(MobileNotFoundException.class);
		assertThrows(MobileNotFoundException.class, () -> orderDetails.showMobiles("vivo"));

	}
	
	
	  @Test
	  void testShowMobilesSortByBrand() throws MobileNotFoundException {
	  String brand = "Samsung"; List<Mobile> expectedMobiles =
	  Arrays.asList(mobile1, mobile3, mobile4, mobile5);
	  
	  Mockito.when(mobileService.getByBrand("Samsung"))
	  .thenReturn(Arrays.asList(mobile1, mobile3, mobile4, mobile5)); List<Mobile>
	  actualMobileList = orderDetails.showMobiles(brand);
	  
	 assertEquals(expectedMobiles, actualMobileList, "List not equal");
	  
	  }
	  @Test 
	  void testOrderValid() throws MobileNotFoundException{
		  String expected="mobile ordered";
		  when(mobileService.getById(1)).thenReturn(mobile1);
		  String actual=orderDetails.orderMobile(1);
		  assertEquals(expected,actual,"not same");
	  }
	 
	  @Test 
	  void testOrderInvalid() throws MobileNotFoundException{
		  String expected="mobile not ordered";
		  when(mobileService.getById(100)).thenReturn(null);
		  String actual=orderDetails.orderMobile(100);
		  assertEquals(expected,actual,"values are different");
	  }
	  
	  @Test 
	  @DisplayName("Checking if not found")
	  void testOrderException() throws MobileNotFoundException{
		  String expected="mobile not ordered";
		  when(mobileService.getById(100)).thenThrow(MobileNotFoundException.class);
	      String actual=orderDetails.orderMobile(100);
	      assertEquals(expected,actual,"values are not same");
	  }
	  
	  @Test 
	  void testOrderEmpty() throws MobileNotFoundException{
		  String expected="mobile not ordered";
		  when(mobileService.getById(100)).thenReturn(new Mobile());
	      String actual=orderDetails.orderMobile(100);
	      assertEquals(expected,actual,"empty object expected");
	  }
	  

}