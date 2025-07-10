package com.example.devicemanage.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public static final int CODE_SUCCESS = 200;
    public static final int CODE_FAIL = 500;

    // 静态方法：成功返回数据
    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .code(CODE_SUCCESS)
                .message("success")
                .data(data)
                .build();
    }

    // 静态方法：成功无数据
    public static Result<Void> success() {
        return Result.<Void>builder()
                .code(CODE_SUCCESS)
                .message("success")
                .build();
    }

    // 静态方法：失败（默认消息）
    public static Result<Void> fail() {
        return Result.<Void>builder()
                .code(CODE_FAIL)
                .message("fail")
                .build();
    }



    // 静态方法：自定义状态码和消息
    public static Result<Void> fail(int code, String message) {
        return Result.<Void>builder()
                .code(code)
                .message(message)
                .build();
    }

    // 静态方法：带数据的失败响应
    public static <T> Result<T> fail(int code, String message, T data) {
        return Result.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
    // 静态方法：带泛型支持的失败响应（无数据）
    public static <T> Result<T> fail(String message) {
        return Result.<T>builder()
                .code(CODE_FAIL)
                .message(message)
                .data(null)
                .build();
    }

}
