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
import co.jsp.entity.Userinfo;

public class UserUpdateServlet extends HttpServlet {

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
		
		
		//用户信息表更新Flag
		boolean updateUserinfoFlag = true;
		//用户信息表伦理删除
		updateUserinfoFlag = userinfodao.deluserinfo(username);
		//用户信息表登录
		updateUserinfoFlag = userinfodao.save(new Userinfo(username, password, sex, major, intro));
		
		if(updateUserinfoFlag){
			System.out.println("用户信息表更新成功");
		}else{
			System.out.println("用户信息表更新失败");
		}
		
		//用户爱好表更新Flag
		boolean updatehobbyFlag = true;
		//用户爱好表伦理删除
		updatehobbyFlag = hobbydao.delHobby(username);
		//用户爱好表登录
		updatehobbyFlag = hobbydao.save(hobbyList);
        if(updatehobbyFlag){
			System.out.println("用户爱好表更新成功");
		}else{
			System.out.println("用户爱好表更新失败");
		}
		request.getRequestDispatcher("/userUpdateSucess.jsp").forward(request, response);
	
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}