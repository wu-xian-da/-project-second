/**
  *project project-second-lib
  *@author changchun.wu
  *2017年8月11日上午9:05:18
  */
package com.jianfei.ps.entity.base;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public class BaseEntity implements Serializable{
	
	private Integer id;
}
