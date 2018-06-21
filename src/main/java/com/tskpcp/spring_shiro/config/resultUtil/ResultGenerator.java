package com.tskpcp.spring_shiro.config.resultUtil;

public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE="SUCCESS";

    public static Result genSuccessResult(){
        return new Result().setCode(ResultCodeEnum.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> genSuccessResult(T data){
        return new Result().setCode(ResultCodeEnum.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE).setData(data);
    }
    public static Result getFailResult(String message){
        return new Result().setCode(ResultCodeEnum.FAIL).setMessage(message);
    }
    public static Result getUnauthorizedResult(){
        return new Result().setCode(ResultCodeEnum.UNAUTHORIZED).setMessage("权限不足");
    }
}
