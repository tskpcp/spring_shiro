package com.tskpcp.spring_shiro.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    public ShiroService shiroService;
    /**
     * 授权用户权限
     */
     @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){

         SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();


         //获取用户
         SecurityProperties.User user=(SecurityProperties.User) SecurityUtils.getSubject().getPrincipal();
         //获取用户角色
         Set<String> roleSet=new HashSet<String>();
         roleSet.add("100002");
         authorizationInfo.setRoles(roleSet);
         //获取用户权限
         Set<String>permissionSet=new HashSet<String>();
         permissionSet.add("权限添加");
         permissionSet.add("权限删除");
         authorizationInfo.setStringPermissions(permissionSet);
         return authorizationInfo;
     }
    /**
     * 登录认证
     */
    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
//
//        //获取用户账号
//        String username=token.getPrincipal().toString();
//        String password=shiroService.getPasswordByUsername(username);
//        if(password!=null){
//            AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(username,password,getName());
//            return authenticationInfo;
//        }
//        return null;
//    }
    /**
     * 验证用户身份
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException{
        UsernamePasswordToken token=(UsernamePasswordToken) authcToken;
        String username=token.getUsername();
        String password=String.valueOf(token.getPassword());

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("nickname",username);
        String paw=password+username;
        String pawDES=MyDES.encryptBasedDes(paw);
        map.put("pswd",)

    }
}
