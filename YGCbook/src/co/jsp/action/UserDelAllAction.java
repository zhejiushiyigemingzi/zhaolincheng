package co.jsp.action;



import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import co.jsp.form.UserForm;
import co.jsp.service.UserDelAllService;

public class UserDelAllAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm)form;
		//姓名
	    String[] usernames = userForm.getCheck();
	    UserDelAllService userDelAllService = new UserDelAllService();
	    userDelAllService.userDelAll(usernames);
        return mapping.findForward("userSearch");
	}
}