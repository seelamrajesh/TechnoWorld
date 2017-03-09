package com.niit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.niit.dao.CartDao;
import com.niit.dao.CategoryDao;
import com.niit.dao.OrderDao;
import com.niit.dao.ProductDao;
import com.niit.model.Billing;
import com.niit.model.Cart;
import com.niit.model.Category;
import com.niit.model.Orders;
import com.niit.model.Product;
import com.niit.model.Shipping;
import com.niit.model.Sign_list;

@Controller
public class MainController {
       
	@Autowired
	CategoryDao cate_dao;
	
	@Autowired
	ProductDao prodao;
	
	@Autowired
	Category cate;
	@Autowired
	Product prod;
	
	@Autowired
	CartDao cart;
	
	@Autowired
	OrderDao order;
	@Autowired
	Shipping ship;
	@Autowired
	Billing bill;
	
	String cate_list;
	// mapping index.jsp
	@RequestMapping("/")
	public String indexView(ModelMap m,HttpSession session) {
		
		Sign_list list = new Sign_list();
		list.setLogin("Login");
		list.setSignin("SignIn");
		list.setSignup("SignUp");
		session.setAttribute("Login", list.getLogin());
		session.setAttribute("SignUp", list.getSignup());
		session.setAttribute("SignIn", list.getSignin());
		 cate_list=cate_dao.getCategoryList(cate);
		 String prod_list=prodao.getProductList(prod);
		 session.setAttribute("prod_list", prod_list);
		session.setAttribute("cate_list", cate_list);
		session.setAttribute("cart_value", null);
		session.setAttribute("cart_size", 0);
        session.setAttribute("myorsi", 0);

		
		
		
		
		return "index";
	}

	// mapping admin.jsp
	@RequestMapping("/adminrequest")
	public String adminView(ModelMap m) {
      m.addAttribute("adminhome",true);
		return "admin";
	}

	// mapping launch.jsp
	@RequestMapping("/launchrequest")
	public String launchView(Model m) {
		m.addAttribute("LaunchRequest", true);
		return "index";
	}
	@RequestMapping("/orderrequest")
	public String launchOrderView(Model m,@RequestParam("order")String status,HttpSession session) {
		m.addAttribute("LaunchRequest", true);
		if(status.equals("confirmed")){
			String	userid= SecurityContextHolder.getContext().getAuthentication().getName();

            List<Cart>  carts=cart.getCartWithUserId(userid);
           int size=carts.size();
			session.setAttribute("cart_value", carts);
			session.setAttribute("cart_size",size);

			m.addAttribute("confirm",true);
		}
		return "index";
	}
	@RequestMapping("/ordercancel")
	public String launchOrderCancelView(Model m,@RequestParam("order")String status) {
		m.addAttribute("LaunchRequest", true);
		if(status.equals("cancel")){
			m.addAttribute("cancel",true);
		}
		return "index";
	}

	// mapping about.jsp
	@RequestMapping("/about")
	public String aboutView(Model m) {
		m.addAttribute("AboutRequest", true);

		return "index";
	}

	// mapping contact.jsp
	@RequestMapping("/contact")
	public String contactView(Model m) {
		m.addAttribute("ContactRequest", true);

		return "index";

	}
	public void dynamicSession(HttpSession session){
		
		Sign_list list = new Sign_list();
		list.setLogin("Login");
		list.setSignin("SignIn");
		list.setSignup("SignUp");
		session.setAttribute("Login", list.getLogin());
		session.setAttribute("SignUp", list.getSignup());
		session.setAttribute("SignIn", list.getSignin());
		cate_list = cate_dao.getCategoryList(cate);
		String prod_list = prodao.getProductList(prod);
		session.setAttribute("prod_list", prod_list);
		session.setAttribute("cate_list", cate_list);
		session.setAttribute("cart_size", 0);
        session.setAttribute("myorsi", 0);

	}
	@RequestMapping("/myord")
	public String myOrder(ModelMap m,HttpSession session){
		ArrayList< Shipping> shiplist = new ArrayList<Shipping>();
		ArrayList< Billing> billlist = new ArrayList<Billing>();
		ArrayList< String> proidlist = new ArrayList<String>();
		ArrayList< String> proidlistname = new ArrayList<String>();


		String	userid= SecurityContextHolder.getContext().getAuthentication().getName();
		List<Orders> myd=order.getOrderWithUserId(userid);
		int size=myd.size();
		Gson gson=new Gson();
		
		for(Orders od:myd){
			String shipstr=od.getShip_address();
			String billstr=od.getBill_address();
			ship=gson.fromJson(shipstr, Shipping.class);
			bill=gson.fromJson(billstr, Billing.class);
			shiplist.add(ship);
			billlist.add(bill);
			String procheck=od.getPro_id();
			if(procheck.contains(",")){
			int	len=procheck.length();
			
			int startpos=0;
			for(int i=0;i<len;i++){
				char c=procheck.charAt(i);
				if(c==','){
				int endpos=i;
				String pro_id=procheck.substring(startpos, endpos++);
				proidlist.add(pro_id);
				startpos=endpos;
				}
				
			}
			String procheckname=od.getPro_name();
              int namelen=procheckname.length();
			int startposnam=0;
			for(int i=0;i<namelen;i++){
				char c=procheckname.charAt(i);
				if(c==','){
				int endposnm=i;
				String pro_name=procheckname.substring(startposnam, endposnm++);
				proidlistname.add(pro_name);
				System.out.println(pro_name);
				startposnam=endposnm;
				}
				
			}
			}
			
		}
		
		m.addAttribute("orderslist",myd);
		m.addAttribute("shiplist", shiplist);
		m.addAttribute("billlist", billlist);
        session.setAttribute("myorsi", size);
    	m.addAttribute("mul",false);

        if(!proidlist.isEmpty()){
        	m.addAttribute("mul",true);
    		m.addAttribute("multi", proidlist);
    		m.addAttribute("multiname", proidlistname);


        }
		
		m.addAttribute("myorder", true);
		return "index";
		
	}
}
