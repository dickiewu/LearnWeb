<%@ page import="name.wuxiaodong01.domain.Ticket" %>
<%@ page import="name.wuxiaodong01.domain.Attachment" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %><%--
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
    <%
        Ticket ticket = ((Ticket) request.getAttribute("ticket"));
        String ticketId = (String) request.getAttribute("ticketId");
    %>
    <a href="login?logout">Logout</a>
    <h1>Ticket #<%= request.getAttribute("ticketId")%>:  <%= ticket.getSubject()%></h1><br/>
    Customer Name: <%=ticket.getCustomerName()%> <br/><br/>
    <%=ticket.getBody()%>

    Attachments:
    <%
        Collection<Attachment> attachments = ticket.getAttachments();
        Iterator<Attachment> iterator = attachments.iterator();
        while(iterator.hasNext()){
            Attachment attachment = iterator.next();
    %>
        <a href="/learnweb/ticketServlet?action=download&ticketId=<%=ticketId%>&attachment=<%=attachment.getName()%>"><%=attachment.getName()%></a>
    <%
        }
    %>
</body>
</html>
