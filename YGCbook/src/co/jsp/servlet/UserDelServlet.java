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

public class UserDelServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//姓名
		String username = request.getParameter("username");
		
		HobbyDAO hobbydao = new HobbyDAO();
		UserinfoDAO userinfodao = new UserinfoDAO(); 
		
		//用户信息表更新Flag
		boolean updateUserinfoFlag = true;
		//用户信息表伦理删除
		updateUserinfoFlag = userinfodao.deluserinfo(username);
		
		
		if(updateUserinfoFlag){
			System.out.println("用户信息表伦理删除成功");
		}else{
			System.out.println("用户信息表伦理删除失败");
		}
		
		//用户爱好表更新Flag
		boolean updatehobbyFlag = true;
		//用户爱好表伦理删除
		updatehobbyFlag = hobbydao.delHobby(username);
		
        if(updatehobbyFlag){
			System.out.println("用户爱好表伦理删除成功");
		}else{
			System.out.println("用户爱好表伦理删除失败");
		}
		request.getRequestDispatcher("/userSearch.jsp").forward(request, response);
	
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}