package co.jsp.service;



import java.util.List;
import co.jsp.dao.UserinfoDAO;
import co.jsp.entity.Userinfo;
public class UserNameCheckService {
	
	public List<Userinfo> userNameCheck(String username){
		
	
		UserinfoDAO userinfodao = new UserinfoDAO(); 
		//等号前面：声明一个泛型为 UserinfoHobby的list 作用？ 用来接收userinfodao.findUserInfoANDHobby的方法返回值
		//等号后面：通过对像userinfodao调用findUserinfoANDHobby方法 调用方式？[.]方法 参数？三个参数都是String类型的
		List<Userinfo> list= userinfodao.checkUserName(username);
		
        return list;
	}
}