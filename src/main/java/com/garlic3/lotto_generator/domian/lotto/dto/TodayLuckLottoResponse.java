package com.garlic3.lotto_generator.domian.lotto.dto;

import com.garlic3.lotto_generator.domian.luck.dto.TodayLuckResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class TodayLuckLottoResponse {
    private String totalLuck;
    private String moneyLuck;
    private List<Integer> lottoNumbers;

    public static TodayLuckLottoResponse of(TodayLuckResponse todayLuckResponse, List<Integer> lottoNumbers) {
        TodayLuckLottoResponse response = new TodayLuckLottoResponse();
        response.totalLuck = todayLuckResponse.getTotalLuck();
        response.moneyLuck = todayLuckResponse.getMoneyLuck();
        response.lottoNumbers = lottoNumbers;
        return response;
    }
}