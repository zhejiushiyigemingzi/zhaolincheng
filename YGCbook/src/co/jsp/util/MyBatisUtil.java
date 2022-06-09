package co.jsp.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
	
    private static SqlSessionFactory sqlSessionFactory = null;
    public static SqlSessionFactory getSqlSessionFactory(){
    	 
    	InputStream inputStream = null;
    	if(null == sqlSessionFactory){
	    	String resource = "MyBatis_config.xml";
	        try {
	            inputStream = Resources.getResourceAsStream(resource);
	            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	            return sqlSessionFactory;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    	}
        return sqlSessionFactory;
    }

}
