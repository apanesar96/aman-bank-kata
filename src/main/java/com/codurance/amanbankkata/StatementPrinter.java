package com.codurance.amanbankkata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;
import static java.util.Collections.reverseOrder;

public class StatementPrinter {
    public static final String Header = "Date       || Amount || Balance";

    private Console console;
    private AtomicInteger runningBalance = new AtomicInteger(0);

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {

        console.printLine(Header);

        printStatementLines(transactions);

    }

    private void printStatementLines(List<Transaction> transactions) {
        List<String> formattedStatements = new ArrayList<>();

        for (Transaction transaction: transactions) {
            formattedStatements.add(format("%s||%d||%d", transaction.date(), transaction.amount(), runningBalance.addAndGet(transaction.amount())));
        }

        Collections.reverse(formattedStatements);
        for(String formattedStatement : formattedStatements) {
            console.printLine(formattedStatement);
        }
    }
}
