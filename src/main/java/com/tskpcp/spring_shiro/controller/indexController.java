package com.tskpcp.spring_shiro.controller;

import com.tskpcp.spring_shiro.config.resultUtil.Result;
import com.tskpcp.spring_shiro.config.resultUtil.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {


    @GetMapping("/helloword")
    public Result helloword(){
        return ResultGenerator.genSuccessResult("helloword");
    }
}
