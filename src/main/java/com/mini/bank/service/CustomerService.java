package com.mini.bank.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mini.bank.dao.TblAccountDao;
import com.mini.bank.dao.TblCustomerDao;
import com.mini.bank.entity.TblAccount;
import com.mini.bank.entity.TblCustomer;
import com.mini.bank.model.Customer;

@Service
public class CustomerService {
	public CustomerService() {
		System.out.println("Customer service call");
	}
	@Autowired
	private TblCustomerDao tblCustomerDao;
	
	@Autowired 
	private TblAccountDao tblAccountDao;
	 
	
	@Transactional(readOnly=true)
	public void processLogin(Customer cust,TblCustomer tblCust) {
		try{
			BeanUtils.copyProperties(
				tblCustomerDao.getCustomerByUname
				(cust.getFrmCustName(), 
				cust.getFrmCustPass()
				),
				tblCust);
		}catch(Exception e) {
			e.printStackTrace();
		};
	}
	//get all accounts of a customer
	@Transactional(readOnly=true)
	public void processViewLoad(List<TblAccount> accList,int custId) {
		try {
			for(TblAccount a:this.tblAccountDao.getAccountsByCust(custId)) {
				//TblAccount tmp=new TblAccount();
				//BeanUtils.copyProperties(a, tmp);
				accList.add(a);
			}
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public void processTransactionLoad(TblCustomer cust) {
		try {
			BeanUtils.copyProperties(
					this.tblCustomerDao.getCustomerByCustId(cust.getCustId()),
					cust
					);
		}catch(Exception e) {e.printStackTrace();}
	}
	public void processChangePass(TblCustomer cust) {
		this.tblCustomerDao.updateChangePassword(cust);
	}
	
}
