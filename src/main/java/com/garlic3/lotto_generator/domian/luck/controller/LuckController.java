package com.garlic3.lotto_generator.domian.luck.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.garlic3.lotto_generator.domian.luck.dto.TodayLuckRequest;
import com.garlic3.lotto_generator.domian.luck.dto.TodayLuckResponse;
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

        log.debug("save luck start for year : {}", yearRequest.getYear());

        // 1년 모든 날짜동안 반복
        for (int month = 1; month <= 12; month++) {
            for (int day = 1; day <= YearMonth.of(year, month).lengthOfMonth(); day++) {
                // 날짜 형식 "YYYYMMDD" 로 변경
                String date = String.format("%04d%02d%02d", year, month, day);
                // api 호출하여 운세 저장
                luckService.saveLuckData(date);
            }
        }

        log.debug("save luck end for year : {}", yearRequest.getYear());

        return ResponseEntity.ok("OK");
    }

}
