/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月10日下午5:26:06
  */
package com.jianfei.ps.entity.system;


import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.jianfei.ps.entity.base.BaseEntity;

@Getter
@Setter
@ToString
public class Roles extends BaseEntity{

	private String rolename;
	
	private String counts;
	
	private List<Integer> menuId;
	
	private List<Integer> buttonId;
	
	private List<String> menubutton;
}
