<%-- 
    Document   : Notification
    Created on : 18 Aug 2025, 4:25:09 pm
    Author     : shaipali maurya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, BankServlet.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notification</title>
    </head>
    <body>
        <jsp:include page="nav.jsp"/>
        <div class="container">
            <div class="col-8 offset-1 p-3 mt-3">
                <%
                    String email = (String) session.getAttribute("email");
                    String utype = (String) session.getAttribute("utype");

                    if (email == null || utype == null) {
                        response.sendRedirect("Login");
                        return;
                    }
                    
                    ResultSet rs = Dao.getNotification(email);
                    
                    while(rs.next()) {
                        out.print("<p class='m-1 btn btn-warning form-control text-start'>"+rs.getString(2)+"</p>");
                    }
                %>
            </div>
        </div>
    </body>
</html>
