package com.niit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.ProductDao;
import com.niit.dao.SubCategoryDao;
import com.niit.model.Category;
import com.niit.model.SubCategory;

@Controller
public class SubCategoryController {
	
	@Autowired
	SubCategoryDao subcatdao;
	@Autowired
	ProductDao prod;
	// map the view to return String
		@RequestMapping("/subcategoryrequest")
		public String getCategoryView(ModelMap map) {
			// return the category DO object
			map.addAttribute("subcate", new SubCategory());
			// return value true validated in view(admin.jsp)
			map.addAttribute("subcategoryrequest", true);
			map.addAttribute("savesubcate", true);
			String category_list=prod.getCategoryList(new Category());
	          map.addAttribute("cate_list",category_list);

			String cate_list=subcatdao.getCategoryList(new SubCategory());
	          map.addAttribute("subcate_list",cate_list);
			return "admin";

		}
		// map the view to return String and execute only when method is post
		@RequestMapping(value = "/subcategoryrequestpost", method = RequestMethod.POST)

		public String setCategoryData(@ModelAttribute("subcate") SubCategory subcate, ModelMap m) {
			// validating if save execute successful then returning message &
			// true(validated in admin.jsp)
			
			if (subcatdao.saveCategory(subcate)) {
				m.addAttribute("message", "Update Successfully");
				m.addAttribute("subcategoryrequest", true);
				m.addAttribute("savesubcate", true);

				String subcate_list=subcatdao.getCategoryList(new SubCategory());
		        m.addAttribute("subcate_list",subcate_list);
				return "admin";
			}

			else {
				m.addAttribute("subcategoryrequest", true);
				m.addAttribute("savesubcate", true);

				m.addAttribute("message", "Something Went Wrong");

				return "admin";

			}

		}
		@RequestMapping(value="/subdeletecate")
		public String deleteCategorydate(@RequestParam("cid")String cid,ModelMap m){
			
			if(subcatdao.deleteCategory(cid)){
				m.addAttribute("message", "delete Successfully");
				m.addAttribute("subcategoryrequest", true);
				m.addAttribute("savesubcate", true);

				m.addAttribute("subcate", new SubCategory());

				String subcate_list=subcatdao.getCategoryList(new SubCategory());
		        m.addAttribute("subcate_list",subcate_list);
				return "admin";
				
				
			}
			else{
				m.addAttribute("message", "something went wrong");
				m.addAttribute("subcategoryrequest", true);
				m.addAttribute("savesubcate", true);

				String subcate_list=subcatdao.getCategoryList(new SubCategory());
		        m.addAttribute("subcate_list",subcate_list);
			return "admin";
			}
			
			
		}
		@RequestMapping(value="/subupdatecate")
		public String updateCategory(@RequestParam("cid")String cid,ModelMap m){
			
			SubCategory cate=subcatdao.getCategory(cid);
			m.addAttribute("subcategoryrequest", true);
			m.addAttribute("subupdatecate", true);

			m.addAttribute("subcate",cate);

			String cate_list=subcatdao.getCategoryList(new SubCategory());
	        m.addAttribute("subcate_list",cate_list);
			return "admin";
			
			
			
		}
		// map the view to return String and execute only when method is post
			@RequestMapping(value = "/subcategoryupdatepost", method = RequestMethod.POST)

			public String updatecategoryData(@ModelAttribute("subcate") SubCategory subcate, ModelMap m) {
				// validating if save execute successful then returning message &
				// true(validated in admin.jsp)
				
				if (subcatdao.updateCategory(subcate)) {
					m.addAttribute("message", "Update Successfully");
					m.addAttribute("subcategoryrequest", true);
					m.addAttribute("savesubcate", true);

					String cate_list=subcatdao.getCategoryList(new SubCategory());
			        m.addAttribute("subcate_list",cate_list);
					return "admin";
				}

				else {
					m.addAttribute("subcategoryrequest", true);
					m.addAttribute("savesubcate", true);

					m.addAttribute("message", "Something Went Wrong");

					return "admin";

				}

			

			}
}
