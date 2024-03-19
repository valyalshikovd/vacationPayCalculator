package com.example.vacationPayCalculator.sevices;

import java.time.LocalDate;
import java.util.List;



public interface HolidaysService {

    public boolean dateIsHoliday(LocalDate date);
    public List<LocalDate> getHolidays();

    public List<LocalDate> getHolidays(int year);
}
