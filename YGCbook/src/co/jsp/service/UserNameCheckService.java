package co.jsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.jsp.entity.Userinfo;
import co.jsp.mapper.UserinfoMapper;

@Service
public class UserNameCheckService {
	
	
	//得到mapper
	@Autowired
	UserinfoMapper userinfoMapper;
	
	public Userinfo userNameCheck(String username){

		Userinfo userinfo;

	    //发出请求，执行数据库操作
	    userinfo = userinfoMapper.checkUserName(username);

	    System.out.println(123);
        return userinfo;
	}
}