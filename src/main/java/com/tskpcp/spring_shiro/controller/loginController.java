package com.tskpcp.spring_shiro.controller;

import com.tskpcp.spring_shiro.config.resultUtil.Result;
import com.tskpcp.spring_shiro.config.resultUtil.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {

    @PostMapping("/doLogin")
    public Result doLogin(String username,String password){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
        }
        catch (AuthenticationException e){
            token.clear();
            return ResultGenerator.getFailResult("登录失败，用户名或密码错误！");
        }
        return ResultGenerator.genSuccessResult("登录成功");
    }
}
