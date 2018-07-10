<%--
  Created by IntelliJ IDEA.
  User: dickie
  Date: 18-7-9
  Time: 上午11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show FOrm</title>
</head>
<body>
    <h1>Create a Ticket!!</h1>
    <a href="login?logout">Logout</a>
    <form action="/learnweb/ticketServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="create">
        主题: <input type="text" name="subject" /><br/><br/>
        内容:
        <textarea name="body" cols="30" rows="6" ></textarea><br/><br/>
        附件: <input type="file" name="file1"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
