<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: romha
  Date: 11/30/2019
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- based on: https://bootsnipp.com/snippets/z8aQr -->
<html>
<head>
    <link href="<c:url value="/resources/login_style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/bootstrap/dist/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/global.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/bootstrap/dist/js/bootstrap.js" />"></script>
    <title>Title</title>
</head>
<body>
<div class="container login-container">
    <div class="row">
        <div class="col-md-6 login-form-1">
            <h3>Login</h3>
            <form method="post" action="http://localhost:8090/account/login">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="username" value="" name="username" id="username" />
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="password" value="" name="password" id="password" />
                </div>
                <div class="form-group">
                    <input type="submit" class="btnSubmit" value="Login" />
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
