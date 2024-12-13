package com.mini.bank.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mini.bank.entity.TblAccount;
import com.mini.bank.entity.TblCustomer;
import com.mini.bank.model.Customer;
import com.mini.bank.service.CustomerService;
import com.mini.bank.util.SessionUtil;

@Controller
public class HomeController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private SessionUtil sessionUtil;
	
	@GetMapping(value= {"/viewAccsPath"})
	public String processViewAccount(HttpSession s,Model model) {
		//check login session time out or not
		String viewName=this.sessionUtil.renderSessionTimeOut(s, model);
		//no session time out
		//get login customer
		TblCustomer cust=(TblCustomer)s.getAttribute("loginUser");
		//get all acccounts of that customer
		List<TblAccount> accList=new ArrayList<TblAccount>();
		//pass prepared list and login id
		this.customerService.processViewLoad(accList,cust.getCustId());
		model.addAttribute("accList", accList);
		return viewName!=null? viewName:"view_account";
	}
	@GetMapping(value= {"/viewTransPath"})
	public String renderViewTransaction(HttpSession s,Model model) {
		String viewName=this.sessionUtil.renderSessionTimeOut(s, model);
		TblCustomer cust=new TblCustomer(); //create a new customer
		cust.setCustId(
				((TblCustomer)(s.getAttribute("loginUser"))).getCustId()
		 );
		this.customerService.processTransactionLoad(cust);
		model.addAttribute("frmCustTran",cust);
		return viewName!=null?viewName:"view_transaction";
	}
	@GetMapping(value= {"/changePassPath"})
	public String renderChangePass(HttpSession s,Model model) {
		String viewName=this.sessionUtil.renderSessionTimeOut(s, model);
		if(viewName==null) {
			model.addAttribute("frmCust",new Customer());
		}
		return viewName!=null?
				viewName:"change_password";
	}
	@PostMapping(value= {"/changePassPath"})
	public String renderChangePassProcess(HttpSession s,Model model,
			@ModelAttribute("frmCust")Customer frmCust) {
		String viewName=this.sessionUtil.renderSessionTimeOut(s, model);
		TblCustomer cus=(TblCustomer)s.getAttribute("loginUser");
		cus.setCustPass(frmCust.getFrmCustPass());
		this.customerService.processChangePass(cus);
		return viewName!=null?viewName:"change_password";
	}
	@GetMapping(value= {"/logoutPath"})
	public String renderLogout(HttpSession s,Model model) {
		s.removeAttribute("loginUser");
		s.invalidate();
		model.addAttribute("frmCust", new Customer());
		return "login";
	}
}
