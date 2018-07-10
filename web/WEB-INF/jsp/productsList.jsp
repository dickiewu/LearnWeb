<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: dickie
  Date: 18-7-10
  Time: 上午11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Products</h1>
    <% Map<Integer,String> products = (Map<Integer, String>) pageContext.findAttribute("products");%>

    <%
        if (products.isEmpty()) {
    %>
            NO PRODUCT!!!
    <%
        }else{
            for (Map.Entry<Integer, String> entry : products.entrySet()) {
                Integer productId = entry.getKey();
                String productName = entry.getValue();
    %>
            <c:url var="url" value="storeServlet">
                <c:param name="productId"><%=productId%></c:param>
                <c:param name="action">viewCart</c:param>
            </c:url>
            <a href="${url}"><%=productName%></a><br/><br/>
    <%
            }
        }
    %>
</body>
</html>
