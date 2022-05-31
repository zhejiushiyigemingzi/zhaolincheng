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

public class UserRegisterAction extends Action {
	

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
		
		boolean sucessFlag = true;
		//用户信息表登录
		//boolean result = dao.save(new Userinfo(username,password,sex,major,intro));
		
		if(userinfodao.save(new Userinfo(username,password,sex,major,intro))){
			
			System.out.println("用户信息表登陆成功");
			
		}else{
			System.out.println("用户信息表登陆失败");
			sucessFlag = false;
			
		}
		System.out.println(sucessFlag);
		//用户爱好表登录
        if(hobbydao.save(hobbyList)){
			
			System.out.println("用户爱好表登陆成功");
			
		}else{
			System.out.println("用户爱好表登陆失败");
			sucessFlag = false;
			
		}
        System.out.println(sucessFlag);
		//request.setAttribute("admin", admin);
        if(sucessFlag){
		    return mapping.findForward("userRegSucess");
        }else{
        	return mapping.findForward("userRegErr");
        }
	}
	

}