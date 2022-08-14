
<header>    
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="login.jsp">Log in</a></li>
                <li><a href="registration.jsp">Registry</a></li>
                <li><a href="MainController?action=Viewcart">View cart</a></li>
                <li>
                    <%
                        String keyword = (String) request.getParameter("txtsearch");
                        if(keyword == null) keyword = "";
                    %>
                    <form action="MainController">
                        <input type="text" name="txtsearch" value="<%=keyword%>">
                        <input type="radio" name="txtsearchby" value="byname" checked>
                        <label for="txtsearchby">By name</label>
                        <input type="radio" name="txtsearchby" value="bycate">
                        <label for="txtsearchby">By category</label><br>
                        <input class="button" type="submit" name="action" value="Search">
                    </form>
                </li>
            </ul>
        </nav>
</header>

