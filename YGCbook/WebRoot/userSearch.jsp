<%@page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
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
		   <table border="1" style="width:80%;text-align:center">
			  <tr>
			    <td><strong>姓名</strong></td>
			    <td><strong>密码</strong></td>
			    <td><strong>性别</strong></td>
			    <td><strong>爱好</strong></td>
			    <td><strong>专业</strong></td>
			    <td><strong>简介</strong></td>
			  </tr>
			  <tr>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			  </tr>
			  <tr>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			  </tr>
			  <tr>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			  </tr>
			  <tr>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			    <td><br></td>
			  </tr>
			</table>
		</center>
		
	</form>
</body>
</html>