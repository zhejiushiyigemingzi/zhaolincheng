package co.jsp.hhh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jsp.entity.Hobby;

public class UserRegisterServlet {
    
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		//姓名
		String username = request.getParameter("username");
		//密码
		String password = request.getParameter("password");
		//性别
		String sex = request.getParameter("sex");
		//专业
		String major = request.getParameter("major");
		//简介
		String intro = request.getParameter("intro");
		//爱好
		String[] hobbyArray = request.getParameterValues("hoby");
		
		List hobbylist= new ArrayList();
		
		
		for(int i=0;i<hobbyArray.length;i++){
			Hobby hobbyObj = new Hobby();
			hobbyObj.setUsername(username);
			hobbyObj.setHobby(hobbyArray[i]);
			System.out.println(i);
		}
		
		
		
		
		
		
		
		
		
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			this.doGet(request, response);
	
	}
}