<%@ tag import="by.gsu.epamlab.constants.Constants" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet"  type="text/css" href="css/main.css">
    <script type="text/javascript" src="js/jquery-1.7.2.min.js" ></script>
    <!--<script type="text/javascript" src=" http://code.jquery.com/jquery-1.11.2.js "></script>-->


</head>

<body>

<div class="header">
    <c:choose>
        <c:when test="${empty user}">
            <p style="display: inline-block">Hello guest</p>
            <a href="${pageContext.request.contextPath}<%=Constants.LOGIN_PAGE%>">Login</a>
            <a href="${pageContext.request.contextPath}<%=Constants.CREATE_USER_PAGE%>">Registration</a>
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
        <div class="courier">
            <c:if test="${user.idRole < 2}">
                    <a href="${pageContext.request.contextPath}<%=Constants.COURIER_PAGE%>">Reports</a>

                <div class="admin">
                    <c:if test="${user.idRole < 1}">
                        <form name="file_load" action="uploadrepertoire" method="post" enctype="multipart/form-data" >
                            <input type="file" size="20" name="file" >
                            <input type="submit" value="Upload repertoire"/>
                        </form>
                    </c:if>
                </div>
            </c:if>
        </div>
    </c:if>
    <jsp:doBody/>
</div>
<footer>
    <a href="${pageContext.request.contextPath}/">На главную</a>
    <p> Developed by Kondratenko e-mail:anddrews@tut.by</p>
</footer>


</body>
</html>
