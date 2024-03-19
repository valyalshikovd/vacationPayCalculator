package com.example.vacationPayCalculator.sevices;

import com.example.vacationPayCalculator.dto.SalaryDto;

import java.time.LocalDate;
import java.util.Date;

public interface CalculateService {
    public SalaryDto calculate(Integer days, Double averageSalary);

    public SalaryDto calculate(Integer days, Double averageSalary, LocalDate date);
}
