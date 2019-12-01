<%--
  Created by IntelliJ IDEA.
  User: romha
  Date: 11/30/2019
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Welcome to SportsBetting</title>
    <link href="<c:url value="/resources/bootstrap/dist/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/global.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/bootstrap/dist/js/bootstrap.js" />"></script>
    <jsp:include page="common/header.jsp" />
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item active" aria-current="page">Home</li>
        </ol>
    </nav>
</head>
<body>
<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">Welcome to SportsBet</h1>
        <p class="lead">Sports betting is the activity of cheating on sports results and placing a wager on the outcome.</p>
    </div>
</div>
</body>
</html>
