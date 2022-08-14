<%-- 
    Document   : personalPage
    Created on : Jun 17, 2022, 4:39:38 PM
    Author     : Tri Thinh
--%>

<%@page import="dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personal Page</title>
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
                color: white;
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
        <%
            Account LoginUser = (Account) session.getAttribute("LOGIN_USER");
            if (LoginUser == null) {
        %>
        <p style='color:red'>You must login to view personal page</p><br>
        <p></p>
        <%} else {
        %>
        <%@include file="header_loginedUser.jsp"%>
        <section>
            <h3>Welcome <%= LoginUser.getFullname()%></h3>
            Account ID: <%=LoginUser.getAccID()%><br>
            Email: <%=LoginUser.getEmail()%><br>
            Full name: <%=LoginUser.getFullname()%><br>
            Phone: <%=LoginUser.getPhone()%><br>
            <a href='MainController?action=Logout'><input class="button" type='button' value='Log Out'</a>
        </section>
        <div style="height:100px"></div> 
        <%@include file = "footer.jsp"%>
        <%}%>
    </body>
</html>
