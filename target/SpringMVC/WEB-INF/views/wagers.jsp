<%--
  Created by IntelliJ IDEA.
  User: romha
  Date: 11/29/2019
  Time: 00:06
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
    <title>Wagers</title>
    <jsp:include page="common/header.jsp" />
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item active" ><a href="../home">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Wagers</li>
        </ol>
    </nav>
</head>
<body>

    <div class="presenter">
        <div class="card">
            <div class="card-header">
                Wagers
            </div>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Event Title</th>
                    <th scope="col">Event type</th>
                    <th scope="col">Start date</th>
                    <th scope="col">Bet type</th>
                    <th scope="col">Outcome value</th>
                    <th scope="col">Outcome odd</th>
                    <th scope="col">Wager amount</th>
                    <th scope="col">Winner</th>
                    <th scope="col">Processed</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${wagers}" var="wager">
                    <tr>
                        <form action="http://localhost:8090/wagers/delete"  method="post">
                        <td>
                            <c:choose>
                                <c:when test="${wager.isProcessed()}">
                                    <input type="hidden" value="${wager.getWagerId()}"  id="id" name="id">
                                    <button class="btn btn-primary" type="submit">Remove</button>
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                                ${wager.getEventTitle()}
                        </td>
                        <td>
                                ${wager.getEventType()}
                        </td>

                         <td>
                                ${wager.getStartDate()}
                         </td>

                        <td>
                                ${wager.getBetType()}
                        </td>

                        <td>
                                ${wager.getOutcomeValue()}
                        </td>

                        <td>
                                ${wager.getOutcomeOdd()}
                        </td>

                        <td>
                                ${wager.getWagerAmount()} ${wager.getCurrency()}
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${wager.isProcessed()}">
                                    <c:choose>
                                        <c:when test="${wager.isWinner()}">
                                            Yes
                                        </c:when>
                                        <c:otherwise>
                                            No
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${wager.isProcessed()}">
                                    Yes
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                        </td>
                        </form>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </div>

</body>
</html>
