package com.niit.dao;

import java.util.List;

import com.niit.model.Cart;


public interface CartDao {
	public boolean save_cart(Cart car,String email);
	public  String getCartList();
	public List<Cart> getCartWithUserId(String user_id);
	public  int cart_size();
    public boolean delete_cart(String cat_id);
    public boolean update_cart(String cat_id,int quantity,String pro_id);
	
	
}