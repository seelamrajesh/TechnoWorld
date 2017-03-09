package com.niit.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
	
	@ExceptionHandler(value=CannotCreateTransactionException.class)
	public String h2ExceptionHandle(ModelMap m){
		
		m.addAttribute("",true);
		m.addAttribute("message", "Server is Busy");
		System.out.println("Method Executing");
		
		return "index";
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	
	public String primaryKeyExceptionHandle(ModelMap m){
		
		m.addAttribute("error",true);
		m.addAttribute("message", "PrimaryKey Busy");
		System.out.println("Method Executing");
		
		return "index";
	}
	
	

}
