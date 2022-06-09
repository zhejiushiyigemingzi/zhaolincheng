package co.jsp.service;

import java.util.ArrayList;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jsp.dto.UserRegisterDto;
import co.jsp.entity.Hobby;
import co.jsp.entity.Userinfo;
import co.jsp.mapper.HobbyMapper;
import co.jsp.mapper.UserinfoMapper;
import co.jsp.util.MyBatisUtil;

@Service
public class UserUpdateService {

	@Autowired
	UserinfoMapper userinfoMapper;
	@Autowired
	HobbyMapper hobbyMapper;
	
	public void userUpdata(UserRegisterDto dto){
		
	    //爱好
		String[] hobbyArray = dto.getHobby();
				
		List<Hobby> hobbyList = new ArrayList<Hobby>();
		
		for(int i=0;i<hobbyArray.length;i++){
			Hobby hobbyObject = new Hobby();
			hobbyObject.setUsername(dto.getUsername());
			hobbyObject.setHobby(hobbyArray[i]);
			hobbyList.add(hobbyObject);
		}



			userinfoMapper.deluserinfo(dto.getUsername());
			//用户信息表登录
			userinfoMapper.save(new Userinfo(dto.getUsername(),dto.getPassword(),dto.getSex(),dto.getMajor(),dto.getIntro()));
			//发出请求，执行数据库操作
			hobbyMapper.delHobby(dto.getUsername());
			for(Hobby hobby : hobbyList){
				hobbyMapper.save(hobby.getUsername(), hobby.getHobby());
			}
			System.out.println(hobbyList.size());

	}
}