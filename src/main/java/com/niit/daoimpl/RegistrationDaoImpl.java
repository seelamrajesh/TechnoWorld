package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.RegisterDao;
import com.niit.model.Login;
import com.niit.model.Register;

@Repository("RegisterDao")
// Registration DaoImpl implements Register DAO interface
public class RegistrationDaoImpl implements RegisterDao {
	// injects Session Factory

	@Autowired
	SessionFactory sessionFactory;

	String Role;
	String name;
	String email;
	// Constructor

	public RegistrationDaoImpl(SessionFactory session) {
		this.sessionFactory = session;
	}

	/*
	 * save method implemented from Product DAO interface. Insert the value into
	 * database
	 */
	@Transactional
	public boolean saveRegister(Register reg) {

		try {
			sessionFactory.getCurrentSession().save(reg);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	// checks whether the user is valid user
	@Transactional
	public boolean isvalidUser(String email,String password) {

		String email_id = email;
		String password_form = password;
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Register where  email =:email and password=:password");
		query.setParameter("email", email_id);
		query.setParameter("password", password_form);
		@SuppressWarnings("unchecked")
		List<Register> list = query.list();
		int size = list.size();

		if (size == 1) {
			for (Register register : list) {
				Role = register.getRole();
				name = register.getName();
                email=register.getEmail();
			}
			return true;
		} else {
			return false;
		}

	}

	// return valid role
	public String validrole() {

		return Role;
	}
	// return valid name

	public String Username() {

		return name;
	}

	@Override
	public String Email() {
		return email;
	}
	

}
