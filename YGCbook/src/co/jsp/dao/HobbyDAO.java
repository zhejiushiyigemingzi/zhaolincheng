package co.jsp.dao;

import java.sql.SQLException;

import java.util.List;
import java.util.Vector;

import cn.key.dbManager.JdbcTemplate;
import cn.key.mapping.UserinfoHobbyMapping;
import co.jsp.entity.Hobby;
import co.jsp.entity.UserinfoHobby;

public class HobbyDAO {
	private JdbcTemplate template = new JdbcTemplate();
	
	public boolean save(List list) {
		int row = 0;
		String sql = "insert into hobby(username,hobby)"+" values(?,?)";
		
				
		try {
			for(Object object : list){
				Hobby hobbyObject = (Hobby)object;
				
				Object[] values = null;
				values = new Object[]{
						hobbyObject.getUsername(), 
						hobbyObject.getHobby()};
				 
			  row = template.updata(sql, values);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		return (row == 1);
	}
	
	//伦理删除
	public boolean delHobby(String username) {
		String sql = "update hobby set delFlg = '1' where username = ?";

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
}

