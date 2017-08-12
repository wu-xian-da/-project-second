/**
  *project project-frist
  *@author changchun.wu
  *2017年8月4日上午10:18:50
  */
package com.jianfei.ps.entity.common;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page<T> {
	
	private int[] defaultPageSizeSelect = {10,20,30,40,50};//显示当前页面的数据条数
	
	private int pageNo;//当前页码
	
	private int pageSize;//默认页展示记录数
	
	private long totalRecord;//总记录数
	
	private int viewPageNumber = 8;//显示页面长度
	
	private List<T> data = new ArrayList<T>();
	
	private String urlParams;//get查询参数封装
	
	private T entity; //查询对象


}
