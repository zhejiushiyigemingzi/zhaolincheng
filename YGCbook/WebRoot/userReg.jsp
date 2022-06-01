<%@page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@page import="co.jsp.dto.UserinfoHobbyDto"%>
<%@ taglib  uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<title>用户注册画面</title>
<script>
function changeActionName(actionName){
	document.getElementById("form").action = actionName;
	document.getElementById("username").disabled = false;
}
//定义executeajax函数
function executeAjax(){
	$.ajax({
		url : 'userNameCheck.do?username=' + document.getElementById("username").value,
		type : 'POST',//数据发送方式
		dataType : 'text',//接受数据格式
		error : function(flag){
	    },
		async : true,//异步加载
		//PrintWinter输出的值会被ajax回调函数success : function(flag){}中的flag参数获取
		success : function(flag){
			if(flag == "0"){
		        //没有被注册 
			    document.getElementById("flag").innerHTML="";
		        document.getElementById("regSubmit").disabled = false;
			}else{
				//已经被注册
				document.getElementById("flag").innerHTML="用户名已存在！！！";
				document.getElementById("regSubmit").disabled = "disabled";
			} 
			  
		}
	});
}
</script>
<script src="jquery-3.2.1.min.js">

</script>
</head>

<%UserinfoHobbyDto dto = (UserinfoHobbyDto)request.getAttribute("dto"); %>
<%if(dto != null) {%>
<body>
	<form action="userUpdate.do" id="form">
		<bean:message bundle="resource" key="username"/>:<input disabled id="username" name="username" value="<%=dto.getUsername() %>"><br>
		<bean:message bundle="resource" key="password"/>:<input type="password" id="password" name="password" value="<%=dto.getPassword() %>"><br>
		<bean:message bundle="resource" key="sex"/>:<input type="radio" name="sex"   id="sex1" value="0" <%if("0".equals(dto.getSex())){ %>checked<%} %>><bean:message bundle="resource" key="male"/><input type="radio"  name="sex" id="sex2"  value="1" <%if("1".equals(dto.getSex())){ %>checked<%} %>><bean:message bundle="resource" key="famale"/><br>
		<bean:message bundle="resource" key="hobby"/>:
		    <input type="checkbox" name="hobby" value="0" <%if(dto.getHobbys().contains("0")){ %>checked<%} %>><bean:message bundle="resource" key="soccer"/>
		    <input type="checkbox" name="hobby" value="1" <%if(dto.getHobbys().contains("1")){ %>checked<%} %>><bean:message bundle="resource" key="basket"/>
		    <input type="checkbox" name="hobby" value="2" <%if(dto.getHobbys().contains("2")){ %>checked<%} %>><bean:message bundle="resource" key="tennis"/><br>
		<bean:message bundle="resource" key="major"/>:<select id="major" name="major" >
			   <option value="0" <%if("0".equals(dto.getMajor())){ %>selected<%} %>><bean:message bundle="resource" key="software"/></option>
			   <option value="1" <%if("1".equals(dto.getMajor())){ %>selected<%} %>><bean:message bundle="resource" key="english"/></option>
			   <option value="2" <%if("2".equals(dto.getMajor())){ %>selected<%} %>><bean:message bundle="resource" key="math"/></option> 
		    </select><br>
		<bean:message bundle="resource" key="intro"/>:<br><textarea id="intro" name="intro"><%=dto.getIntro() %></textarea><br>
		
		<input type="submit" value="<bean:message bundle="resource" key="userUpdate"/>" onclick="changeActionName('userUpdate.do')">
		<input type="submit" value="<bean:message bundle="resource" key="userDel"/>" onclick="changeActionName('userDel.do')">
		<input type="button" value="<bean:message bundle="resource" key="reset"/>">
	</form>
</body>
<%}else{%>
<body>
	<form action="userRegister.do">
		<bean:message bundle="resource" key="username"/>:<input onblur="executeAjax()" id="username" name="username"><font color="red" id="flag"></font><br>
		<bean:message bundle="resource" key="password"/>:<input type="password" id="password" name="password"><br>
		<bean:message bundle="resource" key="sex"/>:<input type="radio" name="sex"   id="sex1" value="0"><bean:message bundle="resource" key="male"/><input type="radio"  name="sex" id="sex2"  value="1" ><bean:message bundle="resource" key="famale"/><br>
		<bean:message bundle="resource" key="hobby"/>:<input type="checkbox" name="hobby" value="0"><bean:message bundle="resource" key="soccer"/>
		    <input type="checkbox" name="hobby" value="1"><bean:message bundle="resource" key="basket"/>
		    <input type="checkbox" name="hobby" value="2"><bean:message bundle="resource" key="tennis"/><br>
		<bean:message bundle="resource" key="major"/>:<select id="major" name="major" >
			   <option value="0"><bean:message bundle="resource" key="software"/></option>
			   <option value="1"><bean:message bundle="resource" key="english"/></option>
			   <option value="2"><bean:message bundle="resource" key="math"/></option> 
		    </select><br>
		<bean:message bundle="resource" key="intro"/>:<br><textarea id="intro" name="intro"></textarea><br>
		
		<input type="submit" id="regSubmit" value="<bean:message bundle="resource" key="userRegister"/>">
		<input type="button" value="<bean:message bundle="resource" key="reset"/>">
	</form>
</body>
<%}%>
</html>