<%@ page import="com.example.assignment2.RetrieveItems" %>
<%@ page import="com.example.assignment2.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/6/2022
  Time: 11:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="ViewCart"><button>View Cart</button></form>
<form action="Checkout" method="post"><button>Checkout</button></form>
<form action="LogoutServlet" method="get"><button>Logout</button></form>

<%
    RetrieveItems retrieveItems = new RetrieveItems();
    List<Item> itemList = retrieveItems.getItemsFromDatabase();
    Item item = new Item();

    for (int i = 0; i < itemList.size(); i++)
    {
        item = itemList.get(i);
%>
<form action="ViewItems" method="get">
    <%=item.getItemName()%><br>
    <img src="<%=request.getContextPath()%><%=item.getItemImage()%>" width="100"/><br><br>
    <button name="item" value="<%=item.getItemID()%>">Add to Cart</button>
</form>
<% } %>
</body>
</html>
