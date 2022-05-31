<%@page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@page import="co.jsp.dto.UserinfoHobbyDto"%>
<html>
<head>
<title>用户注册画面</title>
<script>
function changeActionName(actionName){
	document.getElementById("form").action = actionName;
	document.getElementById("username").disabled = false;
}



</script>
</head>

<%UserinfoHobbyDto dto = (UserinfoHobbyDto)request.getAttribute("dto"); %>
<%if(dto != null) {%>
<body>
	<form action="userUpdate.do" id="form">
		姓名:<input disabled id="username" name="username" value="<%=dto.getUsername() %>"><br>
		密码:<input type="password" id="password" name="password" value="<%=dto.getPassword() %>"><br>
		性别:<input type="radio" name="sex"   id="sex1" value="0" <%if("0".equals(dto.getSex())){ %>checked<%} %>>男<input type="radio"  name="sex" id="sex2"  value="1" <%if("1".equals(dto.getSex())){ %>checked<%} %>>女<br>
		爱好:<input type="checkbox" name="hobby" value="0" <%if(dto.getHobbys().contains("0")){ %>checked<%} %>>足球
		    <input type="checkbox" name="hobby" value="1" <%if(dto.getHobbys().contains("1")){ %>checked<%} %>>篮球
		    <input type="checkbox" name="hobby" value="2" <%if(dto.getHobbys().contains("2")){ %>checked<%} %>>网球<br>
		专业:<select id="major" name="major" >
			   <option value="0" <%if("0".equals(dto.getMajor())){ %>selected<%} %>>软件工程</option>
			   <option value="1" <%if("1".equals(dto.getMajor())){ %>selected<%} %>>英语</option>
			   <option value="2" <%if("2".equals(dto.getMajor())){ %>selected<%} %>>数学</option> 
		    </select><br>
		简介:<br><textarea id="intro" name="intro"><%=dto.getIntro() %></textarea><br>
		
		<input type="submit" value="更新用户" onclick="changeActionName('userUpdate.do')">
		<input type="submit" value="删除用户" onclick="changeActionName('userDel.do')">
		<input type="button" value="重置">
	</form>
</body>
<%}else {%>
<body>
	<form action="userRegister.do">
		姓名:<input id="username" name="username"><br>
		密码:<input type="password" id="password" name="password"><br>
		性别:<input type="radio" name="sex"   id="sex1" value="0">男<input type="radio"  name="sex" id="sex2"  value="1" >女<br>
		爱好:<input type="checkbox" name="hobby" value="0">足球
		    <input type="checkbox" name="hobby" value="1">篮球
		    <input type="checkbox" name="hobby" value="2">网球<br>
		专业:<select id="major" name="major" >
			   <option value="0">软件工程</option>
			   <option value="1">英语</option>
			   <option value="2">数学</option> 
		    </select><br>
		简介:<br><textarea id="intro" name="intro"></textarea><br>
		
		<input type="submit" value="注册用户">
		<input type="button" value="重置">
	</form>
</body>
<%} %>
</html>