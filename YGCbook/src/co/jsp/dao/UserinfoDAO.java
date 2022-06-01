package co.jsp.dao;

import java.sql.SQLException;



import java.util.List;
import java.util.Vector;

import cn.key.dbManager.JdbcTemplate;
import cn.key.mapping.UserinfoHobbyMapping;
import cn.key.mapping.UserinfoMapping;
import co.jsp.entity.Userinfo;
import co.jsp.entity.UserinfoHobby;

public class UserinfoDAO {
	private JdbcTemplate template = new JdbcTemplate();
	
	public boolean save(Userinfo userinfo) {
		int row = 0;
		String sql = "insert into userinfo(username,password,sex,major,intro,delFlg)"+" values(?,?,?,?,?,?)";
		
		Object[] values = new Object[]{
				userinfo.getUsername(), 
				userinfo.getPassword(), 
				userinfo.getSex(),
				userinfo.getMajor(), 
				userinfo.getIntro(),
				"0"};
		try {
			row = template.updata(sql, values);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		return (row == 1);
	}
	
	public boolean update(Userinfo userinfo) {
		int row = 0;
		String sql = "update userinfo set delFlg = '0' where username = ?";

		Object[] values = new Object[]{
				userinfo.getPassword(), 
				userinfo.getSex(),
				userinfo.getMajor(), 
				userinfo.getIntro(),
				userinfo.getUsername()};
		try {
			row = template.updata(sql, values);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		return (row == 1);
	}
	//伦理删除
	public boolean deluserinfo(String username) {
		String sql = "update userinfo set delFlg = '1' where username = ?";

		Object[] values = new Object[]{username};
		
		try {
			 template.updata(sql, values);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		return true;
	}
	
	public List<Userinfo> findUserInfo(String username,String sex,String major) {
		String sql = "select * from userinfo where delFlg = '0' and ";
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
		List<Userinfo> list = new Vector<Userinfo>();
		try {
			list = template.selete(sql, new UserinfoHobbyMapping());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//公有的  返回值为UserinfoHobby的集合  方法名为findUserInfoANDHobby 参数为STRING类型的 username,sex,major
	public List<UserinfoHobby> findUserInfoANDHobby(String username,String sex,String major) {
		String sql = "select userinfo.username,password,sex,major,intro,hobby from userinfo"
                      + " left join hobby"
                      + " on userinfo.username = hobby.username where";
		//用户名
		if(username != null && !"".equals(username)){
			
			sql = sql + " userinfo.username ='" + username + "' and";
		}
		//性别
		sql = sql + " sex = '" + sex + "'";
		
		//专业
		if(major != null &&!"".equals(major)){
			
			sql = sql + " and major ='" + major + "'";
		}	
		sql = sql + " and userinfo.delFlg = '0' and hobby.delFlg = '0'";
		List<UserinfoHobby> list = new Vector<UserinfoHobby>();
		try {
			list = template.selete(sql, new UserinfoHobbyMapping());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Userinfo> checkUserName(String username) {
		String sql = "select * from userinfo where delFlg = '0' and " + " userinfo.username ='" + username + "'";
		
		List<Userinfo> list = new Vector<Userinfo>();
		try {
			list = template.selete(sql, new UserinfoMapping());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<UserinfoHobby> findUserInfoANDHobby(String username) {
		String sql = "select userinfo.username,password,sex,major,intro,hobby from userinfo"
                      + " left join hobby"
                      + " on userinfo.username = hobby.username where userinfo.username = '";
		//用户名
		sql = sql + username + "' and userinfo.delFlg = '0' and hobby.delFlg = '0'";
		
		List<UserinfoHobby> list = new Vector<UserinfoHobby>();
		try {
			list = template.selete(sql, new UserinfoHobbyMapping());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
}

