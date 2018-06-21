package com.tskpcp.spring_shiro.config.resultUtil;

public class Result<T> {
    private ResultCodeEnum code;
    private String message;
    private T data;

    public Result(){}
    public ResultCodeEnum getCode() {
        return code;
    }

    public Result setCode(ResultCodeEnum code) {
        this.code = code;
        return  this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return  this;
    }
}
