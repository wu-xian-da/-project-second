/**
  *project project-frist
  *@author changchun.wu
  *2017年8月4日上午10:20:54
  */
package com.jianfei.ps.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jianfei.ps.dao.base.CrudDao;
import com.jianfei.ps.entity.base.BaseEntity;
import com.jianfei.ps.entity.common.Page;


public abstract class CrudService<D extends CrudDao<T>,T extends BaseEntity> extends BaseService {

	@Autowired
	protected D dao;
	
	public int insert(T entity){
		if (entity.getId() != null) {
			return this.dao.update(entity);
		}
		return this.dao.insert(entity);
	}
	
	public int update(T entity){
		return this.dao.update(entity);
	}
	
	public int delete(int id){
		return this.dao.delete(id);
	}
	
	public T findById(int id){
		return this.dao.findById(id);
	}
	
	public List<T> findAll(){
		return this.dao.findAll();
	}
	
	public List<T> findCondition(T entity){
		return this.dao.findCondition(entity);
	}
}
