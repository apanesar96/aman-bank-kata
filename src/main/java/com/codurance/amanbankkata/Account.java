package com.codurance.amanbankkata;

import java.util.List;

public class Account implements AccountService{

    private TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;

    public Account(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {

        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    @Override
    public void deposit(int amount) {
        transactionRepository.addDeposit(amount);
    }

    @Override
    public void withdraw(int amount) {
        transactionRepository.addWithdrawal(amount);
    }

    @Override
    public void printStatement() {
        List<Transaction> transactions = transactionRepository.allTransactions();
        statementPrinter.print(transactions);
    }
}
