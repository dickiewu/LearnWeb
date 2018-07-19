<%--@elvariable id="ticket" type="name.wuxiaodong01.domain.Ticket"--%>
<%--
  Created by IntelliJ IDEA.
  User: dickie
  Date: 18-7-9
  Time: 上午11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ViewTicket</title>
</head>
<body>

    <a href="login?logout">Logout</a>
    <h1>Ticket #${ticketId}:  ${ticket.subject}</h1><br/>
    Customer Name: ${ticket.customerName} <br/><br/>
    ${ticket.body}

    Attachments:

    <c:forEach items="${ticket.attachments}" var="attachment">
        <c:url var="downloadUrl" value="tickets">
            <c:param name="action">download</c:param>
            <c:param name="ticketId">${ticketId}</c:param>
            <c:param name="attachment">${attachment.name}</c:param>
        </c:url>
        <a href="${downloadUrl}">${attachment.name}}</a>
    </c:forEach>

    <c:remove var="downloadUrl" scope="page"/>
</body>
</html>
