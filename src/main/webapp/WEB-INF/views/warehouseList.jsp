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
	<h2>Warehouse List</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Product Code</th>
                <th>Product Name</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Date</th>
                <th>Expire Date</th>
                
                <th>Location</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="warehouse" items="${warehouseList}">
                <tr>
                    <td>${warehouse.id}</td>
                    <td>${warehouse.productCode}</td>
                    <td>${warehouse.productName}</td>
                    <td>${warehouse.description}</td>
                    <td>${warehouse.quantity}</td>
                    <td>${warehouse.date}</td>
                    <td>${warehouse.expireDate}</td>
                    <td>${warehouse.locationName}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/warehouse/editwarehouse/${warehouse.id}">Edit</a>
                        <a href="${pageContext.request.contextPath}/warehouse/deletewarehouse/${warehouse.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>