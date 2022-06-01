package cn.key.mapping;

import java.sql.ResultSet;

import java.sql.SQLException;

import cn.key.dbManager.IResultSetMapping; 
import co.jsp.entity.UserinfoHobby;

public class UserinfoHobbyMapping implements IResultSetMapping{

	@Override
	public UserinfoHobby mapping(ResultSet rs) throws SQLException {
		int i = 1;
		UserinfoHobby userinfoHobby = new UserinfoHobby(rs.getString(i++), 
														rs.getString(i++),
														rs.getString(i++), 
														rs.getString(i++), 
														rs.getString(i++), 	
					                                    rs.getString(i++));
		return userinfoHobby;
	}
}
 