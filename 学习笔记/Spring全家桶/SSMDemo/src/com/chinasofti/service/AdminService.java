package com.chinasofti.service;

import com.chinasofti.entity.Admin;

public interface AdminService {
	public int insert(Admin admin);
	public Admin findByKey(Integer id);
	public Admin login(Admin admin);
}
