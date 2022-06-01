package co.jsp.action;



import java.io.PrintWriter;

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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AjaxUserSearchAction extends Action {
	
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
		//声明UserinfoDAO的,对象名称为userinfodao
		UserinfoDAO userinfodao = new UserinfoDAO(); 
		//等号前面：声明一个泛型为 UserinfoHobby的list 作用？ 用来接收userinfodao.findUserInfoANDHobby的方法返回值
		//等号后面：通过对像userinfodao调用findUserinfoANDHobby方法 调用方式？[.]方法 参数？三个参数都是String类型的
		List<UserinfoHobby> list= userinfodao.findUserInfoANDHobby(usernames, sex, major);
		
		List<UserinfoHobbyDto> UserinfoHobbyDto = new ArrayList<UserinfoHobbyDto>();
		//Map<String, String> userNamemap = new HashMap<String, String>();
		//标识dto是否被创建
		
		List<String> userNameList = new ArrayList<String>();
		//System.out.println("用户信息如下");
		for(UserinfoHobby userinfoHobby:list){
			//当用户名不存在时,证明大的dto未创建
			if(!userNameList.contains(userinfoHobby.getUsername())){
				//通过有参构造方法创建UserinfoHobbyDto的对象
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
				    sb.append(hobbyDto.getHobby() + ",");
				}
			}
			result.setHobbys(sb.toString().replace("0","足球").replace("1","篮球").replace("2","网球"));
			//当有爱好是，才执行操作截取逗号操作，否则报错
			if(!"".equals(result.getHobbys())){
				if(",".equals(sb.toString().substring(sb.toString().length()-1))){
					result.setHobbys(result.getHobbys().substring(0,result.getHobbys().length()-1));
				}else{
					result.setHobbys(result.getHobbys());
				}
			}
		 } 
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out=response.getWriter();
		//把检索结果转换为json序列
		JSONArray json=JSONArray.fromObject(UserinfoHobbyDto);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("datas", json);
		out.print(jsonObject);
		out.close();
		
		
        return null;
	}
}