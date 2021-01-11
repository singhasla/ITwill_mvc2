package com.spring.account;


//한사람의 계좌 정보를 저장할 VO
public class AccountVO {

	private String accountNo;
	private String custName;
	private int balance;
	
	public AccountVO() {
		
	}
	
	public AccountVO(String accountNO, String custName, int balance){
		this.accountNo = accountNO;
		this.custName = custName;
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	
}
