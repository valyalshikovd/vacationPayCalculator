package com.example.vacationPayCalculator.sevices.impl;

import com.example.vacationPayCalculator.dto.SalaryDto;
import com.example.vacationPayCalculator.sevices.CalculateService;
import com.example.vacationPayCalculator.utils.DateParseUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CalculateServiceImplTest {

    @Autowired
    private CalculateService calculateService;

    @Test
    void calculateTest(){

        SalaryDto res = calculateService.calculate(30, 10000.0, DateParseUtils.dateParse("2024-03-20"));
        System.out.println(res.getVacationPay());
        assertTrue(Math.abs(7333 - res.getVacationPay()) < 1);

        res = calculateService.calculate(0, 100000.0, DateParseUtils.dateParse("2024-03-19"));
        assertEquals( res.getVacationPay() , 0);

        res = calculateService.calculate(30, (double) 0, DateParseUtils.dateParse("2024-03-19"));
        assertEquals( res.getVacationPay() , 0);
    }

    @Test
    public void testNegativeVacationDays() {
        assertThrows(IllegalArgumentException.class, () -> calculateService.calculate(-10, 10000.0, DateParseUtils.dateParse("2024-03-20")));
    }



}