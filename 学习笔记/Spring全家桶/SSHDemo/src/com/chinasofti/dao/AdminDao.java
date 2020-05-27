package com.chinasofti.dao;

import com.chinasofti.bean.Admin;

public interface AdminDao {
	public void save(Admin admin);
	public Admin login(String name,String pwd);
}
