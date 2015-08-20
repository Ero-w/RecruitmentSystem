package com.rs.service.impl;
import com.rs.dao.IAccountDAO;
import com.rs.model.Account;
import com.rs.service.IAccountService;


public class AccountService implements IAccountService{
	
	private IAccountDAO accountDAO;

	@Override
	public int login(Account account) {
		Account a=accountDAO.findById(account.getAid());
		if(a==null){//用户不存在
			return -1;
		}else if(!a.getPassowrd().equals(account.getPassowrd())){//密码错误
			return -2;
		}else{//登录成功
			return 1;
		}
	}
	
	
	
	public IAccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}


}
