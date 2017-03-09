package com.niit.model;

import java.io.Serializable;

import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
public class Shipping implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String shippingid;
private String street;
private String city;
private String state;
private String country;
private String zipcode;

public String getShippingid() {
	return shippingid;
}
public void setShippingid(String shippingid) {
	this.shippingid = shippingid;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getZipcode() {
	return zipcode;
}
public void setZipcode(String zipcode) {
	this.zipcode = zipcode;
}

	

}
