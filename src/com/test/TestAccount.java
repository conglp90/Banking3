package com.test;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

public class TestAccount{
    BankAccountDao mockAccountDao = mock(BankAccountDao.class);

    @Before
    public void setUp () {
        reset(mockAccountDao);
        BankAccount.setBankAccountDao(mockAccountDao);
    }

    @Test
    public void newAccountHasZeroBalanceAndIsPersistent() {
        BankAccount.openAccount("1234567890");
        ArgumentCaptor<BankAccountDTO> savedAccountRecords = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockAccountDao).save(savedAccountRecords.capture());
        assertEquals(savedAccountRecords.getValue().getBalance(), 0.0, 0.01);
        assertEquals(savedAccountRecords.getValue().getAccountNumber(), "1234567890");
    }
}
