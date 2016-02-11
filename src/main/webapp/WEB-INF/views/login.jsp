<%@ page import="by.gsu.epamlab.constants.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<p>${error}</p>
<form name="loginForm" action="loginPage/login" method="post">
  <p>User</p>
  <input name="<%=Constants.USER%>" type="text">
  <p>Password</p>
  <input name="<%=Constants.PASSWORD%>" type="password">
  <input name="page" type="hidden" value="${page}">
  <input name="action" type="hidden" value="login">
  <input type="submit">
</form>

</body>
</html>
