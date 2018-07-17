<%--
  Created by IntelliJ IDEA.
  User: dickie
  Date: 18-7-10
  Time: 下午4:26
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="loginSuccess" type="java.lang.Boolean"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
    <h1>Log in</h1>


    <c:if test="${! empty loginSuccess}">
        <c:if test="${! loginSuccess}">
            username or password error!!!!
        </c:if>
    </c:if>
    <form action="login" method="post" enctype="application/x-www-form-urlencoded">
        username: <input type="text" name="username"><br/>
        password: <input type="password" name="password" /><br>
        <input type="submit" value="Log in" />
    </form>
</body>
</html>
