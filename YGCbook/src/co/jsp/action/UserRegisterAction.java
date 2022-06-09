package co.jsp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import co.jsp.dto.UserRegisterDto;
import co.jsp.form.UserForm;
import co.jsp.service.UserRegisterService;
import co.jsp.service.inter.UserRegisterServiceinter;

@Controller(value="/userRegister")
public class UserRegisterAction extends Action {
	
	//@Autowirde默认通过type查找
	//@Resource//默认通过name查找
	@Autowired
	private UserRegisterService userRegisterService;
	

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
		dto.setIntro(userForm.getIntro());
		//爱好
		dto.setHobby(userForm.getHobby());
		
		boolean sucessFlag =userRegisterService.userRegister(dto);
		
        if(sucessFlag){
		    return mapping.findForward("userRegSucess");
        }else{
        	return mapping.findForward("userRegErr");
        }
	}


	public UserRegisterService getUserRegisterService() {
		return userRegisterService;
	}


	public void setUserRegisterService(UserRegisterService userRegisterService) {
		this.userRegisterService = userRegisterService;
	}


}