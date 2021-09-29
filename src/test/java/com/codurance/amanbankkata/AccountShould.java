package com.codurance.amanbankkata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountShould {

    @Mock
    TransactionRepository transactionRepository;
    @Mock
    StatementPrinter statementPrinter;


    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    void store_a_deposit_transaction() {
        account.deposit(100);

        verify(transactionRepository).addDeposit(100); //repository Pattern for stored trnasactions
    }

    @Test
    void store_withdrawal_transaction() {
        account.withdraw(100);

        verify(transactionRepository).addWithdrawal(100);
    }

    @Test
    void print_a_statement() {
        List<Transaction> transactions = asList(new Transaction("12/05/2015", 100));
        given(transactionRepository.allTransactions()).willReturn(transactions);

        account.printStatement(); //call this method => expect to allTransaction to return transaction and then ...

        verify(statementPrinter).print(transactions);
    }
}
