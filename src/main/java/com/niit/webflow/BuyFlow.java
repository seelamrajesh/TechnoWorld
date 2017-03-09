package com.niit.webflow;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import com.google.gson.Gson;
import com.niit.dao.CartDao;
import com.niit.dao.OrderDao;
import com.niit.dao.ProductDao;
import com.niit.model.Billing;
import com.niit.model.Cart;
import com.niit.model.Orders;
import com.niit.model.Product;
import com.niit.model.Shipping;

@Component
public class BuyFlow {
	
	@Autowired
	Orders order;
	
	@Autowired
	OrderDao orderdao;
	
	@Autowired 
	ProductDao prodao;
	
	@Autowired
	Product product;
	
	@Autowired
	Cart cart;
	
	@Autowired
	CartDao cartdao;
	HttpSession  session;
	String validdelete;
	
	public Orders startFlow(){
		
		

		return new Orders();
		
		
	}
	
	public String addShipping(Orders order,Shipping ship,RequestContext context){
		 
 
		
		Gson gson=new Gson();
		String ship_json= gson.toJson(ship);
		order.setShip_address(ship_json);
		   session = ((HttpServletRequest)context.getExternalContext().getNativeRequest()).getSession();
		   session.setAttribute("ship",ship);
		 return "success";
		
	}
		
	public String addBilling(Orders order,Billing bill,RequestContext context){
		
		Gson gson=new Gson();
		String bill_json= gson.toJson(bill);
		 order.setBill_address(bill_json);
		 double total = 0;
			String random_id = UUID.randomUUID().toString();
		    String id= bill.getProductid();
			product=prodao.getProduct(id);
			
			String	userid= SecurityContextHolder.getContext().getAuthentication().getName();
			StringBuilder pro=new StringBuilder(); 
			StringBuilder pro_nm=new StringBuilder(); 

			if(id.equals("null")){
				List<Cart>  carts=cartdao.getCartWithUserId(userid);
			 for(Cart cartvalue:carts){
				
				double price=cartvalue.getPrice();
				total=total+price;
				String pr=cartvalue.getPro_id();
				String prn=cartvalue.getPro_name();

				pro.append(pr).append(",");
				pro_nm.append(prn).append(",");
				
			}
			 
			 order.setPro_id(pro.toString());
			 order.setEmail_id(userid);
			 order.setOrder_id(random_id);
			 order.setStatus("m");
             order.setPro_name(pro_nm.toString());
			 order.setTotal(total);

				
		     session = ((HttpServletRequest)context.getExternalContext().getNativeRequest()).getSession();
	         session.setAttribute("carted_list",carts);
	         session.setAttribute("user", userid);
	         session.setAttribute("total",total);
	         session.setAttribute("bill",bill);
              validdelete="multi";
	        
	         return "multi";
			}
			else{
	             total=product.getPro_price();
	             Date date = new Date();
				 order.setTotal(total);
				 order.setEmail_id(userid);
				 order.setOrder_id(random_id);
				 order.setStatus("o");
                  order.setPro_name(product.getPro_name());
				 order.setPro_id(id);
	 			SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
	 			String date_car = date_format.format(date);
				 HttpSession  session = ((HttpServletRequest)context.getExternalContext().getNativeRequest()).getSession();
	        
	             session.setAttribute("onepro",product);
	             session.setAttribute("user", userid);
	             session.setAttribute("total",total);
		         session.setAttribute("bill",bill);
		         session.setAttribute("date", date_car);
		         validdelete="one";
		 		return "one";

			}
			
        
		
		
	}
	
	public String addOrder(Orders order){
		
		
		if( validdelete.equals("one")){
			orderdao.save_Order(order);
	        return "success";


		}
		else if( validdelete.equals("multi")){
			if(orderdao.save_Order(order)){
				String	userid= SecurityContextHolder.getContext().getAuthentication().getName();
                 List<Cart>  carts=cartdao.getCartWithUserId(userid);
                   
				 for(Cart cartvalue:carts){
						String cat_id=cartvalue.getCart_id();
						cartdao.delete_cart(cat_id);
						
					}
				 
				
			}
			

			return "success";
		}
		else{
			
			return "fail";
		}
       
		
	}
	
	

}
