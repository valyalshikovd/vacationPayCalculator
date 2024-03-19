package com.example.vacationPayCalculator.sevices.impl;

import com.example.vacationPayCalculator.dto.HolidayDto;
import com.example.vacationPayCalculator.sevices.HolidaysService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Сервис для получения списка праздничных дней.
 */
@Service
public class HolidayServiceImpl implements HolidaysService {
    private final RestTemplate restTemplate;
    private final List<LocalDate> localDates;

    /**
     * Конструктор, который инициализирует сервис и загружает список праздников.
     * @param restTemplate RestTemplate для взаимодействия с внешним API.
     */
    public HolidayServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        localDates = getHolidays();
    }

    @Override
    public List<LocalDate> getHolidays() {
        return getHolidays(LocalDate.now().getYear());
    }


    /**
     * Получает список праздничных дней на указанный год из внещнего API.
     * @param year год, для которого нужно получить список праздников.
     * @return список праздничных дней.
     */
    @Override
    public List<LocalDate> getHolidays(int year) {
        ResponseEntity<HolidayDto[]> holidays = restTemplate.getForEntity("https://date.nager.at/api/v3/publicholidays/" + year+"/RU", HolidayDto[].class);
        System.out.println(holidays);
        if(holidays.getBody() == null)
            return new LinkedList<>();
        return Arrays.stream(holidays.getBody()).map(HolidayDto::getDate).toList();
    }

    /**
     * Проверяет, является ли указанная дата праздничным днем.
     *
     * @param date дата, которую нужно проверить.
     * @return true, если дата является выходным или праздничным днем, false в противном случае.
     */
    @Override
    public boolean dateIsHoliday(LocalDate date){
        if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)
            return true;
        if(localDates.contains(date))
            return true;
        return false;
    }


}
