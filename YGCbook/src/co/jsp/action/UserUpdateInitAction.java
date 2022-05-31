package co.jsp.action;


import java.io.IOException;






import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import co.jsp.dao.UserinfoDAO;
import co.jsp.dto.HobbyDto;
import co.jsp.dto.UserinfoHobbyDto;
import co.jsp.entity.UserinfoHobby;
import co.jsp.form.UserForm;

public class UserUpdateInitAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm)form;
		//姓名
		String username = userForm.getUsername();
		//性别
		//String sex = request.getParameter("sex");
		//爱好
        //String[] hobbyArray = request.getParameterValues("hobby");		
		//专业
		//String major = request.getParameter("major");
		/*HobbyDAO hobbydao = new HobbyDAO();
		List<Hobby> list1 = hobbydao.findByName(username, hobbyArray);*/
		
		UserinfoDAO userinfodao = new UserinfoDAO(); 
		
		List<UserinfoHobby> list= userinfodao.findUserInfoANDHobby(username);
		
		List<UserinfoHobbyDto> UserinfoHobbyDto = new ArrayList<UserinfoHobbyDto>();
		//Map<String, String> userNamemap = new HashMap<String, String>();
		//标识dto是否被创建
		
		List<String> userNameList = new ArrayList<String>();
		System.out.println("用户信息如下");
		for(UserinfoHobby userinfoHobby:list){
			//当用户名不存在时,证明大的dto未创建
			if(!userNameList.contains(userinfoHobby.getUsername())){
				UserinfoHobbyDto dto = new UserinfoHobbyDto(userinfoHobby.getUsername(),
															userinfoHobby.getPassword(),
															userinfoHobby.getSex(),
															userinfoHobby.getMajor(),
															userinfoHobby.getIntro());
			    //将创建的大的dto加到画面展示list里去
				UserinfoHobbyDto.add(dto);
				//将用户名加到用户名list里去
				userNameList.add(userinfoHobby.getUsername());
				//将爱好加入子dto中
				if(userinfoHobby.getHobby() != null){
			         dto.getHobbylist().add(new HobbyDto(userinfoHobby.getHobby()));
				}
			}else{
				//取得以往添加过得大DTO
	            for(UserinfoHobbyDto temp : UserinfoHobbyDto){
		           if(temp.getUsername().equals(userinfoHobby.getUsername())){
			         //将爱好加入子dto中
		        	 if(userinfoHobby.getHobby() != null){
			             temp.getHobbylist().add(new HobbyDto(userinfoHobby.getHobby()));
		        	 }
	               }
	            }
		    }
	    }
		
		
		
		for(UserinfoHobbyDto result : UserinfoHobbyDto){
			StringBuffer sb = new StringBuffer("");
			for(HobbyDto hobbyDto : result.getHobbylist()){
				if(hobbyDto.getHobby() != null){
				    sb.append(hobbyDto.getHobby());
				}
			}
			
			result.setHobbys(sb.toString());
		}
		
		request.setAttribute("dto", UserinfoHobbyDto.get(0));
		return mapping.findForward("userReg");
        	
	}
}