package co.jsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.jsp.mapper.HobbyMapper;
import co.jsp.mapper.UserinfoMapper;

@Service
public class UserDelAllService {
	
	
	//得到mapper
	@Autowired
	UserinfoMapper userinfoMapper;
	//得到mapper
	@Autowired
    HobbyMapper hobbyMapper;
	
	public void userDelAll(String[] usernames){
		
		for(String username : usernames){

        //发出请求，执行数据库操作
		userinfoMapper.deluserinfo(username);;
        //发出请求，执行数据库操作
		hobbyMapper.delHobby(username);

		System.out.println(789);
		}
	}
}