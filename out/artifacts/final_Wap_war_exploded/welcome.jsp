<%--
  Created by IntelliJ IDEA.
  User: nege
  Date: 6/12/19
  Time: 5:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>welcome</title>
</head>
<body>
<p> You successfully logged in</p>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

%>

<form action="logout" method="post">

    <input type="submit" name="logout" value="logout">
</form>
</body>
</html>