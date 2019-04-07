package com.chinasofti.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinasofti.bean.Admin;
import com.chinasofti.dao.AdminDao;

@Service
public class AdminServiceImpl implements AdminService {
	@Resource
	AdminDao adminDao;
	@Override
	public void save(Admin admin) {
		// TODO Auto-generated method stub
		adminDao.save(admin);
	}

	@Override
	public Admin login(String name, String pwd) {
		// TODO Auto-generated method stub
		return adminDao.login(name, pwd);
	}

}
