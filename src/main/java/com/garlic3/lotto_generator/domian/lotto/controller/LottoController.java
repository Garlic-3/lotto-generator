package com.garlic3.lotto_generator.domian.lotto.controller;

import com.garlic3.lotto_generator.domian.lotto.dto.TodayLuckLottoResponse;
import com.garlic3.lotto_generator.domian.lotto.service.LottoService;
import com.garlic3.lotto_generator.domian.luck.dto.TodayLuckRequest;
import com.garlic3.lotto_generator.domian.luck.dto.TodayLuckResponse;
import com.garlic3.lotto_generator.domian.luck.service.LuckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/lotto")
public class LottoController {

    private final LuckService luckService;

    private final LottoService lottoService;

    @GetMapping("/test")
    public ResponseEntity<TodayLuckLottoResponse> test(@RequestBody TodayLuckRequest todayLuckRequest) {

        TodayLuckResponse todayLuck = luckService.getTodayLuck(todayLuckRequest);

        TodayLuckLottoResponse response = lottoService.generateLottoNumber(todayLuck);

        return ResponseEntity.ok(response);
    }

}
