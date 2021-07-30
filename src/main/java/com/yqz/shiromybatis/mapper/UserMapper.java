package com.yqz.shiromybatis.mapper;

import com.yqz.shiromybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {


    List<User> getUserList();

    User getUserByName(String name);

    int addUser(User user);

    int addRole(String name,String role);

    String getRoleByName(String name);
}
