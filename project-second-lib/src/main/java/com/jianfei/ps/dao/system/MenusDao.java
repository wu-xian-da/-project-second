/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月11日上午9:55:58
  */
package com.jianfei.ps.dao.system;

import java.util.List;

import com.jianfei.ps.dao.base.CrudDao;
import com.jianfei.ps.entity.system.Menus;

public interface MenusDao extends CrudDao<Menus>{

	/**
	 * 根据类型菜单查询权限
	 * @return
	 */
	public List<Menus> findMenusByType();
	
	/**
	 * 根据类型按钮查询权限
	 * @return
	 */
	public List<Menus> findButtonByType();
	
	public Menus findMenusByPermission(String permission);
}
