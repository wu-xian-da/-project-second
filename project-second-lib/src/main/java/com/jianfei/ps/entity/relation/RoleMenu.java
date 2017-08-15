/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月14日下午2:05:31
  */
package com.jianfei.ps.entity.relation;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.jianfei.ps.entity.base.BaseEntity;

@Setter
@Getter
@ToString
public class RoleMenu extends BaseEntity{

	private int roleId;
	
	private int menuId;
	
	private int buttonId;
	
	private List<String> roles;
	
	private List<String> menus;
	
	private List<String> buttons;
}
