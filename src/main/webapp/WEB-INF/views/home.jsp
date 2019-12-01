<%--
  Created by IntelliJ IDEA.
  User: romha
  Date: 11/28/2019
  Time: 00:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <link href="<c:url value="/resources/bootstrap/dist/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/global.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/bootstrap/dist/js/bootstrap.js" />"></script>
    <title>Home</title>
    <jsp:include page="common/header.jsp" />

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item active" ><a href="../home">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Player</li>
        </ol>
    </nav>
</head>
<body>
<div class="presenter">
    <div class="card">
    <div class="card-header">
        Account details
    </div>
    <div class="card-body">
        <form class='form-horizontal' method="post" action="http://localhost:8090/home/add">
            <div class="form-group">
                <input type="hidden" class="form-control" name="id" id="id" value="${user.getId()}">
            </div>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" name="name" id="name" aria-describedby="emailHelp" value="${user.getName()}">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" name="email" aria-describedby="emailHelp" value="${user.getEmail()}">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" value="${user.getPassword()}">
            </div>
            <div class="form-group">
                <label for="accountNumber">Account Number</label>
                <input type="number" class="form-control" id="accountNumber" name="accountNumber"  value="${user.getAccountNumber()}">
            </div>
            <div class="form-group">
                <label for="birth">Birth</label>
                <input type="date" class="form-control" id="birth" name="birth" value="${user.getBirth()}">
            </div>
            <div class="form-group">
                <label for="balance">Balance</label>
                <input type="number" class="form-control" id="balance" name="balance" value="${user.getBalance()}">
            </div>
            <div class="form-group">
                <label for="currency">Currency</label>
                <input type="text" class="form-control" id="currency" name="currency" value="${user.getCurrency()}">
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</div>
</div>
</body>
</html>