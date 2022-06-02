package co.jsp.service;

import java.util.ArrayList;
import java.util.List;
import co.jsp.dao.HobbyDAO;
import co.jsp.dao.UserinfoDAO;
import co.jsp.dto.UserRegisterDto;
import co.jsp.entity.Hobby;
import co.jsp.entity.Userinfo;

public class UserRegisterService {
	
	public boolean userRegister(UserRegisterDto dto) {
		 
		HobbyDAO hobbydao = new HobbyDAO();
		UserinfoDAO userinfodao = new UserinfoDAO(); 
		 
		//爱好
		String[] hobbyArray = dto.getHobby();
		
		List hobbyList = new ArrayList();
        for(int i=0;i<hobbyArray.length;i++){
			Hobby hobbyObject = new Hobby();
			hobbyObject.setUsername(dto.getUsername());
			hobbyObject.setHobby(hobbyArray[i]);
			hobbyList.add(hobbyObject);
		}
		 
		boolean sucessFlag = true;
		
		if(userinfodao.save(new Userinfo(dto.getUsername(),dto.getPassword(),dto.getSex(),dto.getMajor(),dto.getIntro()))){
			
			System.out.println("用户信息表登陆成功");
			
		}else{
			System.out.println("用户信息表登陆失败");
			sucessFlag = false;
			
		}
		System.out.println(sucessFlag);
		//用户爱好表登录
        if(hobbydao.save(hobbyList)){
			
			System.out.println("用户爱好表登陆成功");
			
		}else{
			System.out.println("用户爱好表登陆失败");
			sucessFlag = false;
			
		}
        return sucessFlag;
	 }
	

	

}