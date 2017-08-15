/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月14日下午2:07:48
  */
package com.jianfei.ps.dao.relation;

import java.util.List;

import com.jianfei.ps.dao.base.CrudDao;
import com.jianfei.ps.entity.relation.RoleMenu;

public interface RoleMenuDao extends CrudDao<RoleMenu> {

	public List<RoleMenu> findRoleByRoleId(int id);
	
	public List<RoleMenu> findRolesRoleId();
}
