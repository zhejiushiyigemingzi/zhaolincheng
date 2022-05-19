package co.jsp.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jsp.dao.UserinfoDAO;
import co.jsp.dto.HobbyDto;
import co.jsp.dto.UserinfoHobbyDto;
import co.jsp.entity.UserinfoHobby;

public class UserSearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//姓名
		String username = request.getParameter("username");
		//性别
		String sex = request.getParameter("sex");
		//爱好
        //String[] hobbyArray = request.getParameterValues("hobby");		
		//专业
		String major = request.getParameter("major");
				
		
		
		
		
		/*HobbyDAO hobbydao = new HobbyDAO();
		List<Hobby> list1 = hobbydao.findByName(username, hobbyArray);*/
		
		UserinfoDAO userinfodao = new UserinfoDAO(); 
		List<UserinfoHobby> list= userinfodao.findUserInfoANDHobby(username, sex, major);
		
		List<UserinfoHobbyDto> UserinfoHobbyDto = new ArrayList<UserinfoHobbyDto>();
		Map<String, String> userNamemap = new HashMap<String, String>();
		//标识dto是否被创建
		List<String> userNameList = new ArrayList<String>();
		
		System.out.println("用户信息如下");
		for(UserinfoHobby userinfoHobby:list){
			//当用户名不存在时,证明大的dto未创建
			if(!userNameList.contains(userinfoHobby.getUsername())){
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
			    dto.getHobbylist().add(new HobbyDto(userinfoHobby.getHobby()));
			}else{
				//取得以往添加过得大DTO
	            for(UserinfoHobbyDto temp : UserinfoHobbyDto){
		           if(temp.getUsername().equals(userinfoHobby.getUsername())){
			         //将爱好加入子dto中
			         temp.getHobbylist().add(new HobbyDto(userinfoHobby.getHobby()));
	               }
	            }
		  }
	 }
		for(UserinfoHobbyDto result : UserinfoHobbyDto){
		   	System.out.println("----------------");
			System.out.println("姓名:" + result.getUsername());
			System.out.println("密码:" + result.getPassword());
			System.out.println("性别:" + result.getSex());
			System.out.println("专业:" + result.getMajor());
			System.out.println("简介:" + result.getIntro());
			System.out.print("爱好:");
			StringBuffer sb = new StringBuffer("");
			for(HobbyDto hobbyDto : result.getHobbylist()){
				sb.append(hobbyDto.getHobby() + ",");
			}
			if(",".equals(sb.toString().substring(sb.toString().length()-1))){
				System.out.println(sb.toString().substring(0,sb.toString().length()-1));
			}else{
				System.out.println(sb.toString());
			}
			 System.out.println("");
		 }
		//System.out.println("取得成功");
		System.out.println("向画面进行展示");
		//boolean sucessFlag = true;
		//用户信息表登录
		//boolean result = dao.save(new Userinfo(username,password,sex,major,intro));
		
		/*if(userinfodao.save(new Userinfo(username,password,sex,major,intro))){
			
			System.out.println("用户信息表登陆成功");
			
		}else{
			System.out.println("用户信息表登陆失败");
			sucessFlag = false;
		}*/
		
		
		//request.setAttribute("admin", admin);
        if(true){
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