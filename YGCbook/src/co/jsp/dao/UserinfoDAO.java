package co.jsp.dao;

import java.sql.SQLException;


import java.util.List;
import java.util.Vector;

import cn.key.dbManager.JdbcTemplate;
import cn.key.mapping.UserinfoMapping;
import co.jsp.entity.Userinfo;
import co.jsp.entity.UserinfoHobby;

public class UserinfoDAO {
	private JdbcTemplate template = new JdbcTemplate();
	
	public boolean save(UserinfoHobby userinfoHobby) {
		int row = 0;
		String sql = "insert into userinfo(username,password,sex,major,intro)"+" values(?,?,?,?,?)";
		
		Object[] values = new Object[]{
				 userinfoHobby.getUsername(), 
				 userinfoHobby.getPassword(), 
				 userinfoHobby.getSex(),
				 userinfoHobby.getMajor(), 
				 userinfoHobby.getIntro()};
		try {
			row = template.updata(sql, values);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		return (row == 1);
	}
	public List<Userinfo> findUserInfo(String username,String sex,String major) {
		String sql = "select * from userinfo where";
		//用户名
		if(!"".equals(username)){
			
			sql = sql + " username = '" + username + "' and";
		}
		//性别
		sql = sql + " sex = '" + sex + "'";
		
		//专业
		if(!"".equals(major)){
			
			sql = sql + " and major = '" + major + "'";
		}	
		List<Userinfo> list = new Vector<Userinfo>();
		try {
			list = template.selete(sql, new UserinfoMapping());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public List<UserinfoHobby> findUserInfoANDHobby(String username,String sex,String major) {
		String sql = "select userinfo.username,password,sex,major,intro,hobby from userinfo"
                      + " left join hobby"
                      + " on userinfo.username = hobby.username where";
		//用户名
		if(!"".equals(username)){
			
			sql = sql + " userinfo.username = '" + username + "' and";
		}
		//性别
		sql = sql + " sex = '" + sex + "'";
		
		//专业
		if(!"".equals(major)){
			
			sql = sql + " and major = '" + major + "'";
		}	
		List<UserinfoHobby> list = new Vector<UserinfoHobby>();
		try {
			list = template.selete(sql, new UserinfoMapping());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	

}

