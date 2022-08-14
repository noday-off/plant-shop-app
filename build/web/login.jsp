<%-- 
    Document   : login
    Created on : Jun 15, 2022, 2:12:18 PM
    Author     : Tri Thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/mycss.css">
        <style>
            *{
                font-size: 25px;
                padding: 15px;
                margin: 10px;
                margin-left: 5px;
            }
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
        </style>
    </head>
    <body>
        <% 
            String msg = (String) request.getAttribute("msg");
            String warning = (String) request.getAttribute("warning");
        %>
        <form action="MainController" method="POST">
            Email<br>
            <input type="text" name="txtEmail" placeholder="Your email" required><br>
            Password<br>
            <input type="password" name="txtPassword" placeholder="Your password" required><br>
            <input class="button" type="submit" name="action" value="Login"><br>
            <a href="registration.jsp">Register</a>
        </form>
        <h4 style="color:red"><%=(warning != null)?warning:""%></h4>
        <h4 style="color:green"><%=(msg != null)?msg:""%></h4>
    </body>
</html>
