/**
  *project project-frist
  *@author changchun.wu
  *2017年8月4日上午10:48:16
  */
package com.jianfei.ps.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jianfei.ps.entity.system.Roles;
import com.jianfei.ps.service.relation.RoleMenuService;
import com.jianfei.ps.service.relation.UserRoleService;
import com.jianfei.ps.service.system.MenusService;
import com.jianfei.ps.service.system.RolesService;


@Controller
@RequestMapping("/system/roles")
public class RolesController {
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private MenusService menusService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	private void setModel (Model model) {
		//用户与角色关联
		model.addAttribute("userRole",userRoleService.findAllUserId());
		//分页操作
		model.addAttribute("page",new Roles());
		//根据类型菜单查询权限
		model.addAttribute("menu",menusService.findMenusByType());
		//根据类型按钮查询权限
		model.addAttribute("button",menusService.findButtonByType());
		
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String Forminsert(Model model){
		this.setModel(model);
		return "system/roles/form";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(Roles roles ,Model model){

		Roles role = this.rolesService.findRolesByRolename(roles.getRolename());
		if (role != null) {
			System.out.println("角色已经存在,请更换!");
			return "system/roles/form";
		} 
		int result = this.rolesService.insert(roles);
		if (result > 0) {
			//根据角色名称查询角色
			Roles roleid = this.rolesService.findRolesByRolename(roles.getRolename());
			//接收页面传参menubutton
			for (String roleMenuId : roles.getMenubutton()) {
				//添加角色与权限的关联 
				this.rolesService.insertRoleMenu(roleid.getId(), 
				Integer.parseInt(roleMenuId.substring(0, roleMenuId.indexOf("-"))),
				Integer.parseInt(roleMenuId.substring(roleMenuId.indexOf("-")+1)));//字符串指定取值,转换成int型
					
				
			}
			System.out.println("保存角色成功");
		} else {
			System.out.println("保存角色失败");
		}
		return "redirect:/system/roles";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String Formupdate(@PathVariable("id") int id,Model model){
		this.setModel(model);
		//根据角色ID查询角色
		model.addAttribute("roles",rolesService.findById(id));
		//根据角色ID查询角色与权限之间的关联
		model.addAttribute("menubutton",roleMenuService.findRoleByRoleId(id));
		return "system/roles/form";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable("id") int id,Roles roles,Model model) {
		//根据角色名称查询角色是否已经存在
		Roles role = this.rolesService.findRolesByRolename(roles.getRolename());
		//判断角色名称已经存在及ID是否是与修改的ID相同
		if (role != null && role.getId() != id) {
			System.out.println("更新的角色名称已经存在,请更换");
			return "system/roles/form";
		}
		//修改角色
		int result = rolesService.update(roles);
		//修改角色与权限的关系,先删除后添加
		this.roleMenuService.delete(id);
		for (String rolemenuId : roles.getMenubutton()) {
			this.rolesService.insertRoleMenu(id, 
			Integer.parseInt(rolemenuId.substring(0, rolemenuId.indexOf("-"))),
			Integer.parseInt(rolemenuId.substring(rolemenuId.indexOf("-")+1)));//字符串指定取值,转换成int型
		}
		
		if (result > 0) {
			System.out.println("更新成功");
		} else {
			System.out.println("更新失败");
			return "system/roles/form";
		}
		return "redirect:/system/roles";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") int id){
		int result = this.rolesService.delete(id);
		if (result > 0) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
			return "error/error";
		}
		return "redirect:/system/roles";
	}

	@RequestMapping
	public String list(Model model,Roles roles){
		//页面传输的pn,ps
		roles.setPn(roles.pn*roles.ps);
		roles.setPs(roles.ps);
		model.addAttribute("roles",this.rolesService.findCondition(roles));
		
		//总记录条数
		int totalRecord = rolesService.findCount();
		model.addAttribute("totalRecord",totalRecord);
		//分页的页数
		int pageNo = (totalRecord % new Roles().pageSize) == 0 ? totalRecord / new Roles().pageSize : totalRecord / new Roles().pageSize + 1 ;
		model.addAttribute("pageNo",pageNo);
		//上一页的数值变化
		model.addAttribute("bianPageShang",roles.pn/roles.ps-1);
		//下一页的数值变化
		model.addAttribute("bianPageXia",roles.pn/roles.ps+1);
		
		this.setModel(model);
		return "system/roles/list";
	}
}
