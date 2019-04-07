package com.chinasofti.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinasofti.entity.Admin;
import com.chinasofti.service.AdminService;
// ...SSMDemo/admin... 跳转到这。
@RequestMapping("/admin") 
@Controller
public class AdminController {
	@Resource
	AdminService adminService;
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("/toRegist")
	public String toRegist(){
		return "regist";
	}
	
	@RequestMapping("/login")
	public String login(Admin admin,HttpServletRequest request){
		Admin login = adminService.login(admin);
		if(login != null){
			request.setAttribute("msg", "登录成功，欢迎您！"+admin.getName());
			return "main";
		}else{
			request.setAttribute("msg", "账号或密码错误！");
			return "login";
		}
	}
	
	@RequestMapping("/save")
	public String save(Admin admin){
		int result = adminService.insert(admin);
		if(result!=0){
			System.out.println("插入成功！");
		}
		else{
			System.out.println("插入失败！");
		}
		return "login";
	}
}
