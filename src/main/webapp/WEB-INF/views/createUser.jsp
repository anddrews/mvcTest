<%@ page import="by.gsu.epamlab.constants.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
  ${error}
  <form name="createUser" action="createPage/create" method="post">
    <p>Login</p>
    <input type="text" name="<%=Constants.USER%>">
    <p>Password</p>
    <input type="text" name="<%=Constants.PASSWORD%>">
    <p>Confirm password</p>
    <input type="text" name="<%=Constants.PASSWORD_SEC%>">
    <input name="page" type="hidden" value="${page}">
    <input name="action" type="hidden" value="create">
    <input type="submit">
  </form>
</body>
</html>
