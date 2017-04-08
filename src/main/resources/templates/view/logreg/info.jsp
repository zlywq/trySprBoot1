<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include.jsp" %>
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Common Page 2</h1>
	

	
	<P>  The 用户ID is ${userName}. </P>
	<P>  The 用户昵称 is ${userNickName}. </P>
	
	<a href="admin"> Go AdminPage </a>
	

	
	<hr/>
	
	<br/>
	
	<P><a href="../user/getUser?userId=0">getUser</a></P>
	<P><a href="../user/getUser.json?userId=0">getUser.json</a></P>
	<P><a href="../user/getUser.xml?userId=0">getUser.xml</a></P>
	<div>
	    <h2>getUser</h2>
		<form action="<c:url value='../user/getUser'/>" method="GET" target="_blank">
	      <table>
	        <tr><td>userId:</td><td><input type='text' name='userId' value='0'/></td></tr>		        
	        <tr><td colspan='2'></td></tr>
	        <tr><td colspan='2'><input type="submit" value="查看用户信息"/></td></tr>
	      </table>
	    </form>
	</div>
	<br/>
	<br/>
	
	<br/>
	<P><a href="../user/getUserByMobile.json?mobile=111222">getUserByMobile.json</a></P>
	<P><a href="../user/getUserByMobile?mobile=111222">getUserByMobile</a></P>
	<div>
	    <h2>getUserByMobile</h2>
		<form action="<c:url value='../user/getUserByMobile'/>" method="GET" target="_blank">
	      <table>
	        <tr><td>mobile:</td><td><input type='text' name='mobile' value=''/></td></tr>		        
	        <tr><td colspan='2'></td></tr>
	        <tr><td colspan='2'><input type="submit" value="以mobile查看用户信息"/></td></tr>
	      </table>
	    </form>
	</div>
	<br/>
	<br/>

	
	<div>
	    <h2>resetPwdByUser</h2>
		<form action="<c:url value='../logreg/resetPwdByUser.json'/>" method="POST" target="_blank">
			<table>
				<tr><td>userId:</td><td><input type='text' name='userId' value='0'/></td></tr>
				<tr><td>oldPassword</td> <td><input type='password' name='oldPassword'></td></tr>
	        	<tr><td>password</td> <td><input type='password' name='password'></td></tr>
	        	<tr><td>pwd2</td> <td><input type='password' name='pwd2'></td></tr>	        	
	        	<tr><td colspan='2'><input type="submit" value="resetPwdByUser.json"></td></tr>
			</table>
	    </form>
	</div>
	<br/>
	
	
	
	
	<hr/>
	
	
	<br />
	<a href="../logreg/logout">登出</a>
	
	

</body>
</html>












