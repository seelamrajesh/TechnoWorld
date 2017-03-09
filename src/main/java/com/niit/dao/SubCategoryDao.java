package com.niit.dao;

import com.niit.model.SubCategory;

public interface SubCategoryDao {
	
	
	public boolean saveCategory(SubCategory cate);
	public  boolean updateCategory(SubCategory cate);
	public String getCategoryList(SubCategory cate);
	public  SubCategory getCategory(String cat_id);
	public boolean deleteCategory(String cat_id);


}
