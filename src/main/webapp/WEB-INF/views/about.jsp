<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <jsp:body>
    <c:if test="${user.role eq 'USER'}">
      <form name="buttons" action="layoutAction" method="post">
        <input type="submit" value="Booking places">
      </form>
    </c:if>
    <p>About page</p>
    <h3>${play.name}</h3>
    <p>${play.description}</p>
    <jsp:include page="zale.jsp"/>


  </jsp:body>


</t:layout>
