package co.jsp.service;

import co.jsp.dao.HobbyDAO;
import co.jsp.dao.UserinfoDAO;

public class UserDelAllService {
	
	public void userDelAll(String[] usernames){
		
		HobbyDAO hobbydao = new HobbyDAO();
		UserinfoDAO userinfodao = new UserinfoDAO(); 
		
		
		for(String username : usernames){
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
		}
	}
}