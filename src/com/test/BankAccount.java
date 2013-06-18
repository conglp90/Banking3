package com.test;

public class BankAccount {

	public static BankAccountDTO openAccount(String accountNumber) {
		// TODO Auto-generated method stub
		BankAccountDTO accountDTO= new BankAccountDTO(accountNumber,0.0);
		return accountDTO;
	}

}
