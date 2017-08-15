/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月10日下午5:27:27
  */
package com.jianfei.ps.entity.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.jianfei.ps.entity.base.BaseEntity;
import com.jianfei.ps.entity.common.MBType;

@Getter
@Setter
@ToString
public class Menus extends BaseEntity{
	
	private String name;
	
	private String href;
	
	private String permission;
	
	private MBType type;
	
	private int sort;
	
}
