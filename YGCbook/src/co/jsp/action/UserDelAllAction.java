package co.jsp.action;



import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.jsp.form.UserForm;
import co.jsp.service.UserDelAllService;
@Controller(value="/delAll")
public class UserDelAllAction extends Action {
	
	@Autowired
	private UserDelAllService userDelAllService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm)form;
		//姓名
	    String[] usernames = userForm.getCheck();
	    
	    userDelAllService.userDelAll(usernames);
        return mapping.findForward("userSearch");
	}

	public UserDelAllService getUserDelAllService() {
		return userDelAllService;
	}

	public void setUserDelAllService(UserDelAllService userDelAllService) {
		this.userDelAllService = userDelAllService;
	}
	
}