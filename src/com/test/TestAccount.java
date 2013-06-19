package com.test;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    public void OpenAccountMustSaveToDB() {
    	ArgumentCaptor<BankAccountDTO> accountDTOCaptor = ArgumentCaptor.forClass(BankAccountDTO.class);
    	BankAccount.openAccount("1234567890");
    	verify(mockAccountDao,times(1)).save(accountDTOCaptor.capture());
    	assertEquals(accountDTOCaptor.getValue().getBalance(), 0.0, 0.01);
    	assertEquals(accountDTOCaptor.getValue().getAccountNumber(),"1234567890");

    }
}
