package com.mobileapp.service;

import java.util.List;

import com.mobileapp.exceptions.MobileNotFoundException;
import com.mobileapp.model.Mobile;


public interface IMobileService {
	//return null,exception,new Mobile(),mobile
	public  Mobile  getById(int Id) throws MobileNotFoundException;
	 //return null,exception 
	public List<Mobile> getByBrand(String brand) throws MobileNotFoundException;

	
}
