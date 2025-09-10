<%-- 
    Document   : DeleteTeller
    Created on : 20 Aug 2025, 5:27:13 pm
    Author     : shaipali maurya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="BankServlet.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Teller</title>
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

                String tellerEmail = request.getParameter("email");
                try {
                    TellerDao.delete(tellerEmail);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                response.sendRedirect("Accounts.jsp");
           

        %>
    </body>
</html>
