package co.jsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.jsp.mapper.HobbyMapper;
import co.jsp.mapper.UserinfoMapper;

@Service
public class UserDelService {
	
	
	//得到mapper
	@Autowired
	HobbyMapper hobbyMapper;
	//得到mapper
	@Autowired
	UserinfoMapper userinfoMapper;
	
	public boolean userDel(String username){

        userinfoMapper.deluserinfo(username);
	    //发出请求，执行数据库操作
	    hobbyMapper.delHobby(username);

	    System.out.println(456);
		return true;
	}
}