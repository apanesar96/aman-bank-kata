package com.codurance.amanbankkata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
class PrintStatementFeatureShould {
    @Mock
    Console console;

    @Mock
    Clock clock;

    private Account account;

    @BeforeEach
    void setUp() {
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        StatementPrinter statementPrinter = new StatementPrinter(console);
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    void print_statement_containing_all_transactions() {
        given(clock.dateToString()).willReturn("10/01/2012", "13/01/2012", "14/01/2012");

        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);

        account.printStatement();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Date       || Amount || Balance"); //side effect
        inOrder.verify(console).printLine("14/01/2012||500||1400");
        inOrder.verify(console).printLine("13/01/2012||-100||900");
        inOrder.verify(console).printLine("10/01/2012||1000||1000");

    }
}
