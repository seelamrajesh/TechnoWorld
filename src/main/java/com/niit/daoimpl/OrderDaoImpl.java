package com.niit.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.OrderDao;
import com.niit.model.Cart;
import com.niit.model.Orders;

@Repository("OrderDao")
public class OrderDaoImpl implements OrderDao{
	
	@Autowired 
	SessionFactory sessionFactory;
	
	public OrderDaoImpl(SessionFactory session) {
		this.sessionFactory=session;
	}

	@Transactional
	public boolean save_Order(Orders order) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(order);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	@Transactional
	public List<Orders> getOrderWithUserId(String user_id) {
		String hql="from Orders where email_id ='"+user_id+"'";
		
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		
		   @SuppressWarnings("unchecked")
		List<Orders> orders_list=query.list();
		    
		
		return orders_list;
		
	}


}
