/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月11日上午9:59:52
  */
package com.jianfei.ps.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jianfei.ps.dao.system.RolesDao;
import com.jianfei.ps.entity.system.Roles;
import com.jianfei.ps.service.base.CrudService;

@Service
public class RolesService extends CrudService<RolesDao, Roles> {

	@Autowired
	private RolesDao rolesDao;
	
	public Roles findRolesByRolename(String rolename){
		return this.rolesDao.findRolesByRolename(rolename);
	}
}
