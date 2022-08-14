<%-- 
    Document   : vieworder
    Created on : Jul 18, 2022, 11:44:01 AM
    Author     : Tri Thinh
--%>

<%@page import="dao.PlantDAO"%>
<%@page import="dto.Plant"%>
<%@page import="java.util.HashMap"%>
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
        <h4 style="color:red"><%=(warning != null) ? warning : ""%></h4>
        <h4 style="color:green"><%=(msg != null) ? msg : ""%></h4>
        <%
            if (loginUser != null) {
                HashMap<Integer,Integer> list = (HashMap) session.getAttribute("ORDERDETAILS");
                int OrderId = Integer.parseInt(request.getParameter("orderId"));
                out.println("Order ID: "+ OrderId);
                if (list != null && !list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Plant ID</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <%  int total = 0;
                    for (int pid : list.keySet()) {
                        Plant p = PlantDAO.getPlant(pid);
                        total += p.getPrice();
                %>
                <tr>
                    <td><%=pid%></td>
                    <td><img src="<%=p.getImgpath()%>"></td>
                    <td><%=list.get(pid)%></td>
                </tr>
                <%                        
                    }
                %>
            </tbody>
        </table>
        <%
                out.println("<h4>Total: "+total+" 000 VND</h4>");
                } else {
                    out.println("There no solution returned.");
                }
            } else {
                out.println("You must log in to view your order list");
            }%>
        <div style="height:100px"></div>
        <%@include file="footer.jsp"%>
    </body>
</html>
