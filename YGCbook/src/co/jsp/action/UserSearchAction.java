package co.jsp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import co.jsp.dto.UserinfoHobbyDto;
import co.jsp.form.UserForm;
import co.jsp.service.UserSearchService;

public class UserSearchAction extends Action {
	
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
		
		UserSearchService usersearchservice = new UserSearchService();
		List<UserinfoHobbyDto> UserinfoHobbyDto = usersearchservice.userSearch(username, sex, major);
		
		request.setAttribute("UserinfoHobbyDto", UserinfoHobbyDto);
        return mapping.findForward("userSearch");
	}
}