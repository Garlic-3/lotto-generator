package com.garlic3.lotto_generator.global.exception.handler;

import com.garlic3.lotto_generator.config.ResponseCode;
import com.garlic3.lotto_generator.global.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleException(Exception e) {
        // 모든 예외를 처리
        log.error("Unexpected Exception : ", e);
        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponse<>(ResponseCode.FAILURE));
    }
}
