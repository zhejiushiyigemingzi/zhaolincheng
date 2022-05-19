<%@page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@page import="co.jsp.dto.UserinfoHobbyDto"%>
<%@page import="co.jsp.dto.HobbyDto"%>
<html>
<head>
<title>课后练习题</title>

</head>


<body>
	<form action="userSearch">
		姓名:<input id="username" name="username"><br>
		性别:<input type="radio" name="sex" checked  id="sex1" value="0">男<input type="radio"  name="sex" id="sex2"  value="1">女<br>
		
		专业:<select id="major" name="major">
		    <option value=""></option>
			<option value="0">软件工程</option>
			<option value="1" >英语</option>
			<option value="2">数学</option>
		</select><br>
		<input type="submit" value="检索">
		<br><br><br>
		检索一览
		<hr>
		<center>
		   <% List<UserinfoHobbyDto> UserinfoHobbyDto =(List<UserinfoHobbyDto>)request.getAttribute("UserinfoHobbyDto");%>
			  <% if(UserinfoHobbyDto != null){%>
			  <table border="1" style="width:80%;text-align:center">
					  <tr bgcolor="yellow">
					    <td><strong>姓名</strong></td>
					    <td><strong>密码</strong></td>
					    <td><strong>性别</strong></td>
					    <td><strong>爱好</strong></td>
					    <td><strong>专业</strong></td>
					    <td><strong>简介</strong></td>
					  </tr>
			      <% for(UserinfoHobbyDto dto : UserinfoHobbyDto){%>
					  <tr bgcolor="pink">
					    <td><%=dto.getUsername()%></td>
					    <td><%=dto.getPassword()%></td>
					    <td>
					    <%
					    //性别
					    if("0".equals(dto.getSex())){%>
					                    男
					    <%}else{%>
					                    女
					    <%}%>			    
					    </td>
					    <td><%=dto.getHobbys()%></td>
					    <td>
					    <%if("0".equals(dto.getMajor())){%>
					                    软件工程
					    <%}%>
					    <%if("1".equals(dto.getMajor())){%>
					                    英语
					    <%}%>
					    <%if("2".equals(dto.getMajor())){%>
					                    数学
					    <%}%>
					    </td>
					    <td><%=dto.getIntro()%>
					    </td>
					  </tr>
				<%}%>
				</table>
			<%}%>	
		</center>
	</form>
</body>
</html>