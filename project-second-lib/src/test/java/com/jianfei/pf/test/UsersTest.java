/**
  *project project-frist
  *@author changchun.wu
  *2017年7月31日下午2:57:33
  */
package com.jianfei.pf.test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.jianfei.ps.entity.common.Gender;
import com.jianfei.ps.entity.system.Roles;
import com.jianfei.ps.entity.system.Users;
import com.jianfei.ps.service.system.RolesService;
import com.jianfei.ps.service.system.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-context.xml","classpath:spring/spring-mybatis.xml"})
public class UsersTest {
	private static final Logger logger = Logger.getLogger(UsersTest.class);
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Test
	public void InsertUsers(){
		Users users = new Users();
		users.setUsername("水电费地方");
		users.setPassword("123");
		users.setNickname("水电费水电费是服");
		users.setGender(Gender.NAN);
		users.setAge(12);
		users.setCreateTime(new Date());
		users.setLoginTime(new Date());
		users.setIp("0.0.0.0.0.0.1");
		int result = usersService.insert(users);
		System.out.println(result);
	}
	
	@Test
	public void findUsersById(){
		Users user = usersService.findById(1);
		logger.info(JSON.toJSON(user));
		System.out.println(user);
	}
	
	@Test
	public void findAllUsers(){
		List<Users> userList = usersService.findAll();
		System.out.println(userList);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testDataSource() throws SQLException{
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml","classpath:spring/spring-mybatis.xml");
		DruidDataSource dataSources = (DruidDataSource) context.getBean("dataSource");
		System.out.println(dataSources.getConnection());
	}

	@Test
	public void findAllRoles(){
		List<Roles> roleList = rolesService.findAll();
		System.out.println(roleList);
	}
	
	@Test
	public void insertRoles(){
		Roles role = new Roles();
		rolesService.insert(role);
	}
	
	@Test
	public void updateRoles(){
		Roles roles = new Roles();
		roles.setId(27);
		roles.setRolename("seirwoir");
		roles.setCounts("sjdfoiwjier");
		rolesService.update(roles);
	}
	
}
