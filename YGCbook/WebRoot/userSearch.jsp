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
		//searchUsers.do?sex=0 => 这种情况，后台得到的username值为null
		//swaechUsers.do?sex=0&username=  => 这种情况，后台得到的username值为空字符串
		$.ajax({
			url : 'searchUsers.do?sex=' + sex + '&major=' + major + '&username=' + document.getElementById("username").value,
			type : 'post',//数据发送方式
			dataType : 'json',//接受数据格式
			error : function(users){
				alert("失败"); 
		    },
			async : true,//异步加载
			//PrintWinter输出的值会被ajax回调函数success : function(flag){}中的flag参数获取
			success : function(users){
				//将数据反映到表单
				while($("#userTable tr").length>1){
					//jquery实现删除除标题行以外tr
					$("#userTable tr").eq(1).remove();
			    }
				for(i in users.datas){ 
					//与前台users.datas对应
					username = users.datas[i].username;
					password = users.datas[i].password;
					
					sex = users.datas[i].sex;
					if(sex == 0){
						sex = "男";
					}else{
						sex = "女";
					}
					hobbys = users.datas[i].hobbys;
					major = users.datas[i].major;
					if(major == 0){
						major = "软件工程";
					}else if(major == 1){
						major = "英语";
					}else{
						major = "数学";
					}
					intro = users.datas[i].intro;
					//颜色控制
					var bgcolor;
					if(i%2 ==0){
						bgcolor="#F0F8FF";
					}else{
						bgcolor="#7FFFD4";
					}
					
					var userTr = '<tr bgcolor="' + bgcolor + '">' + 
				    				 '<td><input type="checkbox"></td>' +
				                     '<td>' +    '<a href="userUpdateInit.do?username=' + username + '">' + username + '</a>' +  '</td>' +
				                     '<td>' + password + '</td>' + 
				                     '<td>' + sex + '</td>' +
				                     '<td>' + hobbys + '</td>' +
				                     '<td>' + major + '</td>' +
				                     '<td>' + intro + '</td>' + 
				                     '<td style="width:20"><input type="button" value="更新"' + " onClick=changeToUpdatePage('" + username + "')" +'></td>' +
				                     '<td style="width:20"><input type="button" value="删除"' + " onClick=delUser('" + username + "')" +'></td>' +
				                  '</tr>';
				    //往table末尾中添加HTML代码              
				    $("#userTable").append(userTr);
				}
				$("#userTable").append('<tr bgcolor="pink" align="center"><td colspan="9"><input onClick="delAction()"type="submit" onClick="" value="<bean:message bundle="resource" key="allDel"/>"></td></tr>');
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
   
   
   function search(){
	   document.getElementById('search').click();
   }
</script>
<title>课后练习题</title>

</head>


<body onload="search()">
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
			  <table border="1" style="width:100%;text-align:center" id="userTable">
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
				<tr bgcolor="pink" align="center"><td colspan="9"><input onClick="delAction()"type="submit" value="<bean:message bundle="resource" key="allDel"/>"></td></tr>
				</table>
			<%}%>	
		
	</form>
</body>
</html>