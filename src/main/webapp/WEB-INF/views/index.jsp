
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<t:layout>
    <jsp:body>
        <p>Home page</p>
        <c:forEach var="performance"  items="${repertoire}">

                <p>${performance.name}
                    <c:forEach var="date" items="${performance.date}">

                        <jsp:useBean id="beanNow" class="java.util.Date" />

                        <a href="/mvcTest/about?id=${performance.id}" style="padding: 5px;">
                            <fmt:formatDate value="${date}" dateStyle="short"/>
                        </a>
                    </c:forEach>
                </p>
        </c:forEach>




    </jsp:body>


</t:layout>
