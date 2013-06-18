package com.test;
import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class TestAccount{
    @Test
    public void newAccountHasZeroBalanceAndIsPersistent() {
        BankAccountDTO accountDTO= BankAccount.openAccount("1234567890");
        assertEquals(accountDTO.getBalance(), 0.0, 0.01);
    }
}
