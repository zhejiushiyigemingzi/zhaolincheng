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
public class AjaxUserSearchService {

	  //得到mapper
	  @Autowired
	  UserinfoMapper userinfoMapper;
	  
	public List<UserinfoHobbyDto> ajaxUserSearch(String username,String sex,String major){

/*		  //得到session工厂
		  SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		  //得到sexxion=>不会进行自己提交     sqlSessionFactory.openSession()  当没有参数时，会默认传入false,不开启事务，所有操作会进行自动提交
		  SqlSession sqlSession = sqlSessionFactory.openSession(false);*/

        List<UserinfoHobby> list;

        //发出请求，执行数据库操作
	    list = userinfoMapper.findUserInfoANDHobby(username, sex, major);
	    System.out.println(list.size());

		
		List<UserinfoHobbyDto> UserinfoHobbyDto = new ArrayList<UserinfoHobbyDto>();

		List<String> userNameList = new ArrayList<String>();
		//System.out.println("用户信息如下");
		for(UserinfoHobby userinfoHobby:list){
			//当用户名不存在时,证明大的dto未创建
			if(!userNameList.contains(userinfoHobby.getUsername())){
				//通过有参构造方法创建UserinfoHobbyDto的对象
				UserinfoHobbyDto dto = new UserinfoHobbyDto(userinfoHobby.getUsername(),
															userinfoHobby.getPassword(),
															userinfoHobby.getSex(),
															userinfoHobby.getMajor(),
															userinfoHobby.getIntro());
			    //将创建的大的dto加到画面展示list里去
				UserinfoHobbyDto.add(dto);
				//将用户名加到用户名list里去
				userNameList.add(userinfoHobby.getUsername());
				//将爱好加入子dto中
				if(userinfoHobby.getHobby() != null){
			         dto.getHobbylist().add(new HobbyDto(userinfoHobby.getHobby()));
				}
			}else{
				//取得以往添加过得大DTO
	            for(UserinfoHobbyDto temp : UserinfoHobbyDto){
		           if(temp.getUsername().equals(userinfoHobby.getUsername())){
			         //将爱好加入子dto中
		        	 if(userinfoHobby.getHobby() != null){
			             temp.getHobbylist().add(new HobbyDto(userinfoHobby.getHobby()));
		        	 }
	               }
	            }
		    }
	    }
		
		for(UserinfoHobbyDto result : UserinfoHobbyDto){
			StringBuffer sb = new StringBuffer("");
			for(HobbyDto hobbyDto : result.getHobbylist()){
				if(hobbyDto.getHobby() != null){
				    sb.append(hobbyDto.getHobby() + ",");
				}
			}
			result.setHobbys(sb.toString().replace("0","足球").replace("1","篮球").replace("2","网球"));
			//当有爱好是，才执行操作截取逗号操作，否则报错
			if(!"".equals(result.getHobbys())){
				if(",".equals(sb.toString().substring(sb.toString().length()-1))){
					result.setHobbys(result.getHobbys().substring(0,result.getHobbys().length()-1));
				}else{
					result.setHobbys(result.getHobbys());
				}
			}
		 } 
		
		return UserinfoHobbyDto;
	}

	public UserinfoMapper getUserinfoMapper() {
		return userinfoMapper;
	}

	public void setUserinfoMapper(UserinfoMapper userinfoMapper) {
		this.userinfoMapper = userinfoMapper;
	}
	
}