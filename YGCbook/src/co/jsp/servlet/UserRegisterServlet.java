package co.jsp.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jsp.dao.HobbyDAO;
import co.jsp.dao.UserinfoDAO;
import co.jsp.entity.Hobby;
import co.jsp.entity.UserinfoHobby;

public class UserRegisterServlet extends HttpServlet {

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
		//String hobby = request.getParameter("hobby");
		
		//爱好
		String[] hobbyArray = request.getParameterValues("hobby");
				
		
		List hobbyList = new ArrayList();
		
		
		
        for(int i=0;i<hobbyArray.length;i++){
			
			Hobby hobbyObject = new Hobby();
			hobbyObject.setUsername(username);
			hobbyObject.setHobby(hobbyArray[i]);
			hobbyList.add(hobbyObject);
		}
		
		
		HobbyDAO hobbydao = new HobbyDAO();
		UserinfoDAO userinfodao = new UserinfoDAO(); 
		
		boolean sucessFlag = true;
		//用户信息表登录
		//boolean result = dao.save(new Userinfo(username,password,sex,major,intro));
		
		if(userinfodao.save(new UserinfoHobby(username,password,sex,major,intro, intro))){
			
			System.out.println("用户信息表登陆成功");
			
		}else{
			System.out.println("用户信息表登陆失败");
			sucessFlag = false;
		}
		//用户爱好表登录
        if(hobbydao.save(hobbyList)){
			
			System.out.println("用户爱好表登陆成功");
			
		}else{
			System.out.println("用户爱好表登陆失败");
			sucessFlag = false;
		}
		
		//request.setAttribute("admin", admin);
        if(sucessFlag){
		    request.getRequestDispatcher("/userRegSucess.jsp").forward(request, response);
	
        }else{
        	request.getRequestDispatcher("/userRegErr.jsp").forward(request, response);
        }
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}