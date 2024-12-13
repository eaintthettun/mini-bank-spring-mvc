package com.mini.bank.model;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Customer {
	@NotEmpty(message="User name is required")
	private String frmCustName;
	@Size(min=4,max=10)
	private String frmCustPass;
	
	public String getFrmCustName() {
		System.out.println("Model gettter");
		return frmCustName;
	}
	public void setFrmCustName(String frmCustName) {
		System.out.println("Model settter");
		this.frmCustName = frmCustName;
	}
	public String getFrmCustPass() {
		return frmCustPass;
	}
	public void setFrmCustPass(String frmCustPass) {
		this.frmCustPass = frmCustPass;
	}
}
