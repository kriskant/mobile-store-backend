package com.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.CategoryDAO;
import com.modal.Category;
import com.modal.Supplier;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	
	
	@Autowired
	SessionFactory sessionFactory;
	
	public CategoryDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public boolean insertCategory(Category category) {
		sessionFactory.getCurrentSession().saveOrUpdate(category);
		return true;
	}
	@Transactional
	public List<Category> retrieveCategory() {
		@SuppressWarnings("unchecked")
		List<Category> listCategory = (List<Category>) sessionFactory.getCurrentSession()
				.createCriteria(Category.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	        return listCategory;
	}
	@Transactional
	public Category removeCategoryById(int categoryid) {
		Category CategoryToDelete = new Category();
		CategoryToDelete.setCategoryid(categoryid);
		sessionFactory.getCurrentSession().delete(CategoryToDelete);
		return CategoryToDelete;
	}
	@Transactional
	public Category getCategoryById(int categoryid) {
		
		Session session=sessionFactory.openSession();
		 Category c=null;
		 try {
			 session.beginTransaction();
			 c=session.get(Category.class,categoryid);
			 session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return c;
	}
	

}
