package com.he.interceptor;

import com.he.entity.LoginTicket;
import com.he.entity.User;
import com.he.service.LoginTicketService;
import com.he.service.UserService;
import com.he.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class PassportInterceptor implements HandlerInterceptor {
	@Autowired
	private LoginTicketService loginTicketService;

	@Autowired
	private UserService userService;

	@Autowired
	private HostHolder hostHolder;

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		hostHolder.clear();
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView modelAndView)
			throws Exception {
		if (modelAndView != null && hostHolder.getUser() != null) {
			modelAndView.addObject("user", hostHolder.getUser());
		}
	}

	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse arg1, Object arg2)
			throws Exception {
		// TODO Auto-generated method stub
		String ticket = null;
		if (httpServletRequest.getCookies() != null) {
			for (Cookie cookie : httpServletRequest.getCookies()) {
				if (cookie.getName().equals("ticket")) {
					ticket = cookie.getValue();
					break;
				}
			}
		}

		if (ticket != null) {
			LoginTicket loginTicket = loginTicketService.selectByTicket(ticket);
			if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0) {
				return true;
			}

			User user = userService.selectById(loginTicket.getUserId());
			hostHolder.setUser(user);
		}
		return true;
	}
}
