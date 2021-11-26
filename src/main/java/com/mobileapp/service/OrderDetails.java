package com.mobileapp.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mobileapp.exceptions.MobileNotFoundException;
import com.mobileapp.model.Mobile;

public class OrderDetails {
	IMobileService mobileService;
   
   
   public void setMobileService(IMobileService mobileService) {
	this.mobileService = mobileService;
}


public String orderMobile(int mobileId) {
	  Mobile mobile =null;
	   try {
		  mobile=mobileService.getById(mobileId); 
	   }catch(MobileNotFoundException e) {
		  System.out.println(e.getMessage());
	   }
	  // System.out.println(mobile.toString());
	   if(mobile==null || (mobile.getBrand()==null && mobile.getMobileId()==null))
	     return "mobile not ordered";
	   else
		return "mobile ordered";
   }
//handle the exception in the calling class or client class
//so throw the exception
public List<Mobile> showMobiles(String brand) throws MobileNotFoundException{
	List<Mobile> mobileList = new ArrayList<>();
	try {
		mobileList=mobileService.getByBrand(brand);
		
	}catch (MobileNotFoundException e) {
		System.out.println(e.getMessage());
		throw e;
	}
	if(mobileList!=null) 
	return mobileList.stream().sorted(Comparator.comparing(Mobile::getModel)).collect(Collectors.toList());
	else 
		return null;
}

}