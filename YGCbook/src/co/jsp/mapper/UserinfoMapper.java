package co.jsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.jsp.entity.Userinfo;
import co.jsp.entity.UserinfoHobby;

public interface UserinfoMapper {

	public void save(Userinfo userinfo);
	
	public void deluserinfo(@Param("username") String username);
	
	public List<UserinfoHobby> findUserInfoANDHobby(
			@Param("username") String username,
			@Param("sex")String sex,
			@Param("major")String major);
	
	public Userinfo checkUserName(@Param("username") String username);
		
	public List<UserinfoHobby> findUserInfoANDHobbyByUsername(@Param("username") String username);
}
