package com.yqz.shiromybatis.service;



import com.yqz.shiromybatis.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    User getUserByName(String name);

    int addUser(User user);

    int addRole(String name,String role);

    String getRoleByName(String name);
}
