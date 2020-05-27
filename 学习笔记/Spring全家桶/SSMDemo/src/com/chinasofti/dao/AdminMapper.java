package com.chinasofti.dao;

import com.chinasofti.entity.Admin;

@MybatisAnnotation
public interface AdminMapper {
	public int insert(Admin admin);
	public Admin findByKey(Integer id);
	public Admin login(Admin admin);
}
