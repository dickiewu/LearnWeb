<%--
  Created by IntelliJ IDEA.
  User: dickie
  Date: 18-7-9
  Time: 上午11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="name.wuxiaodong01.domain.Ticket" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>

<%
    Map<Integer,Ticket> ticketDb = (Map<Integer, Ticket>) request.getAttribute("ticketDb");
%>
<html>
<head>
    <title>TicketList</title>
</head>
<body>


    <h1>Tickets</h1><br/>
    <a href="login?logout">Logout</a>
    <a href="/learnweb/ticketServlet?action=create">Create</a> <br/>
    <%
        if (ticketDb.isEmpty()) {

        }else{
            Set<Map.Entry<Integer, Ticket>> entries = ticketDb.entrySet();
            for (Map.Entry<Integer, Ticket> entry : entries) {
                int ticketId = entry.getKey();
                Ticket ticket = entry.getValue();
    %>
                Tickets #<%=ticketId%>: <a href="/learnweb/ticketServlet?action=view&ticketId=<%=ticketId%>"><%=ticket.getSubject()%></a>(customer:<%=ticket.getCustomerName()%>)
    <%
            }
        }
    %>
</body>
</html>
