package com.he.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Login {

    @RequestMapping("login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }
}
