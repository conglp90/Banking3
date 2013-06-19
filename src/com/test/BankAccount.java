package com.test;

public class BankAccount {
	static BankAccountDao bankAccountDao;
	public static BankAccountDTO openAccount(String accountNumber) {
		// TODO Auto-generated method stub
		BankAccountDTO accountDTO= new BankAccountDTO(accountNumber,0.0);
		return accountDTO;
	}

	public static void setBankAccountDao(BankAccountDao mockAccountDao) {
		bankAccountDao = mockAccountDao;
	}
	
}
