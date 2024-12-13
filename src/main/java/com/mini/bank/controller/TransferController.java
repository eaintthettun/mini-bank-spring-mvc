package com.mini.bank.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mini.bank.entity.TblCustomer;
import com.mini.bank.model.Transfer;
import com.mini.bank.service.TransferService;
import com.mini.bank.util.SessionUtil;

@Controller
public class TransferController {
	@Autowired 
	private TransferService transferService;
	@Autowired
	private SessionUtil sessionUtil;
	
	@GetMapping(value={"/transferOwnPath","/transferOtherPath"})
	public String renderTransferBoth(@RequestParam("type")String type,
			HttpSession s,Model model) {
		String viewName=this.sessionUtil.renderSessionTimeOut(s, model);
		Transfer transfer=new Transfer(); //create a new transfer
		
		//get all accounts of customer and put it into transfer accounts to load them into user interface
		this.transferService.processTransferLoad(transfer, ((TblCustomer)s.getAttribute(
				"loginUser")).getCustId()); 
		model.addAttribute("frmTransfer", transfer);
		model.addAttribute("frmType", type);
		return viewName!=null?viewName:"transfer";
	}
	@PostMapping(value={"/transferPath"})
	public String renderTransfer(HttpSession s,Model model,
		@ModelAttribute("frmTransfer")Transfer transfer,
			@RequestParam("type")String type) {
		String viewName=this.sessionUtil.renderSessionTimeOut(s, model);
		//fetch the data filled transfer from UI and do transfer process
		this.transferService.processTransfer(transfer);
		return transfer.getFrmErr()==""?
				"home":"transfer";
	}
}
