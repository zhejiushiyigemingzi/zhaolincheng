package co.jsp.action;

import java.io.PrintWriter;
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
import co.jsp.service.AjaxUserSearchService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller(value="/searchUsers")
public class AjaxUserSearchAction extends Action {
	
	@Autowired
	private  AjaxUserSearchService ajaxUserSearchService;
	
	
	//新建AjaxUserSearchAction，导入四个jar包commons-collections-3.2.jar，commons-lang-2.2.jar，ezmorph-1.0.6.jar，json-lib-2.2.3-jdk15.jar
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		//姓名
		String usernames = userForm.getUsername();
		//性别
		String sex = userForm.getSex();
		//专业
		String major = userForm.getMajor();
		

	    List<UserinfoHobbyDto> userinfoHobbyDto = ajaxUserSearchService.ajaxUserSearch(usernames, sex, major);
	
		//解决ajax输出乱码问题
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out=response.getWriter();
		//把检索结果转换为json序列
		JSONArray json=JSONArray.fromObject(userinfoHobbyDto);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("datas", json);
		out.print(jsonObject);
		out.close();	
		
		return null;
	}


	public AjaxUserSearchService getAjaxUserSearchService() {
		return ajaxUserSearchService;
	}


	public void setAjaxUserSearchService(AjaxUserSearchService ajaxUserSearchService) {
		this.ajaxUserSearchService = ajaxUserSearchService;
	}
	
}