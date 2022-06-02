package co.jsp.service;

import java.util.ArrayList;
import java.util.List;
import co.jsp.dao.HobbyDAO;
import co.jsp.dao.UserinfoDAO;
import co.jsp.dto.UserRegisterDto;
import co.jsp.entity.Hobby;
import co.jsp.entity.Userinfo;

public class UserUpdateService {
	
	public boolean userUpdata(UserRegisterDto dto){
		
		
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

		//用户信息表更新Flag
		boolean updateUserinfoFlag = true;
		//用户信息表伦理删除
		updateUserinfoFlag = userinfodao.deluserinfo(dto.getUsername());
		//用户信息表登录
		updateUserinfoFlag = userinfodao.save(new Userinfo(dto.getUsername(),dto.getPassword(),dto.getSex(),dto.getMajor(),dto.getIntro()));
		
		if(updateUserinfoFlag){
			System.out.println("用户信息表更新成功");
		}else{
			System.out.println("用户信息表更新失败");
		}
		
		//用户爱好表更新Flag
		boolean updatehobbyFlag = true;
		//用户爱好表伦理删除
		updatehobbyFlag = hobbydao.delHobby(dto.getUsername());
		//用户爱好表登录
		updatehobbyFlag = hobbydao.save(hobbyList);
        if(updatehobbyFlag){
			System.out.println("用户爱好表更新成功");
		}else{
			System.out.println("用户爱好表更新失败");
		}
       	return updateUserinfoFlag;
	}


	
}