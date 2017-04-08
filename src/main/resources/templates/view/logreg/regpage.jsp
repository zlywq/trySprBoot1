<%@ page pageEncoding="UTF-8" language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include.jsp" %>

<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户注册</title>
</head>
<body>

	<h1>用户注册</h1>

	
	<c:if test="${!empty errMsg}" >
		<div id="errMsg">
			错误信息：<br/>
			${errMsg}
		</div>
	</c:if>  
	
	<!--  
	<P>  The msgToken is ${msgToken}. </P>
	-->
	
	<c:choose>
	    <c:when test="${success}">
		    <div id="success">
				<p>注册成功。</p>
					        
		        <P> go to <a href="../">main page</a> </P>
		        <P> <a href="../logreg/logout">登出</a></P>	
		    </div>	       
	    </c:when>	    
	    <c:otherwise>
		    <form method="post" target="_blank">
				<table>
		        	<tr><td>昵称:</td> <td><input type='text' name='nickName'/></td></tr>
		        	<tr><td>密码:</td> <td><input type='password' name='password'></td></tr>
		        	<tr><td>确认密码:</td> <td><input type='password' name='pwd2'></td></tr>
		        	<tr><td>手机:</td> <td><input type='text' name='mobile'></td></tr>
		        	<tr><td>email:</td> <td><input type='text' name='email'></td></tr>
		        	
		        	<tr><td colspan='2'><input name="submit" type="submit"></td></tr>
		        	<tr><td colspan='2'><input name="reset" type="reset"></td></tr>
		        </table>
			</form>
			<br/>
			
			<form method="post" action="<c:url value='/logreg/reg.json'/>" target="_blank">
				<table>
		        	<tr><td>昵称:</td> <td><input type='text' name='nickName'/></td></tr>
		        	<tr><td>密码:</td> <td><input type='password' name='password'></td></tr>
		        	<tr><td>确认密码:</td> <td><input type='password' name='pwd2'></td></tr>
		        	<tr><td>手机:</td> <td><input type='text' name='mobile'></td></tr>
		        	<tr><td>email:</td> <td><input type='text' name='email'></td></tr>

		        	<tr><td colspan='2'><input name="submit" type="submit" value='reg.json'></td></tr>
		        	<tr><td colspan='2'><input name="reset" type="reset"></td></tr>
		        </table>
			</form>

	    </c:otherwise>
	</c:choose>

	

</body>
</html>