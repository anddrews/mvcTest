<%@ tag import="by.gsu.epamlab.constants.Constants" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
</head>

<body>

<div class="header">
    <c:choose>
        <c:when test="${empty user}">
            <p style="display: inline-block">Users: Hello guest</p>
            <a href="<%=Constants.LOGIN_PAGE%>">Login</a>
            <a href="<%=Constants.CREATE_USER_PAGE%>">Registration</a>
        </c:when>

        <c:otherwise>
            <p style="display: inline-block">Users: ${user.userName}</p>
            <form name="for" action="logout" method="post">
                <a href="JavaScript:document.for.submit()">Logout</a>
            </form>
        </c:otherwise>
    </c:choose>

</div>
<div class="wrapper">
<c:if test="${not empty user}">

    <t:if test="${user.role eq 'ADMIN'}">
        <form name="buttons" action="layoutAction" method="post">
            <input type="submit" value="Upload Repertoire">
        </form>
    </t:if>
    <t:if test="${user.role eq 'USER'}">
        <form name="buttons" action="layoutAction" method="post">
            <input type="submit" value="Booking places">
        </form>
    </t:if>

</c:if>
    <jsp:doBody/>
</div>
<footer>
    <p> Developed by Kondratenko e-mail:anddrews@tut.by</p>
</footer>


</body>
</html>
