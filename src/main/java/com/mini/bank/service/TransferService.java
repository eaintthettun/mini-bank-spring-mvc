package com.mini.bank.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.bank.dao.TblAccountDao;
import com.mini.bank.entity.TblAccount;
import com.mini.bank.entity.TblReceiveTransaction;
import com.mini.bank.entity.TblTransferTransaction;
import com.mini.bank.model.Transfer;

@Service
public class TransferService {
	@Autowired
	private TblAccountDao tblAccountDao;
	
	public void processTransferLoad(Transfer transfer,int custId) {
		transfer.setFrmAccounts(new ArrayList<TblAccount>());
		try {
			//get the accounts of login user from database
			for(TblAccount a:this.tblAccountDao.getAccountsByCust(custId)) {
				//copy one account by one account
				TblAccount tmp=new TblAccount();
				BeanUtils.copyProperties(a, tmp);
				//add the reference list of the controller
				transfer.getFrmAccounts().add(tmp);
			}
		}catch(Exception e) {e.printStackTrace();}
	}
	public void processTransfer(Transfer transfer) {
		String strFromAccNo=transfer.getFrmFromAccNo();
		String strToAccNo=transfer.getFrmToAccNo();
		Long amount=transfer.getFrmAmount();
		Long db_bal=this.tblAccountDao.getAccountByAccNo(strFromAccNo).getAccBalance();
		if(db_bal<amount) {
			transfer.setFrmErr(strFromAccNo+" has insufficient amount");
		}else {
			transfer.setFrmErr("");
			TblTransferTransaction transferTran=new TblTransferTransaction();
			transferTran.setToTranAccNo(strToAccNo);
			transferTran.setTranAmount(amount);
			transferTran.setTranDate(new Date());
			
			TblReceiveTransaction receiveTran=new TblReceiveTransaction();
			receiveTran.setFromTranAccNo(strFromAccNo);
			receiveTran.setTranAmount(amount);
			receiveTran.setTranDate(new Date());
			
			//do add,remove amount from tbl_account table in mysql
			this.tblAccountDao.transfer(strFromAccNo, strToAccNo, 
					amount,transferTran,receiveTran);
		}
	}
}
