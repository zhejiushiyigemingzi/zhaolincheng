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
import co.jsp.service.UserSearchService;

@Controller(value="/userSearch")
public class UserSearchAction extends Action {
	
	@Autowired
	private UserSearchService usersearchservice;
	
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		//姓名
		String username = userForm.getUsername();
		//性别
		String sex = userForm.getSex();
		//专业
		String major = userForm.getMajor();
		
        
		List<UserinfoHobbyDto> UserinfoHobbyDto = usersearchservice.userSearch(username, sex, major);
		
		request.setAttribute("UserinfoHobbyDto", UserinfoHobbyDto);
        return mapping.findForward("userSearch");
	}


	public UserSearchService getUsersearchservice() {
		return usersearchservice;
	}


	public void setUsersearchservice(UserSearchService usersearchservice) {
		this.usersearchservice = usersearchservice;
	}
	
}