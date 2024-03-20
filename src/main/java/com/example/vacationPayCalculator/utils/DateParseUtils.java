package com.example.vacationPayCalculator.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParseUtils {
    public static LocalDate dateParse(String date){

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);

        } catch (Exception e) {
            return null;
        }
    }
}
