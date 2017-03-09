package com.niit.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.niit.dao.CartDao;
import com.niit.model.Cart;
import com.niit.model.Product;

@Repository("CartDao")
public class CartDaoImpl implements CartDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	int cart_size;
	
	public CartDaoImpl(SessionFactory session) {
		this.sessionFactory=session;
	}

	@Transactional
	public boolean save_cart(Cart car,String email) {
		String pro_id=car.getPro_id();
		String hql="from Cart where pro_id ='"+pro_id+"' and emai_id='"+email+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Cart> cart_list=query.list();
           if(cart_list.isEmpty()){
        	   sessionFactory.getCurrentSession().saveOrUpdate(car);
       		return true;

           }
           else{
        	   return false;
           }
	}

	@Override
	public String getCartList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<Cart> getCartWithUserId(String user_id) {
		String hql="from Cart where emai_id ='"+user_id+"'";
		
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		
		   @SuppressWarnings("unchecked")
		List<Cart> cart_list=query.list();
		   cart_size=cart_list.size();
		    
		
		return cart_list;
		
	}

	
	
	
	@Override
	public int cart_size() {
		return cart_size;
	}

	@Transactional
	public boolean delete_cart(String cat_id) {
		
		try {
			Cart cat=(Cart) sessionFactory.getCurrentSession().get(Cart.class,cat_id);
			sessionFactory.getCurrentSession().delete(cat);
			
			return true;

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean update_cart(String cat_id,int quantity,String pro_id) {

		try {
			Cart cat=(Cart) sessionFactory.getCurrentSession().get(Cart.class,cat_id);
			Product pro=(Product) sessionFactory.getCurrentSession().get(Product.class, pro_id);
			cat.setQuantity(quantity);
			cat.setPrice(quantity*pro.getPro_price());
			sessionFactory.getCurrentSession().update(cat);
			
			
			return true;

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
		
	}
	

	

}

