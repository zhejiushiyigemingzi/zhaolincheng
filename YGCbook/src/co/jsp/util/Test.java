package co.jsp.util;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import co.jsp.entity.UserinfoHobby;
import co.jsp.mapper.UserinfoMapper;
 
public class Test {

	public static void main(String[] args){
		  //得到session工厂
		  SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		  //得到sexxion=>不会进行自己提交     sqlSessionFactory.openSession()  当没有参数时，会默认传入false,不开启事务，所有操作会进行自动提交
		  SqlSession sqlSession = sqlSessionFactory.openSession(false);
		  try{
			  //得到mapper
			  UserinfoMapper userinfoMapper = sqlSession.getMapper(UserinfoMapper.class);
	          //发出请求，执行数据库操作
			  List<UserinfoHobby> list = userinfoMapper.findUserInfoANDHobbyByUsername("ttt");
			  //对数据进行提交
			  sqlSession.commit();
			  System.out.println(list.size());
		  }catch(Exception exception){
			  sqlSession.rollback();
			  System.out.println("有个小错误！！！");
			  throw exception;
		  }finally{
			  sqlSession.close();
		  }
    }
}
