package com.example.vacationPayCalculator.sevices.impl;

import com.example.vacationPayCalculator.dto.SalaryDto;
import com.example.vacationPayCalculator.sevices.CalculateService;
import com.example.vacationPayCalculator.sevices.HolidaysService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Сервис для расчета зарплаты.
 */
@Service
@AllArgsConstructor
public class CalculateServiceImpl implements CalculateService {
    HolidaysService holidaysService;


    /**
     * Рассчитывает зарплату за указанное количество дней.
     *
     * @param days количество дней отработанных в месяце.
     * @param averageSalary средняя дневная зарплата.
     * @return объект `SalaryDto` с рассчитанной зарплатой.
     * @throws IllegalArgumentException если days или averageSalary меньше нуля или null.
     */
    @Override
    public SalaryDto calculate(Integer days, Double averageSalary){
        if(days < 0 || averageSalary < 0)
            throw new IllegalArgumentException();
        if(days == null|| averageSalary == null)
            throw new IllegalArgumentException();
        return  new SalaryDto(averageSalary  * (days / 30.0));
    }

    /**
     * Рассчитывает зарплату за указанное количество дней, учитывая праздники.
     *
     * @param days количество дней отработанных в месяце.
     * @param averageSalary средняя дневная зарплата.
     * @param date дата начала периода расчета.
     * @return объект `SalaryDto` с рассчитанной зарплатой.
     * @throws IllegalArgumentException если days меньше нуля или null.
     */
    @Override
    public SalaryDto calculate(Integer days, Double averageSalary, LocalDate date) {
        return calculate(countWorkDays(date, days), averageSalary);
    }

    /**
     * Подсчитывает количество рабочих дней за указанный период с учетом праздников.
     *
     * @param date дата начала периода.
     * @param countOfDays количество дней в периоде.
     * @return количество рабочих дней в периоде.
     * @throws IllegalArgumentException если countOfDays меньше нуля.
     */
    private int countWorkDays(LocalDate date, int countOfDays){
        if(countOfDays < 0)
            throw new IllegalArgumentException();
        int countWorkDays = 0;
        LocalDate currDate = date;

        holidaysService.setYearOfHolidays(currDate.getYear());

        for(int i = 0; i < countOfDays; i++){
            if(holidaysService.dateIsHoliday(currDate)){
                currDate = currDate.plusDays(1);
                continue;
            }

            //если год меняется тогда, подбираем праздники из другого года
            if(currDate.getYear() != currDate.plusDays(1).getYear()){
                holidaysService.setYearOfHolidays(currDate.plusDays(1).getYear());
            }
            countWorkDays++;
            currDate = currDate.plusDays(1);
        }
        return countWorkDays;
    }
}
