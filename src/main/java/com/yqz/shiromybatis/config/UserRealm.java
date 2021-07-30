package com.yqz.shiromybatis.config;

import com.yqz.shiromybatis.pojo.User;
import com.yqz.shiromybatis.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;
//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了授权--------------doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

//        info.addStringPermission("user:add");
//            拿到当前对象
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String username = (String) session.getAttribute("username");
        String role = userService.getRoleByName(username);
        info.addStringPermission(role);
        System.out.println(username+"的"+"角色是"+role);

        return info;


//        User user = (User) subject.getPrincipal(); 从doGetAuthenticationInfo传递User信息过来，需要配合Subject
    }

//    认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        System.out.println("执行了认证--------------------doGetAuthenticationInfo");
//        用户名密码数据库取
//        String username = "chenhc";
//        String password = "chc123";
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
          if(Objects.isNull(userService.getUserByName(((UsernamePasswordToken) token).getUsername()))){
              return null;
          }
        return new SimpleAuthenticationInfo(userService.getUserByName(userToken.getUsername()),userService.getUserByName(((UsernamePasswordToken) token).getUsername()).getPassword(),"");
    }
}
