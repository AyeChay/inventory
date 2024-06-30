<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Register Lot</h2>
    <form:form modelAttribute="lot" method="post" action="${pageContext.request.contextPath}/lot/doregister">
        
        <form:label path="quantity">Quantity:</form:label>
        <form:input path="quantity"/>
        <form:errors path="quantity" cssClass="error"/>
        <br/>
        <form:label path="uom">Unit of Measure:</form:label>
        <form:select path="uom">
			<form:option value="NONE" label="Select"/>
			<form:options items="${UOMList}" />								
		</form:select>
        <form:errors path="uom" cssClass="error"/>
        <br/>
        <form:label path="date">Date:</form:label>
        <form:input type="date" path="date"/>
        <form:errors path="date" cssClass="error"/>
        <br/>
        <form:label path="expired_date">Expired Date:</form:label>
        <form:input type="date" path="expired_date"/>
        <form:errors path="expired_date" cssClass="error"/>
        <br/>
        <form:label path="price">Price:</form:label>
        <form:input path="price"/>
        <form:errors path="price" cssClass="error"/>
        <br/>
        <input type="submit" value="Register"/>
    </form:form>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
</body>
</html>