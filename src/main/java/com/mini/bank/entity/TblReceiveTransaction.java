package com.mini.bank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_receive_transaction")
public class TblReceiveTransaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tran_id")
	private int tranId;
	
	@Column(name="tran_amount")
	private Long tranAmount;
	
	
	@Column(name="tran_date")
	private Date tranDate;
	
	@Column(name="from_tran_acc_no")
	private String fromTranAccNo;
	
	@ManyToOne
	@JoinColumn(name="tran_acc_no")
	private TblAccount account;

	public int getTranId() {
		return tranId;
	}

	public void setTranId(int tranId) {
		this.tranId = tranId;
	}

	public Long getTranAmount() {
		return tranAmount;
	}

	public void setTranAmount(Long tranAmount) {
		this.tranAmount = tranAmount;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getFromTranAccNo() {
		return fromTranAccNo;
	}

	public void setFromTranAccNo(String fromTranAccNo) {
		this.fromTranAccNo = fromTranAccNo;
	}

	public TblAccount getAccount() {
		return account;
	}

	public void setAccount(TblAccount account) {
		this.account = account;
	}
}
