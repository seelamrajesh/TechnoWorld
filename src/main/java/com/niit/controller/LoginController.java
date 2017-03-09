package com.niit.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.CartDao;
import com.niit.dao.CategoryDao;
import com.niit.dao.OrderDao;
import com.niit.dao.ProductDao;
import com.niit.dao.RegisterDao;
import com.niit.model.Cart;
import com.niit.model.Category;
import com.niit.model.Login;
import com.niit.model.Orders;
import com.niit.model.Product;
import com.niit.model.Sign_list;

@Controller
// mapping login.jsp

public class LoginController {
	// injects Register DAO
	@Autowired
	RegisterDao regdao;
	
	@Autowired
	CartDao cartdao;
	
	@Autowired
	CategoryDao cate_dao;
	
	@Autowired
	ProductDao prodao;

	@Autowired
	Category cate;
	@Autowired
	Product prod;
	@Autowired
	OrderDao order;
	
	// map the view to return String execute if method is get
	@RequestMapping(value = "/loginrequest",method = RequestMethod.GET)
	public String loginView(ModelMap m, HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		// return the Login DO object
		m.addAttribute("login", new Login());
		// return value true,validated in view(index.jsp)
		
		
		String logout_valid=(String) session.getAttribute("Login");
		if( logout_valid.equals("Logout")){
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
					new SecurityContextLogoutHandler().logout(request, response, auth);
			
				m.addAttribute("LaunchRequest", true);
				session=request.getSession(true);
				Sign_list list = new Sign_list();
				list.setLogin("Login");
				list.setSignin("SignIn");
				list.setSignup("SignUp");
				session.setAttribute("Login", list.getLogin());
				session.setAttribute("SignUp", list.getSignup());
				session.setAttribute("SignIn", list.getSignin());
				session.setAttribute("role", null);

				session.setAttribute("cart_value", null);
				
				session.setAttribute("cart_size", 0);
		        session.setAttribute("myorsi", 0);

				String cate_list=cate_dao.getCategoryList(cate);
				 String prod_list=prodao.getProductList(prod);
				 session.setAttribute("prod_list", prod_list);
				session.setAttribute("cate_list", cate_list);
			}
		else{
			m.addAttribute("LoginRequest", true);
			Sign_list list = new Sign_list();
			list.setLogin("Login");
			list.setSignin("SignIn");
			list.setSignup("SignUp");
			session.setAttribute("Login", list.getLogin());
			session.setAttribute("SignUp", list.getSignup());
			session.setAttribute("SignIn", list.getSignin());
			session.setAttribute("cart_value", null);
			session.setAttribute("cart_size", 0);
			session.setAttribute("role", null);
	        session.setAttribute("myorsi", 0);


			String cate_list=cate_dao.getCategoryList(cate);
			 String prod_list=prodao.getProductList(prod);
			 session.setAttribute("prod_list", prod_list);
			session.setAttribute("cate_list", cate_list);
			
		}
	
			
	
		
		
		
	
	
			
			
	
	
		
		
		
		return "index";
		
		

	
      
	}

	// map the view to return String and execute only when method is post
	@RequestMapping(value = "/loginsuccess",method = RequestMethod.POST)
	public String loginPost(@RequestParam("email")String email,@RequestParam("password")String pass, ModelMap m, HttpSession session) {
		// if user is valid it return true
		regdao.isvalidUser(email,pass);
		/*
		 * if user is valid user and role=user then return to home page else if
		 * role=admin return to admin else return to login error message
		 * 
		 */
		String userid = SecurityContextHolder.getContext().getAuthentication().getName();
		

		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority authority : authorities) 
		{
		
		if ( authority.getAuthority().equals("ROLE_USER")  ) {
			
    
			m.addAttribute("LaunchRequest", true);
			m.addAttribute("loggedin", true);
			session.setAttribute("role", regdao.Username());
			Sign_list list = new Sign_list();
			list.setLogin("Logout");
			list.setSignin(regdao.Username());
			list.setSignup("SignUp");
			session.setAttribute("Login", list.getLogin());
			session.setAttribute("SignUp", list.getSignup());
			session.setAttribute("SignIn", list.getSignin());
			session.setAttribute("email", userid);
            List<Cart> cart=cartdao.getCartWithUserId(userid);
            int cart_size=cartdao.cart_size();
			session.setAttribute("cart_value", cart);
			session.setAttribute("cart_size", cart_size);
			List<Orders> myd=order.getOrderWithUserId(userid);
			int size=myd.size();
	        session.setAttribute("myorsi", size);


			return "index";
		} else if (authority.getAuthority().equals("ROLE_ADMIN")) {
			m.addAttribute("loggedin", true);
			m.addAttribute("loggedin", true);
			m.addAttribute("role", regdao.Username());
			Sign_list list = new Sign_list();
			list.setLogin("Logout");
			list.setSignin(regdao.Username());
			list.setSignup("SignUp");
			session.setAttribute("Login", list.getLogin());
			session.setAttribute("SignUp", list.getSignup());
			session.setAttribute("SignIn", list.getSignin());
			session.setAttribute("email", userid);
            List<Cart> cart=cartdao.getCartWithUserId(userid);
            int cart_size=cartdao.cart_size();
			session.setAttribute("cart_value", cart);
			session.setAttribute("cart_size", cart_size);
			
                 
			return "admin";
		
		} else {
			m.addAttribute("LoginRequest", true);
			m.addAttribute("entry", true);
			m.addAttribute("message", "password is incorrect or not a valid user");
		return "index";
		}
		}
		return "index";
	}
	

}
