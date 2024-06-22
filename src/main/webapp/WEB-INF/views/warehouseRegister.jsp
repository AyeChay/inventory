<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Warehouse</title>
</head>
<body>
	<h2>Register Warehouse</h2>
    <form:form action="${pageContext.request.contextPath}/warehouse/doregister" modelAttribute="warehouse">
        <div>
            <form:label path="productCode">Product Code:</form:label>
            <form:input path="productCode" />
        </div>
        <div>
            <form:label path="productName">Product Name:</form:label>
            <form:input path="productName" />
        </div>
        <div>
            <form:label path="description">Description:</form:label>
            <form:textarea path="description" />
        </div>
        <div>
            <form:label path="quantity">Quantity:</form:label>
            <form:input path="quantity" />
        </div>
        <div>
            <form:label path="date">Date:</form:label>
            <form:input path="date" type="date" />
        </div>
        <div>
            <form:label path="expireDate">Expire Date:</form:label>
            <form:input path="expireDate" type="date" />
        </div>
        <div>
            <form:label path="locationId">Location:</form:label>
            <form:select path="locationId">
                <form:options items="${locations}" itemValue="id" itemLabel="name" />
            </form:select>
        </div>
        <div>
            <input type="submit" value="Register" />
        </div>
    </form:form>
</body>
</html>