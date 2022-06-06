package co.jsp.mapper;

public interface HobbyMapper {
	//方法返回值 void 与xml文件中的返回值一致
	public void save(String username,String hobby);
		
	public void delHobby(String username);
}
