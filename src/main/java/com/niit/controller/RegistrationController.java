package com.niit.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.RegisterDao;
import com.niit.model.Login;
import com.niit.model.Register;
import com.niit.model.Sign_list;

@Controller
// mapping register.jsp
@RequestMapping("/registerrequest")
public class RegistrationController {
	// injects Register DAO
	@Autowired
	RegisterDao dao;

	// map the view to return String execute if method is get
	@RequestMapping(method = RequestMethod.GET)
	public String registrationView(ModelMap m) {
		// return the Register DO object

		m.addAttribute("register", new Register());
		// return value true,validated in view(index.jsp)
		m.addAttribute("RegisterRequest", true);

		ArrayList<String> gender = new ArrayList<String>();
		gender.add("Male");
		gender.add("Female");
		// return gender list to view
		m.addAttribute("gender", gender);
		Sign_list list = new Sign_list();
		list.setLogin("Login");
		list.setSignin("SignIn");
		list.setSignup("SignUp");
		m.addAttribute("Login", list.getLogin());
		m.addAttribute("SignUp", list.getSignup());
		m.addAttribute("SignIn", list.getSignin());
		return "index";

	}

	@Transactional
	// map the view to return String and execute only when method is post

	@RequestMapping(method = RequestMethod.POST)
	public String registrationPost(@ModelAttribute("register") Register reg, ModelMap m,
			@RequestParam("inputpass") String pass) {
		/*
		 * validating if save execute successful then returning message &
		 * true(validated in index.jsp)
		 */
		String cpass = reg.getPassword();

		if (pass.equals(cpass)) {
			if (dao.saveRegister(reg)) {
				m.addAttribute("LoginRequest", true);
				m.addAttribute("login", new Login());
				m.addAttribute("message", "Registered successful");
				m.addAttribute("entry", true);
				System.out.println(cpass);

				return "index";

			} else {

				m.addAttribute("RegisterRequest", true);
				m.addAttribute("entry", true);
				ArrayList<String> gender = new ArrayList<String>();
				gender.add("Male");
				gender.add("Female");
				// return gender list to view
				m.addAttribute("gender", gender);
				m.addAttribute("message", "something went wrong");
				return "index";

			}
		}

		else {
			m.addAttribute("RegisterRequest", true);
			m.addAttribute("entry", true);
			ArrayList<String> gender = new ArrayList<String>();
			gender.add("Male");
			gender.add("Female");
			// return gender list to view
			m.addAttribute("gender", gender);
			m.addAttribute("message", "password you entered was wrong");
			return "index";

		}

	}
}
