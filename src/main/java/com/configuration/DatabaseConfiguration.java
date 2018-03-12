package com.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dao.AddressDAO;
import com.dao.CartDAO;
import com.dao.CategoryDAO;
import com.dao.OrdersDAO;
import com.dao.ProductDAO;
import com.dao.SupplierDAO;
import com.dao.UserDAO;
import com.daoimpl.AddressDAOImpl;
import com.daoimpl.CartDAOImpl;
import com.daoimpl.CategoryDAOImpl;
import com.daoimpl.OrdersDAOImpl;
import com.daoimpl.ProductDAOImpl;
import com.daoimpl.SupplierDAOImpl;
import com.daoimpl.UserDAOImpl;
import com.modal.Address;
import com.modal.Cart;
import com.modal.Category;
import com.modal.Orders;
import com.modal.Product;
import com.modal.Supplier;
import com.modal.Users;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement
public class DatabaseConfiguration {
@Autowired
@Bean(name="datasource")
	public DataSource getH2data(){
		DriverManagerDataSource Datasource=new DriverManagerDataSource();
	Datasource.setDriverClassName("org.h2.Driver");
		Datasource.setUrl("jdbc:h2:tcp://localhost/~/Sairam");
		Datasource.setUsername("sa");
		Datasource.setPassword("");
		System.out.println("H2 connected");
		return  Datasource;
	}
	private Properties getH2properties(){
		Properties p=new Properties();
		p.put("hibernate.show_sql", "true");
		p.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		p.put("hibernate.hbm2ddl.auto", "update");
		//p.put("hibernate.hbm2ddl.auto", "create");
		System.out.println("Hibernate configuration");
		System.out.println("Table created");
		return p;
	}
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getH2properties());
		sessionBuilder.addAnnotatedClass(Users.class);
		sessionBuilder.addAnnotatedClass(Supplier.class);
		sessionBuilder.addAnnotatedClass(Category.class);
		sessionBuilder.addAnnotatedClass(Product.class);
		sessionBuilder.addAnnotatedClass(Cart.class);
		sessionBuilder.addAnnotatedClass(Orders.class);
		sessionBuilder.addAnnotatedClass(Address.class);
		System.out.println("Session");
		return sessionBuilder.buildSessionFactory();
	}
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		System.out.println("Transaction");
		return transactionManager;
	}
	@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {
	    return new UserDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name="supplierDAO")
	public SupplierDAO getSupplierDAO(SessionFactory sessionFactory){
		return new SupplierDAOImpl(sessionFactory);
	}
	
	@Autowired
	@Bean(name="categoryDAO")
	public CategoryDAO getCategoryDAO(SessionFactory sessionFactory){
		return new CategoryDAOImpl(sessionFactory);
	}
	
	@Autowired
	@Bean(name="productDAO")
	public ProductDAO getProductDAO(SessionFactory sessionFactory){
		return new ProductDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name="cartDAO")
	public CartDAO getCartDAO(SessionFactory sessionFactory){
		return new CartDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name="ordersDAO")
	public OrdersDAO getOrdersDAO(SessionFactory sessionFactory){
		return new OrdersDAOImpl(sessionFactory);		
	}
	@Autowired
	@Bean(name="addressDAO")
	public AddressDAO getAddressDAO(SessionFactory sessionFactory){
		return new AddressDAOImpl(sessionFactory);	
	}
}	

