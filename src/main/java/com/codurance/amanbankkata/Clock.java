package com.codurance.amanbankkata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class  Clock {

    public String dateToString() {
        DateTimeFormatter ddMMyyFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return today().format(ddMMyyFormat);
    }

    protected LocalDate today() { //isolate & control the date
        return LocalDate.now();
    }
}
