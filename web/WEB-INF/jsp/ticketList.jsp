<%--
  Created by IntelliJ IDEA.
  User: dickie
  Date: 18-7-9
  Time: 上午11:53
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="ticketDb" type="java.util.Map<Integr,name.wuxiaodong01.domain.Ticket>"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>TicketList</title>
</head>

    <h1>Tickets</h1><br/>
    <a href="login?logout">Logout</a>
    <a href="/learnweb/tickets?action=create">Create</a> <br/>

    <c:choose>
        <c:when test="${empty ticketDb}">
            NO TICKET!!!!!!
        </c:when>
        <c:otherwise>
            <c:forEach items="${ticketDb}" var="entry">
                <c:url value="tickets" var="viewUrl">
                    <c:param name="action">view</c:param>
                    <c:param name="ticketId">${entry.key}</c:param>
                </c:url>

                Tickets #${entry.key}: <a href="${viewUrl}">${entry.value.subject}</a>(customer:${entry.value.customerName})
            </c:forEach>
        </c:otherwise>
    </c:choose>
    <c:remove var="viewUrl" scope="page"/>
    <br/>${viewUrl}
</body>
</html>
