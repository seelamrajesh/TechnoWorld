package com.niit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.niit.dao.SupplierDao;
import com.niit.model.Supplier_Do;

@Repository("SupplierDao")
// ProductDaoImpl implements Category DAO interface
public class SupplierDaoImpl implements SupplierDao {
	// injects Session Factory
	@Autowired
	SessionFactory sessionFactory;

	// Constructor
	public SupplierDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * save method implemented from Product DAO interface. Insert the value into
	 * database
	 */
	@Transactional
	public boolean saveSupplier(Supplier_Do sup) {

		try {
			sessionFactory.getCurrentSession().save(sup);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public String getSuppilerList(Supplier_Do sup) {

		@SuppressWarnings("unchecked")
		List<Supplier_Do> sup_list = sessionFactory.getCurrentSession().createCriteria(Supplier_Do.class).list();
		Gson gson = new Gson();
		String sup_json = gson.toJson(sup_list);
		return sup_json;
	}

	@Transactional
	public boolean deleteSupplier(String sid) {
		   
		   try {
			   Supplier_Do sup=(Supplier_Do) sessionFactory.getCurrentSession().get(Supplier_Do.class, sid);
				sessionFactory.getCurrentSession().delete(sup);

				return true;
			} catch (Exception e) {

				e.printStackTrace();
				return false;
			}  
	}

	@Transactional
	public Supplier_Do getSupplier(String sid) {
		
		Supplier_Do sup=(Supplier_Do) sessionFactory.getCurrentSession().get(Supplier_Do.class, sid);
           
		return sup;
	}

	@Transactional
	public boolean updateSupplier(Supplier_Do sup) {
		try {
			sessionFactory.getCurrentSession().update(sup);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	

}
