package com.chinasofti.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;




import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;






import com.chinasofti.bean.Admin;

@Repository
public class AdminDaoImpl implements AdminDao {
	@Resource
	private HibernateTemplate template;
	@Override
	public void save(Admin admin) {
		Serializable id = template.save(admin);
		if(id != null){
			System.out.println("数据插入成功！");
		}
	}

	@Override
	public Admin login(String name, String pwd) {
		// TODO Auto-generated method stub
		Admin admin = null;
		String strHql = "from Admin where name=? and pwd=?"; 
		@SuppressWarnings("unchecked")
		List<Admin> listAdmins = (List<Admin>)template.find(strHql, new Object[]{name,pwd});
		if(listAdmins.size()>0){
			admin = listAdmins.get(0);
		}
		return admin;
	}

}
