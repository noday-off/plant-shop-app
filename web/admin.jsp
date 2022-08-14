<%-- 
    Document   : welcome
    Created on : Jun 15, 2022, 2:44:34 PM
    Author     : Tri Thinh
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <%
            Account LoginUser = (Account)session.getAttribute("LOGIN_USER");
            if(LoginUser.getRole() != 1){
                response.sendRedirect("login.jsp");
            }
        %>
        <h1> Welcome <%=LoginUser.getFullname()%></h1>
        <%

                ArrayList<Account> list;
                list = (ArrayList<Account>) session.getAttribute("ACC_LIST");

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
    </body>
</html>
