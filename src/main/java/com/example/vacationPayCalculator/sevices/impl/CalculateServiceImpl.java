package com.example.vacationPayCalculator.sevices.impl;

import com.example.vacationPayCalculator.dto.SalaryDto;
import com.example.vacationPayCalculator.sevices.CalculateService;
import com.example.vacationPayCalculator.utils.DateParseUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@AllArgsConstructor
public class CalculateServiceImpl implements CalculateService {

    @Override
    public SalaryDto calculate(Integer days, Double averageSalary){
        if(days == null|| averageSalary == null)
            return new SalaryDto(0);
        return  new SalaryDto(averageSalary  * (days / 30.0));
    }

    @Override
    public SalaryDto calculate(Integer days, Double averageSalary, LocalDate date) {
        return calculate(countWorkDays(date, days), averageSalary);
    }


    private int countWorkDays(LocalDate date, int countOfDays){
        int countWorkDays = 0;
        LocalDate currDate = date;
        for(int i = 0; i < countOfDays; i++){
            if(!DateParseUtils.dateISWorkDay(currDate)){
                currDate = currDate.plusDays(1);
                continue;
            }
            countWorkDays++;
            currDate = currDate.plusDays(1);
            System.out.println(currDate);
        }
        return countWorkDays;
    }
}
