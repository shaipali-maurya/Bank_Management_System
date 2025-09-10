<%-- 
    Document   : manager
    Created on : 5 Aug 2025, 4:48:05 pm
    Author     : shaipali maurya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager</title>
    </head>
    <body>
        <%
            String utype, email;
            utype = (String) session.getAttribute("utype");
            email = (String) session.getAttribute("email");

            if (utype == null || email == null) {
                response.sendRedirect("Login");
                return;
            }

            if (!(utype.equals("manager"))) {
                response.sendRedirect("Login");
            }
        %>
        <jsp:include page="AllUsers.jsp"/>
    </body>
</html>
