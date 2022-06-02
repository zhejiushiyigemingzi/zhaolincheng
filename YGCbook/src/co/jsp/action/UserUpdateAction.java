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
import co.jsp.dto.UserRegisterDto;
import co.jsp.entity.Hobby;
import co.jsp.entity.Userinfo;
import co.jsp.form.UserForm;
import co.jsp.service.UserUpdateService;

public class UserUpdateAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm)form;
		UserRegisterDto dto = new UserRegisterDto();
		//姓名
		dto.setUsername(userForm.getUsername());;
		//密码
        dto.setPassword(userForm.getPassword());
		//性别
		dto.setSex(userForm.getSex()); 
		//专业
		dto.setMajor(userForm.getMajor());;
		//简介
		dto.setIntro(userForm.getIntro());;
		//爱好
		dto.setHobby(userForm.getHobby());


		boolean updateUserinfoFlag = new UserUpdateService().userUpdata(dto);
		
	    return mapping.findForward("userUpdateSucess");
			
	}

	
}