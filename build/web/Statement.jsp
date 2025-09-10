<%-- 
    Document   : Statement
    Created on : 4 Sept 2025, 4:51:34 pm
    Author     : shaipali maurya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.sql.*"%>
<%@page  import="BankServlet.UserDao"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statement</title>
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

            if (!(utype.equals("user"))) {
                response.sendRedirect("Login");
            }
        %>
        <jsp:include page="nav.jsp"/>
        <div class="row mt-5">
            <div class="col-10 offset-1">
                <h2 class="text-center bg-gray">Statement</h2>
                <%
                    try {
                    
                    ResultSet statement = UserDao.statement(email);
                    int index = 1;

                    out.print("<table class='table table-bordered'>");
                    
                    out.print("<tr>");
                    out.print("<th>#</th>");
                    out.print("<th>Check No</th>");
                    out.print("<th>Amount</th>");
                    out.print("<th>Transcation Type</th>");
                    out.print("<th>Date</th>");

                    out.print("</tr>");

                    while (statement.next()) {
                             out.print("<tr>");
                             out.print("<td>"+index+"</td>");
                             out.print("<td>"+statement.getLong(2)+"</td>");
                             out.print("<td>"+statement.getLong(3)+"</td>");
                             out.print("<td>"+statement.getString(4)+"</td>");
                             out.print("<td>"+statement.getDate(5)+"</td>");
                             out.print("</tr>");
                        index++;

                    }

                    out.print("</table>");
                    } catch(Exception ex) {
                    System.out.println(ex);
                    }
                %>
            </div>
        </div>
    </body>
</html>
