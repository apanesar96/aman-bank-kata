package com.codurance.amanbankkata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class TransactionRepository {

    private Clock clock;
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionRepository(Clock clock) {

        this.clock = clock;
    }

    public void addDeposit(int amount) {
        Transaction depositTransaction = new Transaction(clock.dateToString(), amount);
        transactions.add(depositTransaction);
    }

    public void addWithdrawal(int amount) {
        Transaction withdrawTransaction = new Transaction(clock.dateToString(), -amount);
        transactions.add(withdrawTransaction);
    }

    public List<Transaction> allTransactions() {
        return unmodifiableList(transactions); //dont want to change list
    }
}
