package com.niit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.niit.dao.ProductDao;
import com.niit.fileupload.Image_Upload;
import com.niit.fileupload.Multiple_Image_Upload;
import com.niit.fileupload.TextDocument;
import com.niit.model.Category;
import com.niit.model.Product;
import com.niit.model.SubCategory;
import com.niit.model.Supplier_Do;

@Controller
public class ProductController {
	// injects Product DAO
	@Autowired
	ProductDao prodao;
    String path="E:\\Workspace\\ShopOnWeb\\src\\main\\webapp\\WEB-INF\\resources\\images\\product";
 	// map the view to return String execute if method is get
	@RequestMapping(value = "/productrequest", method = RequestMethod.GET)
	public String getProductView(ModelMap m) {
		// return the Login Product object

		m.addAttribute("prod", new Product());
		// return value true,validated in view(admin.jsp)
		m.addAttribute("productrequest", true);
		m.addAttribute("edit", true);
		String cate_list = prodao.getCategoryList(new Category());
		String sup_list = prodao.getSupplierList(new Supplier_Do());
		String prod_list = prodao.getProductList(new Product());
		String subcate_list=prodao.getSubCategoryList(new SubCategory());
		m.addAttribute("subcate_list",subcate_list);
		m.addAttribute("cate_list", cate_list);
		m.addAttribute("sup_list", sup_list);
		m.addAttribute("prod_list", prod_list);

		return "admin";

	}

	// map the view to return String and execute only when method is post
	@RequestMapping(value = "/productrequestpost", method = RequestMethod.POST)
	public String setProductData(@ModelAttribute("prod") Product prod, ModelMap m) {
		// validating if save execute successful then returning message &
		// true(validated in admin.jsp)
		 MultipartFile part=prod.getPro_image();
		 MultipartFile text_document=prod.getPro_text();
         if(Multiple_Image_Upload.uploadMultipleImage(path, prod.getMultipleImage(), prod.getPro_id()))
         {
            
		 prod.setImage_one( prod.getPro_id()+"s1"+".jpg");
			prod.setImage_two( prod.getPro_id()+"s2"+".jpg");
			prod.setImage_three( prod.getPro_id()+"s3"+".jpg");
			prod.setImage_four( prod.getPro_id()+"s4"+".jpg");
			if (prodao.saveProduct(prod)) {
				m.addAttribute("message", "Update Successfully");
				m.addAttribute("productrequest", true);
				m.addAttribute("edit", true);
				String cate_list = prodao.getCategoryList(new Category());
				String sup_list = prodao.getSupplierList(new Supplier_Do());
				String prod_list = prodao.getProductList(new Product());
				String subcate_list=prodao.getSubCategoryList(new SubCategory());
				m.addAttribute("subcate_list",subcate_list);
				m.addAttribute("cate_list", cate_list);
				m.addAttribute("sup_list", sup_list);
				m.addAttribute("prod_list", prod_list);
	            Image_Upload.UploadMethod(path, part, prod.getPro_id()+".jpg");
	            TextDocument.UploadText(path, text_document,prod.getPro_id()+".txt" );

				return "admin";
			}

			else {
				m.addAttribute("productrequest", true);
				m.addAttribute("edit", true);
				
				m.addAttribute("message", "Something Went Wrong");

				return "admin";

			}
         }
         else{
        	 m.addAttribute("productrequest", true);
				m.addAttribute("edit", true);
				String cate_list = prodao.getCategoryList(new Category());
				String sup_list = prodao.getSupplierList(new Supplier_Do());
				String prod_list = prodao.getProductList(new Product());
				String subcate_list=prodao.getSubCategoryList(new SubCategory());
				m.addAttribute("subcate_list",subcate_list);
				m.addAttribute("cate_list", cate_list);
				m.addAttribute("sup_list", sup_list);
				m.addAttribute("prod_list", prod_list);
				
				m.addAttribute("message", "Must upload 4 images");

				return "admin";
         }
	

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteRequest(@RequestParam("pid") String pid, Model m) {

		if (prodao.deleteProduct(pid)) {

			m.addAttribute("prod", new Product());
			m.addAttribute("edit", true);
			m.addAttribute("productrequest", true);
			String cate_list = prodao.getCategoryList(new Category());
			String sup_list = prodao.getSupplierList(new Supplier_Do());
			String prod_list = prodao.getProductList(new Product());
			String subcate_list=prodao.getSubCategoryList(new SubCategory());
			m.addAttribute("subcate_list",subcate_list);
			m.addAttribute("cate_list", cate_list);
			m.addAttribute("sup_list", sup_list);
			m.addAttribute("prod_list", prod_list);
			return "admin";

		} else {
			m.addAttribute("prod", new Product());
			m.addAttribute("edit", true);
			m.addAttribute("productrequest", true);
			String cate_list = prodao.getCategoryList(new Category());
			String sup_list = prodao.getSupplierList(new Supplier_Do());
			String prod_list = prodao.getProductList(new Product());
			String subcate_list=prodao.getSubCategoryList(new SubCategory());
			m.addAttribute("subcate_list",subcate_list);
			m.addAttribute("cate_list", cate_list);
			m.addAttribute("sup_list", sup_list);
			m.addAttribute("prod_list", prod_list);
			return "admin";
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateRequest(@RequestParam("pid") String pid, Model m) {

		Product pro = prodao.getProduct(pid);

		m.addAttribute("prod", pro);
		m.addAttribute("udate", true);
		m.addAttribute("productrequest", true);
		String cate_list = prodao.getCategoryList(new Category());
		String sup_list = prodao.getSupplierList(new Supplier_Do());
		String prod_list = prodao.getProductList(new Product());
		String subcate_list=prodao.getSubCategoryList(new SubCategory());
		m.addAttribute("subcate_list",subcate_list);
		m.addAttribute("cate_list", cate_list);
		m.addAttribute("sup_list", sup_list);
		m.addAttribute("prod_list", prod_list);

		return "admin";

	}

	@RequestMapping(value = "/updaterequestpost", method = RequestMethod.POST)
	public String udateProductData(@ModelAttribute("prod") Product prod, ModelMap m) {
		
		MultipartFile part=prod.getPro_image();
		 MultipartFile text_document=prod.getPro_text();

        
		 if(Multiple_Image_Upload.uploadMultipleImage(path, prod.getMultipleImage(), prod.getPro_id()))
         {

		    prod.setImage_one( prod.getPro_id()+"s1"+".jpg");
			prod.setImage_two( prod.getPro_id()+"s2"+".jpg");
			prod.setImage_three( prod.getPro_id()+"s3"+".jpg");
			prod.setImage_four( prod.getPro_id()+"s4"+".jpg");
		     
			if (prodao.updateProduct(prod)) {
				m.addAttribute("message", "Update Successfully");
				m.addAttribute("productrequest", true);
				m.addAttribute("edit", true);
				String cate_list = prodao.getCategoryList(new Category());
				String sup_list = prodao.getSupplierList(new Supplier_Do());
				String prod_list = prodao.getProductList(new Product());
				String subcate_list=prodao.getSubCategoryList(new SubCategory());
				m.addAttribute("subcate_list",subcate_list);
				m.addAttribute("cate_list", cate_list);
				m.addAttribute("sup_list", sup_list);
				m.addAttribute("prod_list", prod_list);
				
		        Image_Upload.UploadMethod(path, part, prod.getPro_id()+".jpg");
	            TextDocument.UploadText(path, text_document,prod.getPro_id()+".txt" );





				return "admin";
			}

			else {
				m.addAttribute("productrequest", true);
				m.addAttribute("udate", true);
				m.addAttribute("message", "Something Went Wrong");

				return "admin";

			}
         }
		 else{
			 m.addAttribute("productrequest", true);
				m.addAttribute("udate", true);
				String cate_list = prodao.getCategoryList(new Category());
				String sup_list = prodao.getSupplierList(new Supplier_Do());
				String prod_list = prodao.getProductList(new Product());
				String subcate_list=prodao.getSubCategoryList(new SubCategory());
				m.addAttribute("subcate_list",subcate_list);
				m.addAttribute("cate_list", cate_list);
				m.addAttribute("sup_list", sup_list);
				m.addAttribute("prod_list", prod_list);
				
				m.addAttribute("message", "Must upload 4 images");
				return "admin";

		 }


		
       

	}
}
