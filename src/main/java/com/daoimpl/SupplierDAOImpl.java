package com.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.SupplierDAO;
import com.modal.Supplier;

@Repository
public class SupplierDAOImpl implements SupplierDAO{
	public  SupplierDAOImpl(){}
	@Autowired
	private SessionFactory sessionFactory;
	
	 public SupplierDAOImpl(SessionFactory sessionFactory){
		 this.sessionFactory=sessionFactory; 
	 }
	 @Transactional
	public boolean insertSupplier(Supplier supplier) {
		 sessionFactory.getCurrentSession().saveOrUpdate(supplier);
			
			return true;
	}
	 @Transactional
	public Supplier removeSupplierById(int supplierid) {
		Supplier SupplierToDelete = new Supplier();
		SupplierToDelete.setSupplierid(supplierid);
		sessionFactory.getCurrentSession().delete(SupplierToDelete);
		return SupplierToDelete;
	}
	@Transactional
	public Supplier getSupplierById(int supplierid) {
		 Session session=sessionFactory.openSession();
		 Supplier s=null;
		 try {
			 session.beginTransaction();
			 s=session.get(Supplier.class,supplierid);
			 session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return s;
	}
	public List<Supplier> retrieveSupplier() {
		Session session=sessionFactory.openSession();
       session.beginTransaction();
       List<Supplier> listSupplier=session.createQuery("from Supplier").list();
       session.getTransaction().commit();
        return listSupplier;
	}
	public void updateSupp(Supplier s){
		Session session=sessionFactory.openSession();
	       session.beginTransaction();
	       session.saveOrUpdate(s);
	       session.getTransaction().commit();
	}
	public void deleteSupp(int supplierid){
		Session session=sessionFactory.openSession();
	       session.beginTransaction();
	       Supplier s=session.get(Supplier.class,supplierid);
	       session.delete(s);
	       session.getTransaction().commit();
	}
}
