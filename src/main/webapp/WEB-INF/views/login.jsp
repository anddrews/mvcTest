
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<p>${error}</p>
<form name="loginForm" action="login" method="post">
  <p>User</p>
  <input name="user" type="text">
  <p>Password</p>
  <input name="password" type="text">
  <input type="submit">
</form>

</body>
</html>
