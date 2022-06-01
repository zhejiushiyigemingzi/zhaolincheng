<%@page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@page import="co.jsp.dto.UserinfoHobbyDto"%>
<%@page import="co.jsp.dto.HobbyDto"%>
<%@ taglib  uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<script src="jquery-3.2.1.min.js">
</script>
<script>
	function executeAjax(){
		var sex;
		if(document.getElementsByName("sex")[0].checked){
			sex = 0;
		}else{
			sex = 1;
		}
		var major = document.getElementById("major").value;
		
		$.ajax({
			url : 'searchUsers.do?sex=' + sex + '&major=' + major + '&username=' + document.getElementById("username").value,
			type : 'post',//数据发送方式
			dataType : 'json',//接受数据格式
			error : function(users){
				alert(123); 
		    },
			async : true,//异步加载
			//PrintWinter输出的值会被ajax回调函数success : function(flag){}中的flag参数获取
			success : function(users){
				for(i in users.datas){ 
				  //将数据反映到前台表单
				  alert(users.datas[i].username);
				}
			}
		});
	}
   function changeToUpdatePage(username){
	    window.location="userUpdateInit.do?username=" + username;
   }
   function delUser(username){
	    window.location="userDel.do?username=" + username;
   }
   function delAll(){
	    if(document.getElementById('allCheckBox')){
	    	var checkboxs = document.getElementsByName('check');
	    	for(var i =0;i<checkboxs.length;i++){
	    		checkboxs[i].checked = true;
	    	}
	    }else{
	    	var checkboxs = document.getElementsByName('check');
	    	for(var i =0;i<checkboxs.length;i++){
	    		checkboxs[i].checked = false;
	    	}
	    }
   }

   function delAction(){
	   document.getElementById('form').action = 'delAll.do';
   }
</script>
<title>课后练习题</title>

</head>


<body>
	<form action="userSearch.do" id="form">
		姓名:<input id="username" name="username" onblur="executeAjax()"><br>
		<bean:message bundle="resource" key="sex"/>:
		<input type="radio" name="sex" checked  id="sex1" value="0" onblur="executeAjax()"><bean:message bundle="resource" key="male"/>
		<input type="radio"  name="sex" id="sex2"  value="1" onblur="executeAjax()"><bean:message bundle="resource" key="famale"/><br>
		
		<bean:message bundle="resource" key="major"/>:
		<select id="major" name="major" onblur="executeAjax()">
		    <option value="" onblur="executeAjax()"></option>
			<option value="0" onblur="executeAjax()"><bean:message bundle="resource" key="software"/></option>
			<option value="1" onblur="executeAjax()"><bean:message bundle="resource" key="english"/></option>
			<option value="2" onblur="executeAjax()"><bean:message bundle="resource" key="math"/></option>
		</select><br>
		<input type="submit" value="<bean:message bundle="resource" key="search"/>">
		<br><br><br>
		<bean:message bundle="resource" key="searchList"/>
		<hr>
		
		   <% List<UserinfoHobbyDto> UserinfoHobbyDto =(List<UserinfoHobbyDto>)request.getAttribute("UserinfoHobbyDto");%>
			  <% if(UserinfoHobbyDto != null){%>
			  <table border="1" style="width:100%;text-align:center">
					  <tr bgcolor="grey">
					    <td style="width:20"><input type="checkbox" id="allCheckBox"onClick="delAll()"></td>
					    <td><strong><bean:message bundle="resource" key="username"/></strong></td>
					    <td><strong><bean:message bundle="resource" key="password"/></strong></td>
					    <td><strong><bean:message bundle="resource" key="sex"/></strong></td>
					    <td><strong><bean:message bundle="resource" key="hobby"/></strong></td>
					    <td><strong><bean:message bundle="resource" key="major"/></strong></td>
					    <td><strong><bean:message bundle="resource" key="intro"/></strong></td>
					    <td style="width:20"><bean:message bundle="resource" key="update"/></td>
					    <td style="width:20"><bean:message bundle="resource" key="delete"/></td>
					  </tr>
					  <% int line=0;%>
					  <% for(UserinfoHobbyDto dto : UserinfoHobbyDto){
					        line++;%>
					  <% if(line%2 == 0){%>
					      <tr bgcolor="#F0F8FF">
					  <%} else{%>
					      <tr bgcolor="#7FFFD4">
					  <%} %>
					  <td style="width:20"><input type="checkbox" name="check" value="<%=dto.getUsername()%>"></td>
					  <td><a href="userUpdateInit.do?username=<%=dto.getUsername()%>"><%=dto.getUsername()%></a></td>
					    <td><%=dto.getPassword()%></td>
					    <td>
					    <%
					    //性别
					    if("0".equals(dto.getSex())){%>
					      <bean:message bundle="resource" key="male"/>
					    <%}else{%>
					      <bean:message bundle="resource" key="famale"/>
					    <%}%>			    
					    </td>
					    <td><%=dto.getHobbys()%></td>
					    <td>
					    <%if("0".equals(dto.getMajor())){%>
					      <bean:message bundle="resource" key="software"/>
					    <%}%>
					    <%if("1".equals(dto.getMajor())){%>
					      <bean:message bundle="resource" key="english"/>
					    <%}%>
					    <%if("2".equals(dto.getMajor())){%>
					      <bean:message bundle="resource" key="math"/>
					    <%}%>
					    </td>
					    <td><%=dto.getIntro()%>
					    </td>
					    <td style="width:20" ><input type="button" onClick="changeToUpdatePage('<%=dto.getUsername() %>')" value="<bean:message bundle="resource" key="update"/>"></td>
					    <td style="width:20"><input type="button" onClick="delUser('<%=dto.getUsername() %>')" value="<bean:message bundle="resource" key="delete"/>"></td>
					  </tr>
				<%}%>
				<tr bgcolor="pink" align="center"><td colspan="9"><input onClick="delAction()"type="submit" onClick="" value="<bean:message bundle="resource" key="allDel"/>"></td></tr>
				</table>
			<%}%>	
		
	</form>
</body>
</html>