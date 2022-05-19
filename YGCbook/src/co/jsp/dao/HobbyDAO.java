package co.jsp.dao;

import java.sql.SQLException;

import java.util.List;
import java.util.Vector;

import cn.key.dbManager.JdbcTemplate;
import cn.key.mapping.UserinfoMapping;
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
				 
			   template.updata(sql, values);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		return (row == 1);
	
	
	}
	
	
}

