package com.he.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.he.entity.User;
import com.he.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

	@Autowired
	UserService userService;

	@RequestMapping("/login")
	public String login(Model model, @RequestParam("username") String username,
						@RequestParam("password") String password,
						@RequestParam(value="next", required = false) String next,
						@RequestParam(value="rememberme", defaultValue = "false") boolean rememberme,
						HttpServletResponse response) {
		try {
			Map<String, Object> map = userService.login(username, password);
			if (map.containsKey("ticket")) {
				Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
				cookie.setPath("/");
				if (rememberme) {
					cookie.setMaxAge(3600*24*5);
				}
				response.addCookie(cookie);
/*				eventProducer.fireEvent(new EventModel(EventType.LOGIN)
						.setExt("username", username).setExt("email", "hopehp108@163.com")
				);*/
				if (StringUtils.isNotBlank(next)) {
					return "redirect:" + next;
				}
				return "redirect:/";
			} else {
				model.addAttribute("msg", map.get("msg"));
				return "login";
			}

		} catch (Exception e) {
			logger.error("登陆异常");
			e.printStackTrace();
			return "login";
		}
	}
}
