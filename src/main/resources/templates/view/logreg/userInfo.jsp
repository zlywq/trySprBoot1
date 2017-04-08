<%@ taglib prefix="mytld" uri="/WEB-INF/jstlTld/jstl.tld" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include.jsp" %>
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user信息</title>
</head>
<body>
	<h1>user信息</h1>
	
	<c:if test= "${empty userInfo}"> 
        <P><b>没有user信息</b></P>
    </c:if>
	
	
	<div id="errMsg">${errMsg}</div>
	
	<div>
	<table border=1>
		<tr>
			<td>userId</td>
			<td>${userInfo.userId}</td> 
		</tr>
		
		<tr>
			<td>nickName</td>
			<td>${userInfo.nickName}</td> 
		</tr>
		<tr>
			<td>mobile</td>
			<td>${userInfo.mobile}</td> 
		</tr>
	
		
		
		<tr>
			<td>email</td>
			<td>${userInfo.email}</td> 
		</tr>
		
		<tr>
			<td>isDeleted</td>
			<td>${userInfo.isDeleted}</td> 
		</tr>
				
		<tr>
			<td>createTime</td>
			<td>${userInfo.createTime}, <fmt:formatDate value="${ mytld:convertDateFromIntSeconds(userInfo.createTime) }" pattern="yyyyMMdd-HHmmss" type="both"/>
			</td>    			
		</tr>
		<tr>
			<td>updateTime</td>
			<td>${userInfo.updateTime}, <fmt:formatDate value="${ mytld:convertDateFromIntSeconds(userInfo.updateTime) }" pattern="yyyyMMdd-HHmmss" type="both"/>
			</td>    			
		</tr>
		<tr>
		</tr>
    </table>
	</div>
	
	
	
	
	<c:choose>
    <c:when test="${empty someObjField}">
       	<P></P>
	</c:when>
	<c:otherwise>
		<div>
		<P>作为快递员的信息</P>
		<table border=1>
			<tr>
				<td>f1</td>
				<td>${someObjField.f1}</td> 
			</tr>
			
			
			
	    </table>
		</div>
	</c:otherwise>
    </c:choose>
	
	
	
	
	
	
</body>
</html>