package com.test;




public class BankAccount {
	 private static BankAccountDao bankAccountDao;

	    public static BankAccountDTO openAccount(String accountNumber) {
	        BankAccountDTO account = new BankAccountDTO(accountNumber);
	        bankAccountDao.save(account);
	        return account;
	    }

	    public static void setBankAccountDao(BankAccountDao bankAccountDao) {
	        BankAccount.bankAccountDao = bankAccountDao;
	    }


}
