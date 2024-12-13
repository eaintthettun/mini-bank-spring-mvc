package com.mini.bank.dao;


import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mini.bank.entity.TblCustomer;

@Repository
@Transactional
public class TblCustomerDao {
	public TblCustomerDao() {
		System.out.println("tbl customer dao call");
	}
	@Autowired
	private SessionFactory sessionFactory;
	
	public TblCustomer getCustomerByUname(String uname,String password) {
		TblCustomer tblCust = null;
		Session s=this.sessionFactory.getCurrentSession();
		String hql="from TblCustomer where custUname=:cname and custPass=:cpass";
		Query q=s.createQuery(hql);
		q.setParameter("cname", uname);
		q.setParameter("cpass", password);
		List resultList=q.getResultList();
		if(resultList!=null && !resultList.isEmpty()) {
			tblCust=(TblCustomer)resultList.get(0);
		}
		return tblCust;
	}
	
	public TblCustomer getCustomerByCustId(int custId) {
		TblCustomer tblCust=null;
		Session s=this.sessionFactory.getCurrentSession();
		String hql="from TblCustomer where custId=:id";
		Query q=s.createQuery(hql);
		q.setParameter("id", custId);
		List resultList=q.getResultList();
		if(resultList!=null && !resultList.isEmpty()) {
			tblCust=(TblCustomer) resultList.get(0);
		}
		return tblCust;
	}

	public void updateChangePassword(TblCustomer cust) {
		Session s=this.sessionFactory.getCurrentSession();
		s.update(cust);
		
	}
}
