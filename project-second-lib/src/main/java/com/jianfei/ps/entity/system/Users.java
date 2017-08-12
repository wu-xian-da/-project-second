/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月10日下午5:25:57
  */
package com.jianfei.ps.entity.system;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.jianfei.ps.entity.base.BaseEntity;
import com.jianfei.ps.entity.common.Gender;

@Getter
@Setter
@ToString
public class Users extends BaseEntity{
	
	private String username;//用户名
	
	private String password;//密码
	
	private String nickname;//昵称
	
	private Gender gender;//性别
	
	private int age;//年龄
	
	private Date createTime;//创建时间
	
	private Date loginTime;//最近登录时间
	
	private String ip;//登录IP
	
    private String beginCreateTime;
    
    private String endCreateTime;
    
    private List<Integer> roleId;
    
}
