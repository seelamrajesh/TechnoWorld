package com.niit.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Cart implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	private String cart_id;
	@Column
	private String pro_id;
	@Column
	private int quantity=1;
	@Column
	private double price;
	@Column
	private String  date_cart;
	@Column 
	private String emai_id;
	@Column 
	private String pro_name;
	


	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getEmai_id() {
		return emai_id;
	}
	public void setEmai_id(String emai_id) {
		this.emai_id = emai_id;
	}
	public String getCart_id() {
		return cart_id;
	}
	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}
	
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDate_cart() {
		return date_cart;
	}
	public void setDate_cart(String date_cart) {
		this.date_cart = date_cart;
	}
	

}
