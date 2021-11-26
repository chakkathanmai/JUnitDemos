package com.mobileapp.model;

public class Mobile {
	 private Integer mobileId;
     private  String model;
     private String brand;
     private double price;
	public Integer getMobileId() {
		return mobileId;
	}
	public void setMobileId(Integer mobileId) {
		this.mobileId = mobileId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Mobile(Integer mobileId, String model, String brand, double price) {
		super();
		this.mobileId = mobileId;
		this.model = model;
		this.brand = brand;
		this.price = price;
	}
	public Mobile() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MobileApp [mobileId=" + mobileId + ", model=" + model + ", brand=" + brand + ", price=" + price + "]";
	}
}
