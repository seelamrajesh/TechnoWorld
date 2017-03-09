package com.niit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.niit.dao.CartDao;
import com.niit.dao.CategoryDao;
import com.niit.dao.ProductDao;
import com.niit.fileupload.TextDocument;
import com.niit.model.Cart;
import com.niit.model.Category;
import com.niit.model.Login;
import com.niit.model.Product;
import com.niit.model.Sign_list;
import com.niit.model.SubCategory;

@Controller
public class UserProductController {
	@Autowired
	ProductDao prodao;

	@Autowired
	Product prod;

	@Autowired
	Cart cart;
	@Autowired
	CartDao cartdao;

	@Autowired
	Sign_list list;
	@Autowired
	CategoryDao cate_dao;

	@Autowired
	Category cate;
	@Autowired
	MainController maincl;

	String insteproid;
	String cate_list;

	@RequestMapping(value = "/userproduct", method = RequestMethod.GET)
	public String getUserProduct(@RequestParam("proid") String pro, ModelMap m, HttpSession session) {
		this.insteproid = pro;
		m.addAttribute("ProductUserRequest", true);
		List<Product> pro_userlist = prodao.userProductList(pro);
		List<Category> pro_cate = prodao.getCategoryListArray(pro);
		List<SubCategory> pro_sub_cate = prodao.getSubCategoryListArray(pro);
		
		m.addAttribute("catelist", pro_cate);
		m.addAttribute("prolist", pro_userlist);
		m.addAttribute("subcatelist", pro_sub_cate);
	
		String userid = SecurityContextHolder.getContext().getAuthentication().getName();
		if (userid.equals("anonymousUser") ) {
			maincl.dynamicSession(session);
		}
		
		return "index";

	}

	@RequestMapping(value = "/product_filter", method = RequestMethod.GET)

	public String getUserProductfilter(@RequestParam("sub_id") String sub_id, @RequestParam("proid") String pro,
			ModelMap m,HttpSession session) {

		m.addAttribute("ProductUserRequest", true);

		List<Product> prod_with_sub = prodao.getProductwithSubCate(sub_id);
		List<SubCategory> pro_sub_cate = prodao.getSubCategoryListArray(pro);
		List<Category> pro_cate = prodao.getCategoryListArray(pro);
		List<SubCategory> clickedsubcat = prodao.getSubCategoryClickArray(sub_id);
		m.addAttribute("clickedsubcat", clickedsubcat);

		m.addAttribute("catelist", pro_cate);
		m.addAttribute("subcatelist", pro_sub_cate);
		m.addAttribute("prolist", prod_with_sub);
		String userid = SecurityContextHolder.getContext().getAuthentication().getName();
		if (userid.equals("anonymousUser") ) {
			maincl.dynamicSession(session);
		}
		return "index";

	}

	@RequestMapping(value = "/product_display", method = RequestMethod.GET)
	public String getParticularProductDisplay(@RequestParam("product_id") String proid, ModelMap m,
			HttpSession session) {

		m.addAttribute("ProductRequestDisplay", true);
		session.setAttribute("productid", proid);
		BufferedReader br = TextDocument.ReadText(proid + ".txt");
		if (br != null) {
			StringBuilder builder = new StringBuilder();
			String line;
			try {
				while ((line = br.readLine()) != null) {

					builder.append(line + "\n");

				}
				m.addAttribute("line", builder);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			m.addAttribute("line", "Specification will be added soon..");

		}
		List<Product> pro_part_list = prodao.getParticularProduct(proid);

		m.addAttribute("pro_part_list", pro_part_list);
		m.addAttribute("product", proid);

		return "index";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String addToCart(@RequestParam("pro_id") String proid, ModelMap m, HttpSession session) {
		if (!session.getAttribute("SignIn").equals("SignIn")) {
			String random_id = UUID.randomUUID().toString();

			Date date = new Date();

			SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
			String date_car = date_format.format(date);

			List<Product> pro_userlist = prodao.userProductList(insteproid);
			List<Category> pro_cate = prodao.getCategoryListArray(insteproid);
			List<SubCategory> pro_sub_cate = prodao.getSubCategoryListArray(insteproid);
			prod = prodao.getProduct(proid);
			m.addAttribute("catelist", pro_cate);
			m.addAttribute("prolist", pro_userlist);
			m.addAttribute("subcatelist", pro_sub_cate);
			String user = (String) session.getAttribute("SignIn");
			String email = (String) session.getAttribute("email");

			if (prod != null) {

				cart.setCart_id(random_id);
				cart.setPro_id(prod.getPro_id());
				cart.setPro_name(prod.getPro_name());
				cart.setPrice(prod.getPro_price());
				cart.setDate_cart(date_car);
				cart.setEmai_id(email);
				if (cartdao.save_cart(cart, email)) {
					m.addAttribute("ProductUserRequest", true);

					List<Cart> cart = cartdao.getCartWithUserId(email);
					int cart_size = cartdao.cart_size();
					session.setAttribute("cart_value", cart);
					session.setAttribute("cart_size", cart_size);
				} else {
					List<Cart> cart_list = cartdao.getCartWithUserId(email);
					m.addAttribute("message", "product already exists");

					m.addAttribute("cart_list", cart_list);
					m.addAttribute("cartdisplay", true);
				}
			}

			return "index";
		}

		else {
			m.addAttribute("LoginRequest", true);
			m.addAttribute("login", new Login());
			m.addAttribute("entry", true);
			m.addAttribute("message", "Must Login");

			return "index";

		}
	}

	@RequestMapping("/cartdisplay")
	public String cart_display(ModelMap m, HttpSession session) {
		session.setAttribute("productid","null");

		String email = (String) session.getAttribute("email");
		List<Cart> cart_list = cartdao.getCartWithUserId(email);

		m.addAttribute("cart_list", cart_list);
		m.addAttribute("cartdisplay", true);

		return "index";

	}

	@RequestMapping("/delete_cart")

	public String cart_Delete(@RequestParam("cart_id") String cat_id, ModelMap m, HttpSession session) {

		if (cartdao.delete_cart(cat_id)) {
			String email = (String) session.getAttribute("email");
			List<Cart> cart_list = cartdao.getCartWithUserId(email);
			m.addAttribute("cart_list", cart_list);
			int cart_size = cartdao.cart_size();

			session.setAttribute("cart_value", cart_list);

			session.setAttribute("cart_size", cart_size);

			m.addAttribute("cartdisplay", true);
			return "index";

		} else {
			String email = (String) session.getAttribute("email");

			List<Cart> cart_list = cartdao.getCartWithUserId(email);

			m.addAttribute("cart_list", cart_list);
			m.addAttribute("cartdisplay", true);
			return "index";

		}

	}

	@RequestMapping("/cart_quantity")
	public String cart_update(@RequestParam("udate_quantity") int q_id, @RequestParam("cart_id") String c_id,
			@RequestParam("pro_id") String p_id, ModelMap m, HttpSession session) {
		String email = (String) session.getAttribute("email");

		if (cartdao.update_cart(c_id, q_id, p_id)) {

			List<Cart> cart_list = cartdao.getCartWithUserId(email);
			m.addAttribute("cart_list", cart_list);
			m.addAttribute("cartdisplay", true);
			m.addAttribute("updated", true);
			return "index";

		} else {
			List<Cart> cart_list = cartdao.getCartWithUserId(email);
			m.addAttribute("cart_list", cart_list);
			m.addAttribute("cartdisplay", true);
			m.addAttribute("notupdated", true);

			return "index";

		}
	}
}
