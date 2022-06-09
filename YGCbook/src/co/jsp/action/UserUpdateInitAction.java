package co.jsp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.jsp.dto.UserinfoHobbyDto;
import co.jsp.form.UserForm;
import co.jsp.service.UserUpdateInitService;

@Controller(value="/userUpdateInit")
public class UserUpdateInitAction extends Action {
	
	
	
	@Autowired
	private UserUpdateInitService userUpdateInitService;
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm)form;
		//姓名 
		String username = userForm.getUsername();
		
		List<UserinfoHobbyDto> userinfoHobbyDto = userUpdateInitService.userUpdateInit(username);
		
		request.setAttribute("dto", userinfoHobbyDto.get(0));
		return mapping.findForward("userReg");
	}
	public UserUpdateInitService getUserUpdateInitService() {
		return userUpdateInitService;
	}
	public void setUserUpdateInitService(UserUpdateInitService userUpdateInitService) {
		this.userUpdateInitService = userUpdateInitService;
	}

	
}