package com.niit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.CategoryDao;
import com.niit.model.Category;

@Controller
public class CategoryController {
	// injects category DAO
	@Autowired
	CategoryDao catdao;
	

	// map the view to return String
	@RequestMapping("/categoryrequest")
	public String getCategoryView(ModelMap map) {
		// return the category DO object
		map.addAttribute("cate", new Category());
		// return value true validated in view(admin.jsp)
		map.addAttribute("categoryrequest", true);
		map.addAttribute("savecate", true);

         String cate_list=catdao.getCategoryList(new Category());
          map.addAttribute("cate_list",cate_list);
		return "admin";

	}

	// map the view to return String and execute only when method is post
	@RequestMapping(value = "/categoryrequestpost", method = RequestMethod.POST)

	public String setCategoryData(@ModelAttribute("cate") Category cate, ModelMap m) {
		// validating if save execute successful then returning message &
		// true(validated in admin.jsp)
		
		if (catdao.saveCategory(cate)) {
			m.addAttribute("message", "Update Successfully");
			m.addAttribute("categoryrequest", true);
			m.addAttribute("savecate", true);

			String cate_list=catdao.getCategoryList(new Category());
	        m.addAttribute("cate_list",cate_list);
			return "admin";
		}

		else {
			m.addAttribute("categoryrequest", true);
			m.addAttribute("savecate", true);

			m.addAttribute("message", "Something Went Wrong");

			return "admin";

		}

	}
	@RequestMapping(value="/deletecate")
	public String deleteCategorydate(@RequestParam("cid")String cid,ModelMap m){
		
		if(catdao.deleteCategory(cid)){
			m.addAttribute("message", "delete Successfully");
			m.addAttribute("categoryrequest", true);
			m.addAttribute("savecate", true);

			m.addAttribute("cate", new Category());

			String cate_list=catdao.getCategoryList(new Category());
	        m.addAttribute("cate_list",cate_list);
			return "admin";
			
			
		}
		else{
			m.addAttribute("message", "something went wrong");
			m.addAttribute("categoryrequest", true);
			m.addAttribute("savecate", true);

			String cate_list=catdao.getCategoryList(new Category());
	        m.addAttribute("cate_list",cate_list);
		return "admin";
		}
		
		
	}
	@RequestMapping(value="/updatecate")
	public String updateCategory(@RequestParam("cid")String cid,ModelMap m){
		
		Category cate=catdao.getCategory(cid);
		m.addAttribute("categoryrequest", true);
		m.addAttribute("updatecate", true);

		m.addAttribute("cate",cate);

		String cate_list=catdao.getCategoryList(new Category());
        m.addAttribute("cate_list",cate_list);
		return "admin";
		
		
		
	}
	// map the view to return String and execute only when method is post
		@RequestMapping(value = "/categoryupdatepost", method = RequestMethod.POST)

		public String updatecategoryData(@ModelAttribute("cate") Category cate, ModelMap m) {
			// validating if save execute successful then returning message &
			// true(validated in admin.jsp)
			
			if (catdao.updateCategory(cate)) {
				m.addAttribute("message", "Update Successfully");
				m.addAttribute("categoryrequest", true);
				m.addAttribute("savecate", true);

				String cate_list=catdao.getCategoryList(new Category());
		        m.addAttribute("cate_list",cate_list);
				return "admin";
			}

			else {
				m.addAttribute("categoryrequest", true);
				m.addAttribute("savecate", true);

				m.addAttribute("message", "Something Went Wrong");

				return "admin";

			}

		}

}
