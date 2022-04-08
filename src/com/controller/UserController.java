package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.User;
import com.service.UserService;

@Controller
public class UserController {
@Autowired
private UserService us;
@RequestMapping("/find_name")
public String find_name()
{
	User u=us.queryUserByUsername("admin");
	System.out.println(u.getEmail());
	return "user/regist";
}
@RequestMapping("/find_np")
public String find_np()
{
	User u=us.queryUserByUsernameAndPassword("admin", "admin");
	System.out.println(u.getEmail());
	return null;
}
@RequestMapping("/insert_u")
public String insert_u(User user,String repwd,String code,HttpSession session,Model model,HttpServletRequest req)
{
	String token=(String)req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
	req.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
	
	if(token != null && token.equalsIgnoreCase(code))
	{
		if(us.queryUserByUsername(user.getUsername())!=null)
		{
			model.addAttribute("msg", "用户名已存在！");
			model.addAttribute("username", user.getUsername());
			model.addAttribute("email", user.getEmail());
			return "user/regist";
		}else{
			int f=us.saveUser(user);
			return "user/regist_success";
		}
	}
	model.addAttribute("msg","验证码错误！");
	model.addAttribute("username", user.getUsername());
	model.addAttribute("email", user.getEmail());
	return "user/regist";
}
@RequestMapping("/login")
public String login(User user,HttpSession session,Model model)
{
	System.out.println(user.getUsername());
	System.out.println(user.getPassword());
	String username=user.getUsername();
	user=us.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
	if(user!=null)
	{
		session.setAttribute("user", user);
		return "user/login_success";
	}else
	{
		model.addAttribute("msg","用户名密码错误！");
		model.addAttribute("username", username);
		return "user/login";
	}
}
@RequestMapping("/log_out")
public String log_out(HttpSession session)
{
	session.invalidate();
	return "user/login";
}
}
