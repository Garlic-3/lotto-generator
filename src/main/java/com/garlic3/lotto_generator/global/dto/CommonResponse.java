package com.garlic3.lotto_generator.global.dto;

import com.garlic3.lotto_generator.config.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse<T> {
    private String code;
    private String message;
    private T data;

    // 데이터를 포함하는 생성자
    public CommonResponse(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    // 데이터를 포함하지 않는 생성자
    public CommonResponse(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = null; // 데이터 부분을 null로 초기화
    }
}
