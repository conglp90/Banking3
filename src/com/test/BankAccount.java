package com.test;

public class BankAccount {
	static BankAccountDao bankAccountDao;
	public static BankAccountDTO openAccount(String accountNumber) {
		BankAccountDTO accountDTO= new BankAccountDTO(accountNumber,0.0);
		bankAccountDao.save(accountDTO);
		return accountDTO;
	}

	public static void setBankAccountDao(BankAccountDao mockAccountDao) {
		bankAccountDao = mockAccountDao;
	}

	public static BankAccountDTO getAccount(String accountNumber) {
		BankAccountDTO result= bankAccountDao.getAccountbyAccountNumber(accountNumber);
		return result;
	}
	
}
