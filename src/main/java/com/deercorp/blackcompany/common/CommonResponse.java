package com.deercorp.blackcompany.common;

import lombok.Data;

@Data
public class CommonResponse<T> {
    private String message;
    private String code;
    private T data;

    public CommonResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResponse<T> ok(T data) {
        return new CommonResponse<>("SUCCESS", "성공", data);
    }
}
