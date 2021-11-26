package com.mobileapp.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mobileapp.exceptions.EmptyCartException;
import com.mobileapp.exceptions.MobileNotFoundException;
import com.mobileapp.model.Mobile;

public class CartDetails  {

	
	public void setCartService(ICartService cartService) {
		this.cartService = cartService;
	}

	 ICartService cartService;
	
	public List<Mobile> showCart() throws EmptyCartException {
		List<Mobile> mobileList=cartService.showCart();
		if(mobileList!=null) {
	          mobileList= mobileList.stream()
	          .sorted((item1,item2)->item1.getBrand().compareTo(item2.getBrand()))
		      .collect(Collectors.toList());
	         return mobileList;
		}
	    return null;
	}
	

	public String addtoCart(Mobile mobile) throws MobileNotFoundException {
		cartService.addtoCart(mobile);
		return "added succesfully";		
	}

	public boolean removeFromCart(Mobile mobile) throws MobileNotFoundException {
		
		return false;
	}

}
