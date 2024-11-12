package com.garlic3.lotto_generator.domian.luck.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class TodayLuckRequest {
    private String name;
    private LocalDate birthDate;
}
