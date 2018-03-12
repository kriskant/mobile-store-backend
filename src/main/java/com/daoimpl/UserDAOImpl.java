 package com.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDAO;
import com.modal.Users;

@Repository
public class UserDAOImpl implements  UserDAO{
	
     @Autowired
	private SessionFactory sessionFactory;
     public UserDAOImpl(){}

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
     public void saveUser(Users users) {
 		
 		try {
 			Session session=sessionFactory.openSession();
 			Transaction tr=session.beginTransaction();
 			session.saveOrUpdate(users);
 			tr.commit();
 		
 		} catch (HibernateException e) {
 			e.printStackTrace();
 		}
 		
 	}

	public Users getUserById(int user_id) {
		
		return null;
	}

	public void removeUserById(int user_id) {
		
		
	}
	@SuppressWarnings("deprecation")
	@Transactional
	public Users get(String email) {	
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Users.class);
		c.add(Restrictions.eq("email", email));
		@SuppressWarnings("unchecked")
		List<Users> listUser = (List<Users>) c.list();
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		} else {
			return null;
		}

	}

	}

