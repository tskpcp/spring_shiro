package com.tskpcp.spring_shiro.config;


import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class MySessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION="Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE="Stateless request";

    public MySessionManager(){
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response){
        String id= WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头中有Authorization 则其值为SessionId

        if(!StringUtils.isEmpty(id)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return id;
        }else{
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request,response);
        }
    }
    //https://blog.csdn.net/u013615903/article/details/78781166/
    //https://blog.csdn.net/u012343297/article/details/78919966
    //http://www.ityouknow.com/springboot/2017/06/26/springboot-shiro.html
}
