package co.jsp.service;

import java.util.ArrayList;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import co.jsp.dto.UserRegisterDto;
import co.jsp.entity.Hobby;
import co.jsp.entity.Userinfo;
import co.jsp.mapper.HobbyMapper;
import co.jsp.mapper.UserinfoMapper;
import co.jsp.util.MyBatisUtil;

public class UserUpdateService {
	
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

		
		//得到session工厂
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		//得到sexxion=>不会进行自己提交     sqlSessionFactory.openSession()  当没有参数时，会默认传入false,不开启事务，所有操作会进行自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(false);
		UserinfoMapper userinfoMapper = sqlSession.getMapper(UserinfoMapper.class);
		
		HobbyMapper hobbyMapper = sqlSession.getMapper(HobbyMapper.class);
		try{

			userinfoMapper.deluserinfo(dto.getUsername());
			//用户信息表登录
			userinfoMapper.save(new Userinfo(dto.getUsername(),dto.getPassword(),dto.getSex(),dto.getMajor(),dto.getIntro()));
			
			
	        //发出请求，执行数据库操作
			hobbyMapper.delHobby(dto.getUsername());
			for(Hobby hobby : hobbyList){
				hobbyMapper.save(hobby.getUsername(), hobby.getHobby());
			}
			//对数据进行提交
			sqlSession.commit();
		}catch(Exception exception){
			//回到最开始的状态
			sqlSession.rollback();
			System.out.println("有个小错误！！！");
		}finally{
			  sqlSession.close();
		}
	}
}