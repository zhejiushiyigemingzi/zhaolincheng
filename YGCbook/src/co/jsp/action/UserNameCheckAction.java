package co.jsp.action;



import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import co.jsp.dao.UserinfoDAO;
import co.jsp.entity.Userinfo;
import co.jsp.form.UserForm;
public class UserNameCheckAction extends Action {
	
	//新建UserNameCheckAction，execute方法体的返回值为null
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		UserForm userForm = (UserForm)form;
		//姓名
		String username = userForm.getUsername();
		//声明UserinfoDAO的,对象名称为userinfodao
		UserinfoDAO userinfodao = new UserinfoDAO(); 
		//等号前面：声明一个泛型为 UserinfoHobby的list 作用？ 用来接收userinfodao.findUserInfoANDHobby的方法返回值
		//等号后面：通过对像userinfodao调用findUserinfoANDHobby方法 调用方式？[.]方法 参数？三个参数都是String类型的
		List<Userinfo> list= userinfodao.checkUserName(username);
		
		PrintWriter printWriter = response.getWriter();
		//调用DAO，从数据库获取用户信息....
		if(list.size()>=1){
			printWriter.print("1");;
		}else{
			printWriter.print("0");
		}
        	return null;
	}
}