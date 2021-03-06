package com.test;

import java.util.Calendar;
import java.util.List;

public class BankAccount {
	static BankAccountDao bankAccountDao;
	static Calendar calendar;
	public static BankAccountDTO openAccount(String accountNumber) {
		BankAccountDTO accountDTO= new BankAccountDTO(accountNumber,0.0);
		bankAccountDao.save(accountDTO, calendar.getTimeInMillis());
		return accountDTO;
	}


	public static BankAccountDTO getAccount(String accountNumber) {
		BankAccountDTO result= bankAccountDao.getAccountbyAccountNumber(accountNumber);
		return result;
	}

	public static void deposit(String accountNumber, double amount, String description) {
		bankAccountDao.saveTransaction(accountNumber, amount, description,calendar.getTimeInMillis());
		BankAccountDTO accountDTO=getAccount(accountNumber);
		accountDTO.setBalance(amount);
		accountDTO.setDescription(description);
		bankAccountDao.save(accountDTO, calendar.getTimeInMillis());
	}

	public static void setBankAccountDao(BankAccountDao mockAccountDao) {
		bankAccountDao = mockAccountDao;
	}
	
	public static void setCalendar(Calendar mockCalendar) {
		calendar=mockCalendar;
	}

	public static void withDraw(String accountNumber, double amount, String description) {
		deposit(accountNumber, amount, description);
	}


	public static List<TransactionDTO> getListTransactionOccurred(String accountNumber) {
		return bankAccountDao.getListTransactionOccurred(accountNumber);
	}

	public static List<TransactionDTO> getListTransactionOccurred(String accountNumber, Long startTime, Long stopTime) {
		return bankAccountDao.getListTransactionOccurred(accountNumber, startTime, stopTime);
	}
	
}
