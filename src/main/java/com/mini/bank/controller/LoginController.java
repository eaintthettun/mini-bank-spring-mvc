package com.mini.bank.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mini.bank.entity.TblCustomer;
import com.mini.bank.model.Customer;
import com.mini.bank.service.CustomerService;

@Controller
public class LoginController {
	@Autowired
	private CustomerService customerService;
	@GetMapping(value={"/"}) 
	public String initLoginForm(Model model) {
		model.addAttribute("frmCust",new Customer());
		return "login";
	}
	
	@PostMapping(value= {"/loginPath"})
	public String processLoginForm(
			@Validated @ModelAttribute("frmCust")Customer cust,BindingResult bresult,
			Model model,HttpSession session) {
		if(bresult.hasErrors()) {
			model.addAttribute("frmCust", cust);
			return "login";
		}
		TblCustomer tblCust=new TblCustomer();
		this.customerService.processLogin(cust,tblCust);
		System.out.println("Customer name="+tblCust.getCustName());
		if(tblCust==null || tblCust.getCustName()==null) {
			model.addAttribute("frmCust", cust);
			model.addAttribute("errLogin", "User Name and Password is mismatch");
			return "login";
		}else {
			session.setAttribute("loginUser", tblCust);
			return "home";
		}
	}
}
