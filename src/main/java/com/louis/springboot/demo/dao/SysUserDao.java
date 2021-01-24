package com.louis.springboot.demo.dao;

import java.io.Serializable;

import com.louis.springboot.demo.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

//import com.louis.springboot.demo.entity.SysUser;

public interface SysUserDao extends JpaRepository<SysUser, Long>, Serializable {
	
}