<%@ taglib prefix="mytld" uri="/WEB-INF/jstlTld/jstl.tld" %>
<%@ page pageEncoding="UTF-8" language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include.jsp" %>

<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>err info</title>
</head>
<body>

	<h1>err occurred</h1>

	<div id="errMsg">${errMsg}</div>
	
	<c:if test="${!empty errCode}" >
		<p>errCode:${errCode}</p>
    </c:if>
    <c:if test="${!empty errMsgDetail}" >
		<p>${errMsgDetail}</p>
    </c:if>   
	

</body>
</html>