package com.codurance.amanbankkata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryShould {
    private final String TODAY = "12/05/2015";

    @Mock
    Clock clock;

    private TransactionRepository transactionRepository;


    @BeforeEach
    void setUp() {
        transactionRepository = new TransactionRepository(clock);
    }

    private Transaction transaction(String date, int amount) {
        return new Transaction(date, amount);
    }

    @Test
    void create_and_store_deposit_transaction() {
        given(clock.dateToString()).willReturn(TODAY);

        transactionRepository.addDeposit(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0), is(transaction(TODAY, 100))); //hashcode needed for this
    }

    @Test
    void create_and_store_withdrawal_transaction() {
        given(clock.dateToString()).willReturn(TODAY);

        transactionRepository.addWithdrawal(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0), is(transaction(TODAY, -100))); //hashcode needed for

    }
}
