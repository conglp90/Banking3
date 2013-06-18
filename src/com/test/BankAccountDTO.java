package com.test;

public class BankAccountDTO {

	private String accountNumber;
	private Double balance;

	public BankAccountDTO(String accountNumber, Double balance) {
		// TODO Auto-generated constructor stub
		this.accountNumber=accountNumber;
		this.balance=balance;
	}

	public Double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}

	

}
