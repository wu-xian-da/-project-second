/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月14日下午2:08:26
  */
package com.jianfei.ps.service.relation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jianfei.ps.dao.relation.RoleMenuDao;
import com.jianfei.ps.entity.relation.RoleMenu;
import com.jianfei.ps.service.base.CrudService;

@Service
public class RoleMenuService extends CrudService<RoleMenuDao, RoleMenu> {
	
	@Autowired
	private RoleMenuDao roleMenuDao;
	
	public List<RoleMenu> findRoleByRoleId(int id){
		return this.roleMenuDao.findRoleByRoleId(id);
	}
	
	public List<RoleMenu> findRolesRoleId(){
		return this.roleMenuDao.findRolesRoleId();
	}
	
	public List<RoleMenu> findBUTTON(int roleId){
		return this.roleMenuDao.findBUTTON(roleId);
	}
}
