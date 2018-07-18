<%--
  Created by IntelliJ IDEA.
  User: dickie
  Date: 18-7-17
  Time: 下午9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
    <a href="login?logout">Logout</a><br/>
    <h1>Sessions</h1>

    There are a total of ${sessionSize} active sessions in the application <br/>
    <%--@elvariable id="allSessions" type="java.util.List<javax.servlet.http.HttpSession>"--%>
    <c:forEach items="${allSessions}" var="item">
        ${item.id}-${username}-last active ${item.lastAccessedTime}
    </c:forEach>
</body>
</html>