package com.yqz.shiromybatis.config;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;


//config在编辑阶段就会执行
@Configuration
public class ShiroConfig {

//    ShiroFilterFactoryBean
    @Bean(name="shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager securityManager ){
        System.out.println("-----------coming getShiroFilterFactoryBean---------------");
//  拦截
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//     设置安全管理器
        bean.setSecurityManager(securityManager);
//        添加shiro的内置过滤器
  /*
  * 无需认证就可以访问：anon
  * 必须认证了才能访问：authc
  * 必须拥有记住我功能才能用：user
  * 拥有对某个资源的权限才能访问：perms
  * 拥有某个角色权限才能访问：role
  *
  * */
        HashMap<String, String> map = new LinkedHashMap<>();

//       权限操作    未授权跳转到未授权页面
        map.put("/user/addUser","perms[user:add]");
        map.put("/user/updateUser","perms[user:update]");

         map.put("/user/*","authc");
        bean.setFilterChainDefinitionMap(map);
        bean.setLoginUrl("/tologin");
        bean.setUnauthorizedUrl("/401");
        return bean;
    }









//    DefaultWebSecurityManager
        @Bean
       public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){

            System.out.println("-----------coming  getDefaultWebSecurityManager-------------");
           DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//       关联realm
           securityManager.setRealm(userRealm);

           return securityManager;
       }



//    创建realm对象  需要自定义:step1
     @Bean
    public UserRealm userRealm(){

         System.out.println("----------coming userRealm-----------");


          return new UserRealm();
    }
}
