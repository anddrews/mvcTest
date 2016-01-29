<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <jsp:body>
    <p>About page</p>
    <h3>${performance.name}</h3>
    <p>${performance.description}</p>

    <jsp:include page="zale.jsp"/>


  </jsp:body>


</t:layout>
