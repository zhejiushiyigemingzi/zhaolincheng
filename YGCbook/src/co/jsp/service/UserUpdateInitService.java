package co.jsp.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.jsp.dto.HobbyDto;
import co.jsp.dto.UserinfoHobbyDto;
import co.jsp.entity.UserinfoHobby;
import co.jsp.mapper.UserinfoMapper;

@Service
public class UserUpdateInitService {
	
	//得到mapper
	@Autowired
	UserinfoMapper userinfoMapper;
	
	public List<UserinfoHobbyDto> userUpdateInit(String username){

		List<UserinfoHobby> list;
	    //发出请求，执行数据库操作
	    list = userinfoMapper.findUserInfoANDHobbyByUsername(username);

	    System.out.println(list.size());
	    
		List<UserinfoHobbyDto> userinfoHobbyDto = new ArrayList<UserinfoHobbyDto>();

		List<String> userNameList = new ArrayList<String>();
		System.out.println("用户信息如下");
		for(UserinfoHobby userinfoHobby:list){
			//当用户名不存在时,证明大的dto未创建
			if(!userNameList.contains(userinfoHobby.getUsername())){
				UserinfoHobbyDto dto = new UserinfoHobbyDto(userinfoHobby.getUsername(),
															userinfoHobby.getPassword(),
															userinfoHobby.getSex(),
															userinfoHobby.getMajor(),
															userinfoHobby.getIntro());
			    //将创建的大的dto加到画面展示list里去
				userinfoHobbyDto.add(dto);
				//将用户名加到用户名list里去
				userNameList.add(userinfoHobby.getUsername());
				//将爱好加入子dto中
				if(userinfoHobby.getHobby() != null){
			         dto.getHobbylist().add(new HobbyDto(userinfoHobby.getHobby()));
				}
			}else{
				//取得以往添加过得大DTO
	            for(UserinfoHobbyDto temp : userinfoHobbyDto){
		           if(temp.getUsername().equals(userinfoHobby.getUsername())){
			         //将爱好加入子dto中
		        	 if(userinfoHobby.getHobby() != null){
			             temp.getHobbylist().add(new HobbyDto(userinfoHobby.getHobby()));
		        	 }
	               }
	            }
		    }
	    }
		
        for(UserinfoHobbyDto result : userinfoHobbyDto){
			StringBuffer sb = new StringBuffer("");
			for(HobbyDto hobbyDto : result.getHobbylist()){
				if(hobbyDto.getHobby() != null){
				    sb.append(hobbyDto.getHobby());
				}
			}
			result.setHobbys(sb.toString());
		}
		
		return userinfoHobbyDto;
        	
	}
}