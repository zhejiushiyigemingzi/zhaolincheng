package co.jsp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import co.jsp.form.UserForm;
import co.jsp.service.UserDelService;

public class UserDelAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm)form;
		//姓名
	    String username = userForm.getUsername();
		
		//用户信息表更新Flag
		boolean updateUserinfoFlag = new UserDelService().userDel(username);
		
		if(updateUserinfoFlag){
			System.out.println("用户信息表伦理删除成功");
		}else{
			System.out.println("用户信息表伦理删除失败");
		}
		
        return mapping.findForward("userSearch");
	}
}