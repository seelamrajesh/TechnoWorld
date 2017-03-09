package com.niit.model;

import org.springframework.stereotype.Component;

@Component
public class Sign_list {
	
	String login;
	String signin;
	String signup;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSignin() {
		return signin;
	}
	public void setSignin(String signin) {
		this.signin = signin;
	}
	public String getSignup() {
		return signup;
	}
	public void setSignup(String signup) {
		this.signup = signup;
	}
	

}
