package com.codurance.amanbankkata;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClockShould {

    @Test
    void return_todays_date_in_dd_MM_yyyy_format() {
        Clock clock = new TestClock();
        String date = clock.dateToString();
        assertThat(date, is("24/04/2015"));
    }

    private class TestClock extends Clock {
        @Override
        protected LocalDate today() {
             return LocalDate.of(2015, 04, 24);
        }

    }
}
