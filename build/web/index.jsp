<%-- 
    Document   : index
    Created on : Jun 15, 2022, 2:09:54 PM
    Author     : Tri Thinh
--%>

<%@page import="dao.PlantDAO"%>
<%@page import="dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
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
        <%@include file="header.jsp"%>
        <section>
            <div style="clear:both"></div>
            <%            String notification = (String) session.getAttribute("NOTIFICATION");
                if (notification != null && !notification.isEmpty()) {%>
            <h4><%= notification%></h4>
            <%
                    session.setAttribute("NOTIFICATION", null);
                }
                String msg = (String) request.getAttribute("msg");
                if (msg == null) {
                    msg = "";
                }
            %>
            <h4 style="color:green"><%=msg%></h4>
            <%

                ArrayList<Plant> list;
                list = (ArrayList<Plant>) session.getAttribute("PLANT_LIST");

                String[] tmp = {"out of stock", "available"};

                if (list != null && !list.isEmpty()) {
                    int total = 0;
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>Image </th>
                        <th>ID: </th>
                        <th>Name: </th>
                        <th>Price: </th>
                        <th>Status: </th>
                        <th>Category: </th>
                        <th colspan="2"></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Plant p : list) {
                    %>
                    <tr>
                        <td><img src="<%=p.getImgpath()%>" style="width:100px" class="planting"></td>
                        <td><%= p.getId()%></td>
                        <td><%= p.getName()%></td>
                        <td><%= p.getPrice()%></td>
                        <td><%= tmp[p.getStatus()]%></td>
                        <td><%= p.getCatename()%></td>
                        <% if (p.getStatus() == 1) {
                        %>
                        <td><a href="MainController?action=Addtocart&pid=<%=p.getId()%>" style="text-decoration: none"><input type="button" class="button" value="Add"></a></td>
                                <%}%>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <%
                }
            %>

        </section>
            <div style="height:100px"></div>
        <%@include file="footer.jsp"%>
    </body>
</html>
