package com.example.vacationPayCalculator.controllers;


import com.example.vacationPayCalculator.dto.SalaryDto;
import com.example.vacationPayCalculator.sevices.CalculateService;
import com.example.vacationPayCalculator.sevices.HolidaysService;
import com.example.vacationPayCalculator.utils.DateParseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST контроллер для обработки запросов на расчет зарплаты.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class CalculateController {

    private CalculateService calculateService;
    private HolidaysService holidaysService;


    /**
     * Обрабатывает запрос на расчет зарплаты.
     *
     * @param averageSalary средняя зарплата.
     * @param weekendDays количество выходных дней.
     * @param date дата для расчета (необязательный параметр).
     * @return ResponseEntity, содержащий рассчитанную зарплату.
     */
    @RequestMapping("/calculate")
    public ResponseEntity<SalaryDto> calculate(@RequestParam Double averageSalary,
                                               @RequestParam Integer weekendDays,
                                               @RequestParam(required = false) String date){


        holidaysService.getHolidays();

        if(date != null){
            return ResponseEntity.ok(calculateService.calculate(weekendDays, averageSalary, DateParseUtils.dateParse(date)));
        }
        return ResponseEntity.ok(calculateService.calculate(weekendDays, averageSalary));

    }
}
