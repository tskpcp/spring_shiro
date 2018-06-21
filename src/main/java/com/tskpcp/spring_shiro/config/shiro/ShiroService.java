package com.tskpcp.spring_shiro.config.shiro;

import org.springframework.stereotype.Service;

@Service
public class ShiroService {

    public String getPasswordByUsername(String username){
        switch (username){
            case "1":
                return "1";
            case "2":
                return "2";
            default:
                return null;
        }
    }
}
