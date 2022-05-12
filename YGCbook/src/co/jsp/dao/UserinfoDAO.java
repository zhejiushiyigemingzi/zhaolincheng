package co.jsp.dao;

import java.sql.SQLException;

import cn.key.dbManager.JdbcTemplate;
import co.jsp.entity.Userinfo;

public class UserinfoDAO {
	private JdbcTemplate template = new JdbcTemplate();
	
	public boolean save(Userinfo userinfo) {
		int row = 0;
		String sql = "insert into userinfo(username,password,sex,major,intro)"+" values(?,?,?,?,?)";
		
		Object[] values = new Object[]{
				 userinfo.getUsername(), 
				 userinfo.getPassword(), 
				 userinfo.getSex(), 
				 userinfo.getMajor(), 
				 userinfo.getIntro()};
		try {
			row = template.updata(sql, values);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		return (row == 1);
	}
	
	
	

}

