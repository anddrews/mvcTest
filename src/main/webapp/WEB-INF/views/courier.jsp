<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<t:layout>
  <jsp:body>

    <form name="criteria" action="courier" method="post" class="criteria">
      <p>User</p>
      <input type="text" name="user" >
      <br>
      <p>Play</p>
      <input type="text" name="play">
      <br>
      <input type="submit" value="Create">
    </form>



    <table class="report">
      <th>made</th>
      <th>date</th>
      <th>Play name</th>
      <th>user</th>
      <th>row</th>
      <th>place</th>
      <th>price</th>
      <th>status</th>
      <c:forEach var="row" items="${report}">
        <tr>
          <td><input type="checkbox" value="${row.id}"/></td>
          <td><fmt:formatDate value="${row.date}" type="date" dateStyle="short"/> </td>
          <td>${row.playName}</td>
          <td>${row.user}</td>
          <td>${row.row}</td>
          <td>${row.place}</td>
          <td>${row.price}</td>
          <td>${row.status}</td>
        </tr>
      </c:forEach>
    </table>


  </jsp:body>


</t:layout>