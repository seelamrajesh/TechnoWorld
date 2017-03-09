package com.niit.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.niit.dao.ProductDao;
import com.niit.model.Category;
import com.niit.model.Product;
import com.niit.model.SubCategory;
import com.niit.model.Supplier_Do;

@Repository("ProductDao")

// ProductDaoImpl implements Category DAO interface
public class ProductDaoImpl implements ProductDao {

	// injects Session Factory
	@Autowired
	SessionFactory SessionFactory;

	// Constructor
	public ProductDaoImpl(SessionFactory sessionFactory) {
		this.SessionFactory = sessionFactory;
	}

	/*
	 * save method implemented from Product DAO interface. Insert the value into
	 * database
	 */
	@Transactional
	public boolean saveProduct(Product prod) {
		try {
			SessionFactory.getCurrentSession().save(prod);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}
       @Transactional
	public String getCategoryList(Category cate) {
		@SuppressWarnings({ "unchecked" })
		List<Category> cate_list=(List<Category>) SessionFactory.getCurrentSession().createCriteria(Category.class).list();
			Gson  gson=new Gson();
			String cate_json=gson.toJson(cate_list);
		return cate_json;
	}

	@Transactional
	public String getSupplierList(Supplier_Do sup) {
	  @SuppressWarnings({ "unchecked" })
		List<Supplier_Do> sup_list=(List<Supplier_Do>) SessionFactory.getCurrentSession().createCriteria(Supplier_Do.class).list();
			Gson  gson=new Gson();
			String sup_json=gson.toJson(sup_list);
		return sup_json;
		
	}

	@Transactional
	public String getProductList(Product prod) {
		@SuppressWarnings({ "unchecked" })
		List<Product> prod_list=(List<Product>) SessionFactory.getCurrentSession().createCriteria(Product.class).list();
			Gson  gson=new Gson();
			String prod_json=gson.toJson(prod_list);
			
		return prod_json;
	}

	@Transactional
	public Product getProduct(String p_id) {

			
 Product pro= (Product) SessionFactory.getCurrentSession().get(Product.class, p_id);
		
			return pro;
		
		
	
		
		
	}

	@Transactional
	public boolean deleteProduct(String d_p_id) {
		try {
			
			Product pro_del= (Product) SessionFactory.getCurrentSession().get(Product.class, d_p_id);
	         SessionFactory.getCurrentSession().delete(pro_del);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public boolean updateProduct(Product prod) {
		
		try {
			SessionFactory.getCurrentSession().update(prod);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public List<Product> userProductList(String pro_id) {
		     
		String productListQuery="from Product where pro_cate_id ='"+pro_id+"'";
		Query query_pro=SessionFactory.getCurrentSession().createQuery(productListQuery);
		@SuppressWarnings("unchecked")
		List<Product> prod_list=query_pro.list();
		
		
		
		return prod_list;
	}

	@Transactional
	public String getSubCategoryList(SubCategory subcate) {
		 
		@SuppressWarnings("unchecked")
		List<SubCategory> subcate_list= (List<SubCategory>) SessionFactory.getCurrentSession().createCriteria(SubCategory.class).list();
		Gson gson_sub=new Gson();
		String subcate_json=gson_sub.toJson(subcate_list);
		
		return subcate_json;
	}

	@Transactional
	public List<Category> getCategoryListArray(String cat_id) {
		
	     
			String categoryListQuery="from Category where cate_id ='"+cat_id+"'";
			Query query_pro=SessionFactory.getCurrentSession().createQuery(categoryListQuery);
			@SuppressWarnings("unchecked")
			List<Category> cate_list=query_pro.list();		
			
			return cate_list;
	}

	@Transactional
	public List<SubCategory> getSubCategoryListArray(String sub_id) {
		String subCategoryListQuery="from SubCategory where subcate_cate ='"+sub_id+"'";
		Query query_pro=SessionFactory.getCurrentSession().createQuery(subCategoryListQuery);
		@SuppressWarnings("unchecked")
		List<SubCategory> subcate_list=query_pro.list();

		return subcate_list;
	}

	@Transactional
	public List<Product> getProductwithSubCate(String sub_id) {
		String prowithsubcategory="from Product where pro_sub_cate_id ='"+sub_id+"'";
		Query query_pro=SessionFactory.getCurrentSession().createQuery(prowithsubcategory);
		@SuppressWarnings("unchecked")
		List<Product> subcate_list=query_pro.list();
		
		return subcate_list;
	}


	@Transactional
	public List<SubCategory> getSubCategoryClickArray(String sub_id) {
		String subCategoryListQuery="from SubCategory where sub_cate_id ='"+sub_id+"'";
		Query query_pro=SessionFactory.getCurrentSession().createQuery(subCategoryListQuery);
		@SuppressWarnings("unchecked")
		List<SubCategory> subcate_list=query_pro.list();

		return subcate_list;
	}
      @Transactional
	public List<Product> getParticularProduct(String pro_id) {
    	  
    	  String particularproduct="from Product where pro_id='"+pro_id+"'";
    	  
    	  Query query_pro=SessionFactory.getCurrentSession().createQuery(particularproduct);
  		@SuppressWarnings("unchecked")
  		List<Product> prod_particular_list=query_pro.list();
		return prod_particular_list;
	}

}
