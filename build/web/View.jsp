<%-- 
    Document   : View
    Created on : 13 Aug 2025, 4:21:58 pm
    Author     : shaipali maurya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="BankServlet.Dao"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View</title>
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

        <jsp:include page="nav.jsp"/>
        <div class="container">
        <%
            long account_no = 0;
            account_no = Long.parseLong(request.getParameter("account_no"));

            if (!(account_no == 0)) {
                ResultSet rs = Dao.getDetails(account_no);

                out.print("<table class='table table-bordered mt-5 p-3'>");
               

                if (rs.next()) {
                    out.print("<tr>");
                    out.print("<th>Name</th>");
                    out.print("<td>" + rs.getString(1) + "</td>");
                    out.print("<th>Account No</th>");
                    out.print("<td>" + account_no + "</td>");
                    
                      out.print("</tr><tr>");
                      
                    out.print("<th>Branch</th>");
                    out.print("<td>" + rs.getString(3) + "</td>");
                    
                    out.print("<th>Balance</th>");
                    out.print("<td>" + rs.getLong(4) + "</td>");
                    
                      out.print("</tr><tr>");
                      
                    out.print("<th>Account Type</th>");
                    out.print("<td>" + rs.getString(5) + "</td>");
                    out.print("<th>Contact</th>");
                    out.print("<td>" + rs.getLong(6) + "</td>");
                    out.print("</tr>");
                }
            }
        %>
        </div>
    </body>
</html>
