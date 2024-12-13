package com.mini.bank.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mini.bank.entity.TblAccount;
import com.mini.bank.entity.TblReceiveTransaction;
import com.mini.bank.entity.TblTransferTransaction;
@Repository
@Transactional(rollbackFor=Exception.class)
public class TblAccountDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public List<TblAccount> getAccountsByCust(int custId){
		List<TblAccount> accList=new ArrayList<TblAccount>();
		Session s=this.sessionFactory.getCurrentSession();
		String hql="from TblAccount where customer.custId=:cid";
		Query q=s.createQuery(hql);
		q.setParameter("cid", custId);
		accList=q.getResultList();
		return accList==null || accList.isEmpty()?null:accList;
	}
	@Transactional
	public void transfer(String fromAccNo,String toAccNo,
			Long amount,TblTransferTransaction transferTran,TblReceiveTransaction receiveTran) {
		Session s=this.sessionFactory.getCurrentSession();
		
		TblAccount fromAcc=s.find(TblAccount.class, fromAccNo); //select * from tbl_account where acc_no=?
		fromAcc.setAccBalance(fromAcc.getAccBalance()-amount);
		s.update(fromAcc); //update tbl_account
		
		TblAccount toAcc=s.find(TblAccount.class, toAccNo);
		toAcc.setAccBalance(toAcc.getAccBalance()+amount);
		s.update(toAcc); //update tbl_account
		
		transferTran.setAccount(fromAcc);
		s.save(transferTran);
		
		receiveTran.setAccount(toAcc);
		s.save(receiveTran);
	}
	public TblAccount getAccountByAccNo(String accNo) {
		Session s=this.sessionFactory.getCurrentSession();
		//select * from table where primary key=value
		//HQL
		TblAccount acc=s.find(TblAccount.class, accNo);
		return acc;
	}
}
