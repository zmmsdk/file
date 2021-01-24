package com.louis.springboot.demo.dao;

import com.louis.springboot.demo.entity.OrderList;
import com.louis.springboot.demo.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

//import com.louis.springboot.demo.entity.SysUser;
//定义实体OrderList和主键  Integer
public interface OrderListDao extends JpaRepository<OrderList, Integer>, Serializable {
	
}