/**
  *project project-frist
  *@author changchun.wu
  *2017年8月1日下午2:31:15
  */
package com.jianfei.ps.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jianfei.ps.entity.system.Users;
import com.jianfei.ps.service.system.UsersService;
import com.jianfei.ps.tools.utils.HttpUtils;


@Controller
@RequestMapping(value="/system")
public class LoginController {
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping
	public String loginPage(){
		return "system/login";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model){
		return "system/login";
	}
	
	@PostMapping("/login")
	public String loginUsers(Model model,Users users,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Users user = usersService.findUsersByNcikname(users.getNickname());
		if (user != null) {
			if (users.getNickname().equals(user.getNickname()) && users.getPassword().equals(user.getPassword())) {
				user.setLoginTime(new Date());
				user.setIp(HttpUtils.getRemoteAddr(request));
				usersService.updateLoginTimeAndIp(user);
				System.out.println("登录成功");
				return "system/index";
			} else if (users.getNickname().equals(user.getNickname()) && !users.getPassword().equals(user.getPassword())){
				response.sendRedirect("http://localhost:8080/system?error2=password");
				return "system/login";
			} else {
				response.sendRedirect("http://localhost:8080/system?error1=nickname");
				return "system/login";
			}
		}else {
			System.out.println("登录失败");
			response.sendRedirect("http://localhost:8080/system?error3=fail");
			return "system/login";
		}
	}
}
