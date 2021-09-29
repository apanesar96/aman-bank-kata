package com.codurance.amanbankkata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class StatementPrinterShould {

    private static final List<Transaction> NO_TRANSACTIONS = Collections.EMPTY_LIST;

    @Mock
    Console console;

    private StatementPrinter statementPrinter;

    @BeforeEach
    void setUp() {
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    void print_header() {
        statementPrinter.print(NO_TRANSACTIONS);
        verify(console).printLine("Date       || Amount || Balance");
    }

    @Test
    void print_statements_in_reverse_chronoligcal_order() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("10/01/2012", 1000),
                new Transaction("13/01/2012", -100),
                new Transaction("14/01/2012", 500)
        );


        statementPrinter.print(transactions);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Date       || Amount || Balance"); //side effect
        inOrder.verify(console).printLine("14/01/2012||500||1400");
        inOrder.verify(console).printLine("13/01/2012||-100||900");
        inOrder.verify(console).printLine("10/01/2012||1000||1000");

    }
}