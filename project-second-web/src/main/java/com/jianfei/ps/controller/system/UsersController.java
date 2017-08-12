/**
  *project project-frist
  *@author changchun.wu
  *2017年7月26日下午3:36:22
  */
package com.jianfei.ps.controller.system;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jianfei.ps.entity.common.Gender;
import com.jianfei.ps.entity.system.Roles;
import com.jianfei.ps.entity.system.Users;
import com.jianfei.ps.entity.relation.UserRole;
import com.jianfei.ps.service.relation.UserRoleService;
import com.jianfei.ps.service.system.RolesService;
import com.jianfei.ps.service.system.UsersService;


@Controller
@RequestMapping(value="/system/users")
public class UsersController{
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private UserRoleService userRoleSerivce;
	
	private void setModel (Model model) {
		model.addAttribute("gender",Gender.values());
		model.addAttribute("roles",rolesService.findAll());
		model.addAttribute("userRole",userRoleSerivce.findAllRoleId());
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String Forminsert(Model model){
		this.setModel(model);
		return "system/users/form";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(Users users ,Model model){
		System.out.println(users);
		Users user = this.usersService.findUsersByNcikname(users.getNickname());
		if (user != null) {
			System.out.println("昵称已存在,请更换");
			this.setModel(model);
			return "system/users/form";
		} 
		users.setCreateTime(new Date());
		this.usersService.insert(users);
		
		Users user_role = usersService.findUsersByNcikname(users.getNickname());
		
		for (Integer roleId : users.getRoleId()) {
			this.usersService.insertUserRoleId(user_role.getId(), roleId);
		}

		System.out.println("保存用户成功");
		return "redirect:/system/users";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String Formupdate(@PathVariable("id") int id,Model model){
		this.setModel(model);
		model.addAttribute("users",usersService.findById(id));
		return "system/users/form";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable("id") int id,Users users,Model model){
		Users user = this.usersService.findUsersByNcikname(users.getNickname());
		if (user != null && user.getId() != id) {
			this.setModel(model);
			System.out.println("昵称已存在,请更换!");
			return "system/users/form";
		}
		int result = usersService.update(users);
		if (result > 0) {
			System.out.println("更新成功");
		} else {
			System.out.println("更新失败");
			return "error/error";
		}
		return "redirect:/system/users";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") int id){
		int result = this.usersService.delete(id);
		if (result > 0) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
			return "error/error";
		}
		return "redirect:/system/users";
	}

	@RequestMapping
	public String list(Model model,Users users){
		System.out.println(users); 
		model.addAttribute("users",this.usersService.findAll());
		this.setModel(model);
		return "system/users/list";
	}
}
