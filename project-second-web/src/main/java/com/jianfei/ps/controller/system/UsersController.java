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
import com.jianfei.ps.entity.system.Users;
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
		//Gender枚举
		model.addAttribute("gender",Gender.values());
		//查询所有角色
		model.addAttribute("roles",rolesService.findAll());
		//查询角色与用户关联
		model.addAttribute("userRole",userRoleSerivce.findAllRoleId());
		//分页操作
		model.addAttribute("page",new Users());
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String Forminsert(Model model){
		this.setModel(model);
		return "system/users/form";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(Users users ,Model model){
		//根据昵称查询用户是否已经存在
		Users user = this.usersService.findUsersByNcikname(users.getNickname());
		if (user != null) {
			System.out.println("昵称已存在,请更换");
			this.setModel(model);
			return "system/users/form";
		} 
		users.setCreateTime(new Date());
		this.usersService.insert(users);
		//根据昵称查询用户存在的ID
		Users user_role = usersService.findUsersByNcikname(users.getNickname());
		//用户与角色的关联的添加
		for (Integer roleId : users.getRoleId()) {
			this.usersService.insertUserRoleId(user_role.getId(), roleId);
		}

		System.out.println("保存用户成功");
		return "redirect:/system/users";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String Formupdate(@PathVariable("id") int id,Model model){
		this.setModel(model);
		//根据ID查询用户
		model.addAttribute("users",usersService.findById(id));
		return "system/users/form";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable("id") int id,Users users,Model model){
		//根据昵称查询用户
		Users user = this.usersService.findUsersByNcikname(users.getNickname());
		//判断昵称存在及ID是否相同
		if (user != null && user.getId() != id) {
			this.setModel(model);
			System.out.println("昵称已存在,请更换!");
			return "system/users/form";
		}
		int result = usersService.update(users);
		//修改用户与角色之间的关联,先删除后添加
		this.userRoleSerivce.delete(id);
		for (Integer roleId : users.getRoleId()) {
			this.usersService.insertUserRoleId(id, roleId);
		}
		
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
		this.userRoleSerivce.delete(id);
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
		//页面传输的pn,ps
		users.setPn(users.pn*users.ps);
		users.setPs(users.ps);
		//分页,条件,查询所有
		model.addAttribute("users",this.usersService.findCondition(users));
		//总记录条数
		int totalRecord = usersService.findCount();
		model.addAttribute("totalRecord",totalRecord);
		//分页的页数
		int pageNo = (totalRecord % new Users().pageSize) == 0 ? totalRecord / new Users().pageSize : totalRecord / new Users().pageSize + 1 ;
		model.addAttribute("pageNo",pageNo);
		//上一页的数值变化
		model.addAttribute("bianPageShang",users.pn/users.ps-1);
		//下一页的数值变化
		model.addAttribute("bianPageXia",users.pn/users.ps+1);
		
		this.setModel(model);
		return "system/users/list";
	}
}
