package com.he.mapper;

import com.he.entity.User;
import org.springframework.stereotype.Component;


public interface UserMapper {

    public User findUserByName(String username);

    public void regUser(User user);


}
