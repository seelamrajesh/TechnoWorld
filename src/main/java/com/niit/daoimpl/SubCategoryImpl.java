package com.niit.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.niit.dao.SubCategoryDao;
import com.niit.model.Category;
import com.niit.model.SubCategory;

@Repository("SubCategoryDao")
public class SubCategoryImpl implements SubCategoryDao {
	
	
	@Autowired
	SessionFactory sessionFactory;

	// Constructor
	public SubCategoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Transactional
	public boolean saveCategory(SubCategory cate) {
		try {
			sessionFactory.getCurrentSession().save(cate);
			return true;

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}


	@Transactional
	public boolean updateCategory(SubCategory cate) {
		try {
			sessionFactory.getCurrentSession().update(cate);
			return true;

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
		}


	@Transactional
	public String getCategoryList(SubCategory cate) {
		@SuppressWarnings("unchecked")
		List<Category> cat_list=sessionFactory.getCurrentSession().createCriteria(SubCategory.class).list();
		Gson gson=new Gson();
		String cate_json=gson.toJson(cat_list);
		
		
		return cate_json;
	}


	@Transactional
	public SubCategory getCategory(String cat_id) {
		SubCategory cat_list=(SubCategory) sessionFactory.getCurrentSession().get(SubCategory.class, cat_id);
		return cat_list;	
		}


	@Transactional
	public boolean deleteCategory(String cat_id) {
		try {
			SubCategory cat=(SubCategory) sessionFactory.getCurrentSession().get(SubCategory.class, cat_id);
	         
			sessionFactory.getCurrentSession().delete(cat);
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
			}

}
