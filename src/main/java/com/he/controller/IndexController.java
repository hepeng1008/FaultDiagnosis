package com.he.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {

	
	@RequestMapping("/login")
	@ResponseBody
	public String hello(HttpServletRequest request, HttpServletResponse response){
		System.out.println("hello world!");
		return "hello world";
	}
}
