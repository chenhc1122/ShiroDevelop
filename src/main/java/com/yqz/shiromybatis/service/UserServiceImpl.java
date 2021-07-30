package com.yqz.shiromybatis.service;

import com.yqz.shiromybatis.mapper.UserMapper;
import com.yqz.shiromybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public int addUser(User user) {
        userMapper.addUser(user);
        return 0;
    }

    @Override
    public int addRole(String name, String role) {
        userMapper.addRole(name,role);
        return 0;
    }

    @Override
    public String getRoleByName(String name) {
       return userMapper.getRoleByName(name);

    }


}
