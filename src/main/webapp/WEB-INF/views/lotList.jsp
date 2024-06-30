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
	<h2>Lot List</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Lot Number</th>
                <th>Quantity</th>
                <th>Unit of Measure</th>
                <th>Date</th>
                <th>Expired Date</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="lot" items="${lotList}">
                <tr>
                    <td>${lot.id}</td>
                    <td>${lot.lotNo}</td>
                    <td>${lot.quantity}</td>
                    <td>${lot.uom}</td>
                    <td>${lot.date}</td>
                    <td>${lot.expired_date}</td>
                    <td>${lot.price}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/lot/editlot/${lot.id}">Edit</a>
                        <a href="${pageContext.request.contextPath}/lot/deletelot/${lot.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>