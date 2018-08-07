package com.he.service;

import com.he.entity.User;

import java.util.Map;

public interface UserService {

    public User findUserByName(String username);

    public void regUser(User user);

    public Map<String,Object> login(String username, String password);

    User selectById(int id);

}
