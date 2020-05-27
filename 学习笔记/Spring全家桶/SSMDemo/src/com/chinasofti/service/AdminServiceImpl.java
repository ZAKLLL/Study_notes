package com.chinasofti.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinasofti.dao.AdminMapper;
import com.chinasofti.entity.Admin;
@Service
public class AdminServiceImpl implements AdminService {
	@Resource
	AdminMapper adminMapper;
	
	@Override
	public int insert(Admin admin) {
		return adminMapper.insert(admin);
	}

	@Override
	public Admin findByKey(Integer id) {
		// TODO Auto-generated method stub
		return adminMapper.findByKey(id);
	}

	@Override
	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		return adminMapper.login(admin);
	}

}
