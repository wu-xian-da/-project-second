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
import com.jianfei.ps.entity.system.Users;
import com.jianfei.ps.service.relation.UserRoleService;
import com.jianfei.ps.service.system.RolesService;


@Controller
@RequestMapping("/system/roles")
public class RolesController {
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	private void setModel (Model model) {
		model.addAttribute("userRole",userRoleService.findAllUserId());
		model.addAttribute("page",new Roles());
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String Forminsert(Model model){
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
			System.out.println("保存角色成功");
		} else {
			System.out.println("保存角色失败");
		}
		return "redirect:/system/roles";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String Formupdate(@PathVariable("id") int id,Model model){
		model.addAttribute("roles",rolesService.findById(id));
		return "system/roles/form";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable("id") int id,Roles roles,Model model) {
		System.out.println(roles);
		Roles role = this.rolesService.findRolesByRolename(roles.getRolename());
		System.out.println(role);
		if (role != null && role.getId() != id) {
			System.out.println("更新的角色名称已经存在,请更换");
			return "system/roles/form";
		}
		int result = rolesService.update(roles);
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
		int pageNo = (totalRecord % new Users().pageSize) == 0 ? totalRecord / new Users().pageSize : totalRecord / new Users().pageSize + 1 ;
		model.addAttribute("pageNo",pageNo);
		//上一页的数值变化
		model.addAttribute("bianPageShang",roles.pn/roles.ps-1);
		//下一页的数值变化
		model.addAttribute("bianPageXia",roles.pn/roles.ps+1);
		
		this.setModel(model);
		return "system/roles/list";
	}
}
