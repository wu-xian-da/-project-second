/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月11日上午9:55:45
  */
package com.jianfei.ps.dao.system;

import com.jianfei.ps.dao.base.CrudDao;
import com.jianfei.ps.entity.system.Roles;

public interface RolesDao extends CrudDao<Roles>{

	/***
	 * 根据角色名称查询Roles
	 * @param roles
	 * @return
	 */
	public Roles findRolesByRolename(String rolename);
}
