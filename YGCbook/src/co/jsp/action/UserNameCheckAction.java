package co.jsp.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.jsp.entity.Userinfo;
import co.jsp.form.UserForm;
import co.jsp.service.UserNameCheckService;


@Controller("/userNameCheck")
public class UserNameCheckAction extends Action {
	
	@Autowired
    private UserNameCheckService userNameCheckService;
	
	//新建UserNameCheckAction，execute方法体的返回值为null
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		//姓名
		String username = userForm.getUsername();
		

        Userinfo userinfo = userNameCheckService.userNameCheck(username);
		
		PrintWriter printWriter = response.getWriter();
		//调用DAO，从数据库获取用户信息....
		if(userinfo != null){
			printWriter.print("1");;
		}else{
			printWriter.print("0");
		}
        return null;
	}

	public UserNameCheckService getUserNameCheckService() {
		return userNameCheckService;
	}

	public void setUserNameCheckService(UserNameCheckService userNameCheckService) {
		this.userNameCheckService = userNameCheckService;
	}
	
}