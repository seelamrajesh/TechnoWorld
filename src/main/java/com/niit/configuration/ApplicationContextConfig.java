package com.niit.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.Cart;
import com.niit.model.Category;
import com.niit.model.Orders;
import com.niit.model.Product;
import com.niit.model.Register;
import com.niit.model.SubCategory;
import com.niit.model.Supplier_Do;



@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class ApplicationContextConfig {
	// database information
		@Bean(name = "dataSource")
		public DataSource getSource() {
			DriverManagerDataSource source = new DriverManagerDataSource();
			source.setUrl("jdbc:h2:tcp://localhost/~/sa");
			source.setDriverClassName("org.h2.Driver");
			source.setUsername("sa");
			source.setPassword("tech");
		
			System.out.println("source");

			return source;

		}

		// hibernate property
		public Properties getHibernateProperties() {

			Properties p = new Properties();

			p.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			System.out.println("properites");

			return p;
		}

		// session
		@Autowired
		@Bean(name = "sessionFactory")
		public SessionFactory getSession(DataSource source) {

			LocalSessionFactoryBuilder session = new LocalSessionFactoryBuilder(source);

			session.addProperties(getHibernateProperties());
			session.addAnnotatedClass(Register.class);
			session.addAnnotatedClass(Category.class);
			session.addAnnotatedClass(Supplier_Do.class);
			session.addAnnotatedClass(SubCategory.class);
			session.addAnnotatedClass(Product.class);
			session.addAnnotatedClass(Cart.class);
			session.addAnnotatedClass(Orders.class);
			System.out.println("session");
			return session.buildSessionFactory();

		}

		// transaction manager
		@Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getHibernateTransaction(SessionFactory s) {
			HibernateTransactionManager hbm = new HibernateTransactionManager(s);

			return hbm;
		}

	

}
