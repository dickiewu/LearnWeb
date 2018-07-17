<%@ page import="com.google.common.collect.Lists" %>
<%@ page import="name.wuxiaodong01.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.common.collect.Maps" %>
<%@ page import="com.google.common.collect.ImmutableMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%--@elvariable id="user" type="name.wuxiaodong01.domain.User"--%>
<%--
  Created by IntelliJ IDEA.
  User: dickie
  Date: 18-7-10
  Time: 下午9:42
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%
    List<User> users = Lists.newArrayList();
    for (int i = 0; i < 10; i++) {
        users.add(new User(i,"wxd-"+i,"tom","tom"));
    }
    request.setAttribute("users",users);

    Map<Integer,String> map = new HashMap<>();
    map.put(1,"aaa1");
    map.put(2,"aaa2");
    map.put(3,"aaa3");
    map.put(4,"aaa4");
    map.put(5,"aaa5");
    map.put(6,"aaa6");
    request.setAttribute("usersMap",map);

%>

    <c:forEach items="${users}" var="user" varStatus="status" step="2">
        ${user.userId}---${user.username}   ${"       begin:"+=status.begin+=", "+="end:"+=status.end+=",step:"+=status.step+=", index:"+=status.index+=", count:"
        +=status.count+=",first:"+=status.first+=", last:"+=status.last}<br/>
    </c:forEach>

    <c:catch>
        <%! private String name = "sss";%>
    </c:catch>
</body>
</html>
