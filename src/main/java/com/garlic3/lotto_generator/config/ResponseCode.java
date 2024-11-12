package com.garlic3.lotto_generator.config;

public enum ResponseCode {
    // COMMON
    SUCCESS("S_CM_001", "Operation successful"),
    FAILURE("F_CM_001", "Operation failed");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
