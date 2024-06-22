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
	<h2>Edit Location</h2>
    <form:form action="${pageContext.request.contextPath}/location/doupdate" modelAttribute="location">
        <form:hidden path="id" />
        <div>
            <form:label path="name">Location Name:</form:label>
            <form:input path="name" />
        </div>
        <div>
            <form:label path="address">Address:</form:label>
            <form:input path="address" />
        </div>
        <div>
            <input type="submit" value="Update" />
        </div>
    </form:form>
</body>
</html>