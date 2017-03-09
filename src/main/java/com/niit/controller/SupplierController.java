package com.niit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.SupplierDao;
import com.niit.model.Category;
import com.niit.model.Supplier_Do;

@Controller
public class SupplierController {
	// injects Supplier DAO

	@Autowired
	SupplierDao supdao;
	// map the view to return String

	@RequestMapping(value = "/supplierrequest", method = RequestMethod.GET)
	public String getSupplierViews(ModelMap m) {
		// return the Supplier DO object
		m.addAttribute("sup", new Supplier_Do());
		// return value true validated in view(admin.jsp)
		m.addAttribute("supplierrequest", true);
		m.addAttribute("save", true);

	String sup_list=supdao.getSuppilerList(new Supplier_Do());
	m.addAttribute("sup_list",sup_list);
		return "admin";

	}
	// map the view to return String and execute only when method is post

	@RequestMapping(value = "/supplierrequestpost", method = RequestMethod.POST)
	public String setSupplierData(@ModelAttribute("sup") Supplier_Do sup, ModelMap m) {

		/*
		 * validating if save execute successful then returning message &
		 * true(validated in admin.jsp)
		 */
		if (supdao.saveSupplier(sup)) {
			m.addAttribute("message", "Update Successfully");
			m.addAttribute("supplierrequest", true);
			m.addAttribute("save", true);

			String sup_list=supdao.getSuppilerList(new Supplier_Do());
			m.addAttribute("sup_list",sup_list);
			
			return "admin";
		}

		else {
			m.addAttribute("supplierrequest", true);
			m.addAttribute("save", true);

			String sup_list=supdao.getSuppilerList(new Supplier_Do());
			m.addAttribute("sup_list",sup_list);
			
			m.addAttribute("message", "Something Went Wrong");

			return "admin";

		}

	}
	
	@RequestMapping(value="/deletesup",method=RequestMethod.GET)
	public String deleteSupplier(@RequestParam("sid")String sid,ModelMap m){
		
		if(supdao.deleteSupplier(sid)){
		m.addAttribute("sup", new Supplier_Do());
		m.addAttribute("save", true);

		m.addAttribute("message", "Delete Successfully");
		m.addAttribute("supplierrequest", true);
		String sup_list=supdao.getSuppilerList(new Supplier_Do());
		m.addAttribute("sup_list",sup_list);
	     m.addAttribute("supplierrequest", true);
		return "admin";

		}
		else{
			m.addAttribute("sup", new Supplier_Do());
			m.addAttribute("message", "Something went wrong");
			m.addAttribute("supplierrequest", true);
			m.addAttribute("save", true);

			String sup_list=supdao.getSuppilerList(new Supplier_Do());
			m.addAttribute("sup_list",sup_list);
		     m.addAttribute("supplierrequest", true);
			return "admin";

		}
		
		
		
	}
	@RequestMapping(value="/updatesup",method=RequestMethod.GET)
	public String updateSupplier(@RequestParam("sid")String sid,ModelMap m){
		
		Supplier_Do sup=supdao.getSupplier(sid);
		m.addAttribute("sup",sup);
		m.addAttribute("update", true);

		m.addAttribute("supplierrequest", true);
		String sup_list=supdao.getSuppilerList(new Supplier_Do());
		m.addAttribute("sup_list",sup_list);

		return "admin";
		
		
	}
	@RequestMapping(value = "/requestpostupdate", method=RequestMethod.POST)
	public String SupplierDataupdate(@ModelAttribute("sup") Supplier_Do sup, ModelMap m) {

		
		if (supdao.updateSupplier(sup)) {
			m.addAttribute("message", "Update Successfully");
			m.addAttribute("supplierrequest", true);
			m.addAttribute("save", true);

			String sup_list=supdao.getSuppilerList(new Supplier_Do());
			m.addAttribute("sup_list",sup_list);
			
			return "admin";
		}

		else {
			m.addAttribute("supplierrequest", true);
			m.addAttribute("save", true);

			String sup_list=supdao.getSuppilerList(new Supplier_Do());
			m.addAttribute("sup_list",sup_list);
			
			m.addAttribute("message", "Something Went Wrong");

			return "admin";

		}

	}

}
