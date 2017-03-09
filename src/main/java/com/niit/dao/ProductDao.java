package com.niit.dao;

import java.util.List;

import com.niit.model.Category;
import com.niit.model.Product;
import com.niit.model.SubCategory;
import com.niit.model.Supplier_Do;

//Product DAO interface
public interface ProductDao {
	

	public boolean saveProduct(Product prod);// Save method  
	public boolean updateProduct(Product prod);//Update method
	public String  getCategoryList(Category cate);// get category list
	public String  getSupplierList(Supplier_Do sup);//get supplier list
	public String   getProductList(Product prod);//get product list
	public String    getSubCategoryList(SubCategory subcate);//get subcategory list
	public Product  getProduct(String p_id); //get particular product
	public boolean  deleteProduct(String d_p_id);//delete product
	public List<Product> userProductList(String pro_id);//get list of product list type
	public List<Category>  getCategoryListArray(String cat_id);// get list of paricular category list type
	public List<SubCategory>  getSubCategoryListArray(String sub_id);//get list of particular subcategory 
	public List<Product>   getProductwithSubCate(String sub_id);//get list of product of subcategory
	public List<SubCategory> getSubCategoryClickArray(String sub_id);//get list of particular subcategory 
    public List<Product>     getParticularProduct(String pro_id);

}
