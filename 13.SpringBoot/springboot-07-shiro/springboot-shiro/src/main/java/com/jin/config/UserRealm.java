package com.jin.config;

import com.jin.pojo.User;
import com.jin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的 UserRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal(); //拿到user对象

        //设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        //连接真实数据库
        User user = userService.queryUserByName(userToken.getUsername());
        if (user==null) { //没有这个人
            return null; //UnknownAccountException
        }

        //可以加密，盐值加密
        // 密码认证，shiro做
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
