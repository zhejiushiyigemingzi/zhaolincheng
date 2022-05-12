package co.jsp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jsp.dao.UserinfoDAO;
import co.jsp.entity.Userinfo;

public class UserRegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//姓名
		String username = request.getParameter("username");
		//密码
		String password = request.getParameter("password");
		//性别
		String sex = request.getParameter("sex");
		//爱好TODO
		//String hobby = request.getParameter("hobby");
		//专业
		String major = request.getParameter("major");
		//简介
		String intro = request.getParameter("intro");
		

		UserinfoDAO dao = new UserinfoDAO(); 
		//用户信息表登录
		//boolean result = dao.save(new Userinfo(username,password,sex,major,intro));
		
		
		
		if(dao.save(new Userinfo(username,password,sex,major,intro))){
			
			System.out.println("用户信息表登陆成功");
			
		}else{
			System.out.println("用户信息表登陆失败");
		}
		
		
		
		
		
		
		
		//request.setAttribute("admin", admin);
		request.getRequestDispatcher("/background/sysAdmin/upSysAdminStates.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}