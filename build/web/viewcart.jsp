<%-- 
    Document   : viewcart
    Created on : Jun 19, 2022, 7:11:47 PM
    Author     : Tri Thinh
--%>

<%@page import="dto.Account"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.PlantDAO"%>
<%@page import="dto.Plant"%>
<%@page import="dto.Plant"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
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
        <%@include file = "header.jsp" %>
        <%
            HashMap<Integer, Integer> cart = (HashMap) session.getAttribute("CART");
            Account loginUser = (Account)session.getAttribute("LOGIN_USER");
            String email = null;
            if(loginUser != null){
            email = loginUser.getEmail();
        %>
        <h3>Welcome <%= loginUser.getFullname()%></h3>
        <a href="MainController?action=Logout"><input class="button" type="button" value="Log out"></a>
        <a href="personalPage.jsp"><input class="button" type="button" value="Personal page"></a>
        <%
            }
            String msg = (String) request.getAttribute("msg");
            String warning = (String) request.getAttribute("warning");
        %>
        <h4 style="color:red"><%=(warning != null)?warning:""%></h4>
        <h4 style="color:green"><%=(msg != null)?msg:""%></h4>
        <%
            if(cart != null && !cart.isEmpty()){         
        %>
        <table class="shopping">
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th></th>
                    <th></th>
                </tr>
        <%
            int total = 0;
            for (int pid : cart.keySet()) {
                Plant p = PlantDAO.getPlant(pid);
                if(p != null){
                    total += p.getPrice()*cart.get(pid);
        %>
        <tr>
        <form action="MainController" method="POST">
            <td><input type="text" name="pid" value="<%= pid%>" readonly></td>   
            <td><img src="<%=p.getImgpath()%>" style="width: 50px" class="planting"></td>
            <td><input type="text" name="price" value="<%= p.getPrice()%>" readonly></td>
            <td><input type="number" min="1" name="quantity" value="<%= cart.get(pid)%>"></td>
            <td><input class="button" type="submit" name="action" value="Update"></td>
            <td><input class="button" type="submit" name="action" value="Delete"></td>
        </form>
        </tr>
        <% }} %>
        </table>
        <h3>Total : <%=total%> 000 VND</h3>
        <a href="MainController?action=SaveOrder&email=<%=email%>"><input class="button" type="button" value="Save order"></a>
        <% }else{
                out.println("Your cart is empty!");
            }
        %>
        <div style="height:100px"></div>
        <%@include file="footer.jsp"%>
    </body>
</html>
