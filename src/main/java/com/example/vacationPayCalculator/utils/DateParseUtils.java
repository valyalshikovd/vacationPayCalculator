package com.example.vacationPayCalculator.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateParseUtils {


    public static LocalDate dateParse(String date){

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static boolean dateISWorkDay(LocalDate date){
        if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY){
            return false;
        }
        if(getHolidays().contains(date)){
            return false;
        }
        return true;
    }

    private static List<LocalDate> getHolidays(){
        List<LocalDate> holidays = new ArrayList<>();

        holidays.add(LocalDate.of(2024, 1, 1));
        holidays.add(LocalDate.of(2024, 1, 2));
        holidays.add(LocalDate.of(2024, 1, 3));
        holidays.add(LocalDate.of(2024, 1, 4));
        holidays.add(LocalDate.of(2024, 1, 5));
        holidays.add(LocalDate.of(2024, 1, 6));
        holidays.add(LocalDate.of(2024, 1, 7));
        holidays.add(LocalDate.of(2024, 1, 8));
        holidays.add(LocalDate.of(2024, 2, 23));
        holidays.add(LocalDate.of(2024, 2, 24));
        holidays.add(LocalDate.of(2024, 2, 25));
        holidays.add(LocalDate.of(2024, 3, 8));
        holidays.add(LocalDate.of(2024, 3, 9));
        holidays.add(LocalDate.of(2024, 3, 10));
        holidays.add(LocalDate.of(2024, 4, 28));
        holidays.add(LocalDate.of(2024, 4, 29));
        holidays.add(LocalDate.of(2024, 4, 30));
        holidays.add(LocalDate.of(2024, 5, 1));
        holidays.add(LocalDate.of(2024, 5, 9));
        holidays.add(LocalDate.of(2024, 5, 10));
        holidays.add(LocalDate.of(2024, 5, 11));
        holidays.add(LocalDate.of(2024, 5, 12));
        holidays.add(LocalDate.of(2024, 6, 12));
        holidays.add(LocalDate.of(2024, 11, 3));
        holidays.add(LocalDate.of(2024, 11, 4));
        holidays.add(LocalDate.of(2024, 12, 29));
        holidays.add(LocalDate.of(2024, 12, 30));
        holidays.add(LocalDate.of(2024, 12, 31));
        return holidays;
    }


}
