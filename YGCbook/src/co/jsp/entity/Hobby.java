package co.jsp.entity;



public class Hobby {
	//用户名
	private String username;
	//爱好
	private String hobby;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Hobby(String username, String hobby) {
		super();
		this.username = username;
		this.hobby = hobby;
	}
	public Hobby() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
