package co.jsp.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import co.jsp.mapper.HobbyMapper;
import co.jsp.mapper.UserinfoMapper;
import co.jsp.util.MyBatisUtil;

public class UserDelAllService {
	
	public void userDelAll(String[] usernames){
		
		for(String username : usernames){
			
			//得到session工厂
			  SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
			  //得到sexxion=>不会进行自己提交     sqlSessionFactory.openSession()  当没有参数时，会默认传入false,不开启事务，所有操作会进行自动提交
			  SqlSession sqlSession = sqlSessionFactory.openSession(false);
			  
				  //得到mapper
				  UserinfoMapper userinfoMapper = sqlSession.getMapper(UserinfoMapper.class);
				  //得到mapper
			      HobbyMapper hobbyMapper = sqlSession.getMapper(HobbyMapper.class);


			try{
		        //发出请求，执行数据库操作
				userinfoMapper.deluserinfo(username);;
		        //发出请求，执行数据库操作
				hobbyMapper.delHobby(username);
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
}