/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月12日下午1:07:41
  */
package com.jianfei.ps.dao.relation;

import java.util.List;

import com.jianfei.ps.dao.base.CrudDao;
import com.jianfei.ps.entity.relation.UserRole;

public interface UserRoleDao extends CrudDao<UserRole> {
	
	/***
	 * 查角色ID
	 * @return
	 */
	public List<UserRole> findAllRoleId();
	
	/***
	 * 查询用户ID
	 * @return
	 */
	public List<UserRole> findAllUserId();
	
}
