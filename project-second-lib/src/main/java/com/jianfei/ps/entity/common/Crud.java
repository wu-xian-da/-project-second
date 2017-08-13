/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月11日上午9:44:11
  */
package com.jianfei.ps.entity.common;

public enum Crud {
	
	INSERT("新增"),UPDATE("编辑"),DELETE("删除"),SELECT("查询");
	
	private String name;
	
	private Crud(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
