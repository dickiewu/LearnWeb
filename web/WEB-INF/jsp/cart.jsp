<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: dickie
  Date: 18-7-10
  Time: 下午12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<Integer,Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
    Map<Integer,String> products = (Map<Integer, String>) request.getAttribute("products");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Cart</h1>
    <a href="storeServlet?action=browse">Product List</a><br/><br/>
    <%
        if(cart.isEmpty()){
    %>
            No　Products!!!
    <%
        }else{
    %>
            <a href="storeServlet?action=emptyCart">Empty Cart</a>

    <%
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                Integer productId = entry.getKey();
                Integer productNum = entry.getValue();
    %>
                <%=products.get(productId)%>(num: <%=productNum%>) <br/>
    <%
            }
        }
    %>
</body>
</html>
