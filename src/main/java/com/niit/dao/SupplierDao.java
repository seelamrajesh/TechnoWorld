package com.niit.dao;

import com.niit.model.Supplier_Do;

//Supplier DAO interface
public interface SupplierDao {

	// Save method declaration with Product DO parameters
	public boolean saveSupplier(Supplier_Do sup);
	public String  getSuppilerList(Supplier_Do sup);
	public boolean  deleteSupplier(String sid);
	public Supplier_Do   getSupplier(String sid);
	public boolean updateSupplier(Supplier_Do sup);

}
