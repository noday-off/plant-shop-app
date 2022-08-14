<%-- 
    Document   : registration
    Created on : Jun 15, 2022, 2:13:28 PM
    Author     : Tri Thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <link rel="stylesheet" href="css/mycss.css">
        <style>
            *{
                font-size: 25px;
                padding: 15px;
                margin: 10px;
                margin-left: 5px;
            }
        </style>
    </head>
    <body>
        <%
            String error = (String) session.getAttribute("ERROR");
            String txtEmail = (String) session.getAttribute("txtEmail");
            String txtFullname = (String) session.getAttribute("txtFullname");
            
            if (txtEmail == null )  txtEmail = "";
            if(txtFullname == null) txtFullname = "";
            if(error == null){
                error = "";
            }
        %>
        <h1>Registration form</h1>
        <form action="registerServlet" method="POST">
            <h1>Register</h1>
            <table border="1">
                <tbody>
                    <tr><td>Email</td><td><input type="email" name="txtEmail" value="<%= txtEmail%>" required></td></tr>
                    <tr><td>Full name</td><td><input type="text" name="txtFullname" value ="<%= txtFullname%>" required></td></tr>
                    <tr><td>Password</td><td><input type="password" name="txtPassword" required></td></tr>
                    <tr><td>Phone</td><td><input type="text" name="txtPhone" required></td></tr>
                    <tr><td colspan="2"><input type="submit" value="Register" ></td></tr>
                </tbody>
            </table>
            
        </form>
    </body>
</html>
