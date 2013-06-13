package com.test;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: chauttm
 * Date: 6/7/13
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestTransaction {
//    TransactionDao mockTransactionDao = mock(TransactionDao.class);
//
//    @Before
//    public void setUp () {
//        reset(mockTransactionDao);
////        when(mockTransactionDao.save(any(TransactionDTO.class))).thenAnswer(new Answer() {
////            @Override
////            public BankAccountDTO answer(InvocationOnMock invocation) throws Throwable {
////                return (BankAccountDTO)invocation.getArguments()[0];
////            }
////        });
//        Transaction.setDao(mockTransactionDao);
//    }
//
//    @Test
//    public void newTransactionIsNotYetExecutedButPersistent () {
//        Transaction.createTransaction("1234567890", 100, "deposit");
//        ArgumentCaptor<TransactionDTO> transactionRecords = ArgumentCaptor.forClass(TransactionDTO.class);
//        verify(mockTransactionDao,times(1)).save(transactionRecords.capture());
//        assertEquals(transactionRecords.getValue().getAmount(), 100.0, 0.01);
//        assertEquals(transactionRecords.getValue().getAccountNumber(), "1234567890");
//        assertFalse(transactionRecords.getValue().isExecuted());
//        assertTrue(transactionRecords.getValue().getTimestamp()!=0);
//    }
}
