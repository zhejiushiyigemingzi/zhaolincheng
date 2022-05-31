package co.jsp.action;









import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import co.jsp.dao.HobbyDAO;
import co.jsp.dao.UserinfoDAO;
import co.jsp.entity.Hobby;
import co.jsp.entity.Userinfo;
import co.jsp.form.UserForm;

public class UserUpdateAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm)form;
		//姓名
		String username = userForm.getUsername();
		//密码
		String password = userForm.getPassword();
		//性别
		String sex = userForm.getSex(); 
		//专业
		String major = userForm.getMajor();
		//简介
		String intro = userForm.getIntro();
		//爱好
		String[] hobbyArray = userForm.getHobby();
				
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
	    return mapping.findForward("userUpdateSucess");
			
	}

	
}