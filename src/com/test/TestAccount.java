package com.test;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class TestAccount{
	private BankAccountDao mockAccountDao = mock(BankAccountDao.class);
	private Calendar mockCalendar = mock(Calendar.class);
	
	@Before
    public void setUp () {
        reset(mockAccountDao);
        BankAccount.setBankAccountDao(mockAccountDao);
        reset(mockCalendar);
        BankAccount.setCalendar(mockCalendar);
    }
	
    @Test
    public void newAccountHasZeroBalance() {
        BankAccountDTO accountDTO= BankAccount.openAccount("1234567890");
        assertEquals(accountDTO.getBalance(), 0.0, 0.01);
    }
    
    @Test
    public void OpenAccountMustSaveNewAccountToDB() {
    	ArgumentCaptor<BankAccountDTO> accountDTOCaptor = ArgumentCaptor.forClass(BankAccountDTO.class);
    	
    	BankAccount.openAccount("1234567890");
    	
    	verify(mockAccountDao,times(1)).save(accountDTOCaptor.capture());
    	assertEquals(accountDTOCaptor.getValue().getBalance(), 0.0, 0.01);
    	assertEquals(accountDTOCaptor.getValue().getAccountNumber(),"1234567890");

    }
    
	@Test
	public void testCanGetAccountByAccountNumber() {
		BankAccountDTO accountDTOPush = BankAccount.openAccount("0123456789");
		when(mockAccountDao.getAccountbyAccountNumber("0123456789")).thenReturn(accountDTOPush);
		
		BankAccountDTO accountPop = BankAccount.getAccount("0123456789");
		
		verify(mockAccountDao, times(1)).getAccountbyAccountNumber("0123456789");
		assertEquals(accountPop, accountDTOPush);
	}
	
	@Test
	public void testAfterDepositBalanceHasIncrease() {
		ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor.forClass(BankAccountDTO.class);
		BankAccountDTO accountDTO= BankAccount.openAccount("0123456789");
		when(mockAccountDao.getAccountbyAccountNumber("0123456789")).thenReturn(accountDTO);
		
		BankAccount.deposit("0123456789", 200.0, "Nhieu tien ko tieu het thi gui thoi");
		
		verify(mockAccountDao, times(2)).save(argument.capture());
		assertEquals(200.0, argument.getValue().getBalance(), 0.01);
		assertEquals("0123456789", argument.getValue().getAccountNumber());
		assertEquals("Nhieu tien ko tieu het thi gui thoi", argument.getValue().getDescription());
	}
	
	@Test
	public void testAfterDepositTransactionShouldBeSaveToDB() {
		ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Double> amountCaptor = ArgumentCaptor.forClass(Double.class);
		ArgumentCaptor<String> decriptionCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Long> timeStampCaptor = ArgumentCaptor.forClass(Long.class);
		BankAccountDTO bankAccount = BankAccount.openAccount("0123456789");
		when(mockAccountDao.getAccountbyAccountNumber("0123456789")).thenReturn(bankAccount);
		Long timeStamp = System.currentTimeMillis();
		when(mockCalendar.getTimeInMillis()).thenReturn(timeStamp);
		ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor.forClass(BankAccountDTO.class);
		
		
		BankAccount.deposit("0123456789", 200.0, "Nhieu tien ko tieu het thi gui thoi");
		
		
		verify(mockAccountDao, times(1)). saveTransaction(accountNumberCaptor.capture(), amountCaptor.capture(), decriptionCaptor.capture(), timeStampCaptor.capture());
		assertEquals("0123456789", accountNumberCaptor.getValue());
		assertEquals(200.0, amountCaptor.getValue(),0.01);
		assertEquals("Nhieu tien ko tieu het thi gui thoi",decriptionCaptor.getValue());
		assertEquals(timeStamp, timeStampCaptor.getValue());
	}
	
	@Test
	public void testAfterWithDrawBalanceHasIncrease() {
		ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor.forClass(BankAccountDTO.class);
		BankAccountDTO accountDTO= BankAccount.openAccount("0123456789");
		accountDTO.setBalance(200.0);
		when(mockAccountDao.getAccountbyAccountNumber("0123456789")).thenReturn(accountDTO);
		
		BankAccount.withDraw("0123456789", 100.0, "Nhieu tien ko tieu het thi gui thoi");
		
		verify(mockAccountDao, times(2)).save(argument.capture());
		assertEquals(100.0, argument.getValue().getBalance(), 0.01);
		assertEquals("0123456789", argument.getValue().getAccountNumber());
		assertEquals("Nhieu tien ko tieu het thi gui thoi", argument.getValue().getDescription());
	}

	@Test
	public void testAfterWithDrawTransactionShouldBeSaveToDB() {
		ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Double> amountCaptor = ArgumentCaptor.forClass(Double.class);
		ArgumentCaptor<String> decriptionCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Long> timeStampCaptor = ArgumentCaptor.forClass(Long.class);
		BankAccountDTO bankAccount = BankAccount.openAccount("0123456789");
		when(mockAccountDao.getAccountbyAccountNumber("0123456789")).thenReturn(bankAccount);
		Long timeStamp = System.currentTimeMillis();
		when(mockCalendar.getTimeInMillis()).thenReturn(timeStamp);
		ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor.forClass(BankAccountDTO.class);
		
		
		BankAccount.withDraw("0123456789", 200.0, "Nhieu tien ko tieu het thi gui thoi");
		
		
		verify(mockAccountDao, times(1)). saveTransaction(accountNumberCaptor.capture(), amountCaptor.capture(), decriptionCaptor.capture(), timeStampCaptor.capture());
		assertEquals("0123456789", accountNumberCaptor.getValue());
		assertEquals(200.0, amountCaptor.getValue(),0.01);
		assertEquals("Nhieu tien ko tieu het thi gui thoi",decriptionCaptor.getValue());
		assertEquals(timeStamp, timeStampCaptor.getValue());
	}
	
	@Test
	public void testGetListTransactionOccurred() {
		ArgumentCaptor<String> accountNumberCaptor = ArgumentCaptor.forClass(String.class);
		
		BankAccount.getListTransactionOccurred("0123456789");
		
		verify(mockAccountDao, times(1)).getListTransactionOccurred(accountNumberCaptor.capture());
		assertEquals("0123456789", accountNumberCaptor.getValue());
	}
}
