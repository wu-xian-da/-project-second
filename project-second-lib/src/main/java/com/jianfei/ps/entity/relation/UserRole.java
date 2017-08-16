/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月12日下午1:05:37
  */
package com.jianfei.ps.entity.relation;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.jianfei.ps.entity.base.BaseEntity;

@Setter
@Getter
@ToString
public class UserRole extends BaseEntity{
	
	private int userId;
	
	private int roleId;
	
	private List<String> roles;
	
	private List<String> users;
	
}
