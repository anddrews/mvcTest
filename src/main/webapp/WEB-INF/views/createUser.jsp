<%@ page import="by.gsu.epamlab.constants.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  ${error}
  <form name="createUser" action="create" method="post">
    <p>Login</p>
    <input type="text" name="<%=Constants.USER%>">
    <p>Password</p>
    <input type="text" name="<%=Constants.PASSWORD%>">
    <p>Confirm password</p>
    <input type="text" name="<%=Constants.PASSWORD_SEC%>">
    <input type="submit">
  </form>
</body>
</html>
