package com.he.service.impl;

import com.he.entity.LoginTicket;
import com.he.entity.User;
import com.he.mapper.LoginTicketMapper;
import com.he.mapper.UserMapper;
import com.he.service.UserService;
import com.he.utils.DiagnosisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    LoginTicketMapper loginTicketMapper;

    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    public void regUser(User user) {
        userMapper.regUser(user);

    }

    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userMapper.findUserByName(username);

        if (user == null) {
            map.put("msg", "用户名不存在");
            return map;
        }

        if (!DiagnosisUtil.MD5(password + user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "密码不正确");
            return map;
        }

        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        return map;
    }

    public User selectById(int id) {
        return null;
    }

    private String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketMapper.addTicket(ticket);
        return ticket.getTicket();
    }
}
