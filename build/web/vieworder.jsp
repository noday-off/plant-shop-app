<%-- 
    Document   : vieworder
    Created on : Jul 18, 2022, 11:44:01 AM
    Author     : Tri Thinh
--%>

<%@page import="dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/mycss.css">
        <style>
            header{
                height: 60px;
                background-color: lightgreen;
            }
            ul{
                list-style-type: none;
                width:100%;
            }
            table *{
                margin: 5px;
            }
            table thead{
                background:lightblue;
            }
            header>nav>ul>li{
                color: black;
                margin-top: 9px;
                padding-right: 20px;
                float:left;
            }
            a{
                text-decoration: none;
                color: black;
            }
            a:visited{
                color:black;
            }
            .button{
                background-color: black;
                color:white;
                border: none;
                box-shadow: 1px 1px 1px #000;
            }
            footer{
                position: fixed;
                left: 0;
                bottom: 0;
                width: 100%;
                height:50px;
                background-color:lightgreen;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <%@include file="header_loginedUser.jsp"%>
        <%
            Account loginUser = (Account) session.getAttribute("LOGIN_USER");
            String msg = (String) request.getAttribute("msg");
            String warning = (String) request.getAttribute("warning");
        %>
        <form action="MainController" method="POST">
            By status: <select name="status">
                <option value="0">All</option>
                <option value="1">Processing</option>
                <option value="2">Completed</option>
                <option value="3">Canceled</option>
            </select>
            <input class="button" type="submit" name="action" value="ViewOrder">
        </form>
        <h4 style="color:red"><%=(warning != null) ? warning : ""%></h4>
        <h4 style="color:green"><%=(msg != null) ? msg : ""%></h4>
        <%
            if (loginUser != null) {
                ArrayList<Order> list = (ArrayList<Order>) session.getAttribute("ORDER_LIST");
                if (list != null && !list.isEmpty()) {
                    String[] ordStatus = {"-", "processing", "completed", "canceled"};
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Ship Date</th>
                    <th>Status</th>
                    <th>action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Order order : list) {
                %>
                <tr>
                    <td><a href="MainController?action=ViewOrderDetails&orderId=<%=order.getOrderID()%>"><%=order.getOrderID()%></a></td>
                    <td><%=order.getOrderDate()%></td>
                    <td><%=order.getShipDate()%></td>
                    <td><%=ordStatus[order.getStatus()]%></td>
                    <%
                        if (order.getStatus() == 1) {
                    %>
                    <td>
                        <a href="MainController?action=CancelOrder&orderId=<%=order.getOrderID()%>"><input class="button" type="button" value="Cancel"></a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
        <%} else {
                    out.println("There no solution returned.");
                }
            } else {
                out.println("You must log in to view your order list");
            }%>
        <div style="height:100px"></div>
        <%@include file="footer.jsp"%>
    </body>
</html>
