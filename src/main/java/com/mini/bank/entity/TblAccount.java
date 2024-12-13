package com.mini.bank.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_account")
public class TblAccount {
	@Id
	@Column(name="acc_no")
	private String accNo;
	
	@Column(name="acc_open_date")
	private Date accOpenDate;
	
	@Column(name="acc_status")
	private int accStatus;
	
	@Column(name="acc_balance")
	private Long accBalance;
	
	@ManyToOne
	@JoinColumn(name="cust_id",nullable=false)
	private TblCustomer customer;
	
	@ManyToOne
	@JoinColumn(name="type_id",nullable=false)
	private TblAccountType accType;
	
	@OneToMany(mappedBy="account",fetch=FetchType.EAGER)
	private Set<TblTransferTransaction> transferTrans;
	
	@OneToMany(mappedBy="account",fetch=FetchType.EAGER)
	private Set<TblReceiveTransaction> receiveTrans;
	

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public Date getAccOpenDate() {
		return accOpenDate;
	}

	public void setAccOpenDate(Date accOpenDate) {
		this.accOpenDate = accOpenDate;
	}

	public int getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(int accStatus) {
		this.accStatus = accStatus;
	}

	public Long getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(Long accBalance) {
		this.accBalance = accBalance;
	}

	public TblCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(TblCustomer customer) {
		this.customer = customer;
	}

	public TblAccountType getAccType() {
		return accType;
	}

	public void setAccType(TblAccountType accType) {
		this.accType = accType;
	}

	public Set<TblTransferTransaction> getTransactions() {
		return transferTrans;
	}

	public void setTransactions(Set<TblTransferTransaction> transactions) {
		this.transferTrans = transactions;
	}

	public Set<TblTransferTransaction> getTransferTrans() {
		return transferTrans;
	}

	public void setTransferTrans(Set<TblTransferTransaction> transferTrans) {
		this.transferTrans = transferTrans;
	}

	public Set<TblReceiveTransaction> getReceiveTrans() {
		return receiveTrans;
	}

	public void setReceiveTrans(Set<TblReceiveTransaction> receiveTrans) {
		this.receiveTrans = receiveTrans;
	}
}
