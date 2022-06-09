package co.jsp.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.key.dao.BookInfoDAO;

public class Test {

	public static void main(String[] args){
		 //Spring的调用方式
		 ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		 BookInfoDAO helloWorld = (BookInfoDAO) classPathXmlApplicationContext.getBean("bookInfoDAO");
		 
		 System.out.println(helloWorld.getClass());
         System.out.println(helloWorld.getName());
		 System.out.println(helloWorld.getTemplate().getClass());
    }
}
 