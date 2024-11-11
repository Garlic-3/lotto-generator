package com.garlic3.lotto_generator.domian.luck.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.garlic3.lotto_generator.domian.luck.dto.YearRequest;
import com.garlic3.lotto_generator.domian.luck.service.LuckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/luck")
public class LuckController {
    private final LuckService luckService;

    @GetMapping("/save")
    public ResponseEntity<String> saveLuck(@RequestBody YearRequest yearRequest) throws JsonProcessingException {
        int year = yearRequest.getYear();

        // Loop through each day of the year
        for (int month = 1; month <= 12; month++) {
            for (int day = 1; day <= YearMonth.of(year, month).lengthOfMonth(); day++) {
                // Format the date as "YYYYMMDD"
                String date = String.format("%04d%02d%02d", year, month, day);

                // Call saveHoroscopeDate with the formatted date
                luckService.saveLuckData(date);
            }
        }
        return ResponseEntity.ok("OK");
    }

}
