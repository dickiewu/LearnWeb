<%--
  Created by IntelliJ IDEA.
  User: dickie
  Date: 18-7-8
  Time: 下午6:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Hello jsp</title>
    <meta charset="UTF-8">
</head>
<body>
    <form action="helloJsp.jsp" enctype="application/x-www-form-urlencoded">
        <input type="checkbox" name="hobbies" value="basketball"> 篮球<br/>
        <input type="checkbox" name="hobbies" value="football"> 足球<br/>
        <input type="checkbox" name="hobbies" value="hiking"> 骑行 <br/>
        <input type="submit" value="Submit">
    </form>

   <%-- <%
        String[] hobbies = request.getParameterValues("hobbies");
        if(hobbies!=null && hobbies.length>0){
    %>
            <ul>
    <%
            for (String hobby : hobbies) {
    %>
                <li><%=hobby%></li>
    <%
            }
        }
    %>
            </ul>--%>
</body>
</html>