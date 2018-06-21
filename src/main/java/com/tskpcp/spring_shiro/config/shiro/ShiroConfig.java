package com.tskpcp.spring_shiro.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();


        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<String,String>();
        //注意过滤器配置顺序，不能颠倒
        //配置推出 过滤器，其中的具体的推出代码Shiro已经替我们实现了，登出后跳转配置loginUrl
        filterChainDefinitionMap.put("/logout","logout");
        //配置不会被拦截的连接，顺序判断
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/ajaxLogin/","anon");
        filterChainDefinitionMap.put("login","anon");
        filterChainDefinitionMap.put("/**","anon");
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/unauth");
        //登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器（由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     */
     @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
         HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
         //散列算法:这里使用MD5算法;
         hashedCredentialsMatcher.setHashAlgorithmName("md5");
         //散列的次数，比如散列两次，相当于 md5(md5(""));
         hashedCredentialsMatcher.setHashIterations(2);
         return hashedCredentialsMatcher;
     }
    /**
     * 自定义Realm
     */
    @Bean(name="myShiroRealm")
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm=new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理 使用redis
//        securityManager.setSessionManager();
        // 自定义缓存实现 使用redis
        //securityManager.setCacheManager();
        return securityManager;
    }
    /**
     * 自定义sessionManager
     */
    @Bean
    public SessionManager sessionManager(){
        MySession
    }
    /**
     * 配置shiro redisManager
     */


    /**
     * cacheManager 缓存 redis实现
     */

    /**
     * RedisSessionDAO shiro sessionDao层的实现，通过redis
     */

    /**
     * 开启shiro aop注解支持
     * 使用代理方式，所以需要开启代码支持
     */

    /**
     * 注册全局异常处理
     */
}
