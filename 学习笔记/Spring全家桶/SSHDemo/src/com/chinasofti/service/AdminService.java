package com.chinasofti.service;

import com.chinasofti.bean.Admin;

public interface AdminService {
	public void save(Admin admin);
	public Admin login(String name,String pwd);
}
