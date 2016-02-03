<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<t:layout>
    <jsp:body>
        <p>Home page</p>
        <c:forEach var="play"  items="${repertoire}">

                <p>${play.name}
                    <c:forEach var="date" items="${play.date}">

                        <a  href="/mvcTest/about?id=${play.id}&data=${date.time}" style="padding: 5px;">
                           <fmt:formatDate value="${date}" type="date" dateStyle="short" timeStyle="short"/>
                        </a>
                    </c:forEach>

                </p>
        </c:forEach>

    </jsp:body>


</t:layout>
