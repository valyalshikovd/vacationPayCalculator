package com.example.vacationPayCalculator.controllers;


import com.example.vacationPayCalculator.dto.SalaryDto;
import com.example.vacationPayCalculator.sevices.CalculateService;
import com.example.vacationPayCalculator.utils.DateParseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class CalculateController {

    private CalculateService calculateService;

    @RequestMapping("/calculate")
    public ResponseEntity<SalaryDto> calculate(@RequestParam Double averageSalary,
                                               @RequestParam Integer weekendDays,
                                               @RequestParam(required = false) String date){



        if(date != null){
            return ResponseEntity.ok(calculateService.calculate(weekendDays, averageSalary, DateParseUtils.dateParse(date)));
        }
        return ResponseEntity.ok(calculateService.calculate(weekendDays, averageSalary));

    }
}
