/**
  *project project-frist
  *@author changchun.wu
  *2017年8月1日下午2:31:15
  */
package com.jianfei.ps.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jianfei.ps.entity.relation.RoleMenu;
import com.jianfei.ps.entity.relation.UserRole;
import com.jianfei.ps.entity.system.Users;
import com.jianfei.ps.service.relation.RoleMenuService;
import com.jianfei.ps.service.relation.UserRoleService;
import com.jianfei.ps.service.system.UsersService;
import com.jianfei.ps.tools.utils.HttpUtils;


@Controller
@RequestMapping(value="/")
public class LoginController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	@RequestMapping
	public String loginPage(){
		return "login";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model){
		return "login";
	}
	
	@PostMapping("/login")
	public String loginUsers(Model model,Users users,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Users user = usersService.findUsersByNcikname(users.getNickname());
		if (user != null) {
			if (users.getNickname().equals(user.getNickname()) && users.getPassword().equals(user.getPassword())) {
				user.setLoginTime(new Date());
				user.setIp(HttpUtils.getRemoteAddr(request));
				usersService.updateLoginTimeAndIp(user);
				//先根据用户查询角色
				UserRole userRole = userRoleService.findById(user.getId());

				//model 存放roleId
				model.addAttribute("roleid",userRole.getRoleId());
				model.addAttribute("user",users.getNickname());
				//再根据角色查询权限
				//framset页面之间的跳转传值使用request.getSession().setAttribute();
				request.getSession().setAttribute("rolemenus", this.roleMenuService.findMENU(userRole.getRoleId()));
				
				System.out.println("登录成功");
				return "jsp/index";
			} else if (users.getNickname().equals(user.getNickname()) && !users.getPassword().equals(user.getPassword())){
				response.sendRedirect("http://localhost:8080?error2=password");
				return "login";
			} else {
				response.sendRedirect("http://localhost:8080?error1=nickname");
				return "login";
			}
		}else {
			System.out.println("登录失败");
			response.sendRedirect("http://localhost:8080?error3=fail");
			return "login";
		}
	}
}
