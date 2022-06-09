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
import co.jsp.service.UserDelService;


@Controller(value="/userDel")
public class UserDelAction extends Action {
	
	@Autowired
	private UserDelService userDelService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm)form;
		//姓名
	    String username = userForm.getUsername();
		
		//用户信息表更新Flag
		boolean  updateUserinfoFlag = userDelService.userDel(username);

        return mapping.findForward("userSearch");
	}

	public UserDelService getUserDelService() {
		return userDelService;
	}

	public void setUserDelService(UserDelService userDelService) {
		this.userDelService = userDelService;
	}
	
}