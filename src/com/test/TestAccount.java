package com.test;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class TestAccount{
	BankAccountDao mockAccountDao = mock(BankAccountDao.class);
	
	@Before
    public void setUp () {
        reset(mockAccountDao);
        BankAccount.setBankAccountDao(mockAccountDao);
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
}
