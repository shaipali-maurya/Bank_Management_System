<%-- 
    Document   : Account
    Created on : 7 Aug 2025, 5:00:42 pm
    Author     : shaipali maurya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.sql.*"%>
<%@page  import="BankServlet.UserDao"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
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
        <div class="row mt-5">
            <div class="col-10 offset-1">
                <h2 class="text-center bg-gray">Accounts</h2>
                <%
                    ResultSet accounts = UserDao.account_data();
                    int index = 1;

                    out.print("<table class='table table-bordered'>");
                    out.print("<tr>");
                    out.print("<th>#</th>");
                    out.print("<th>Holder Name</th>");
                    out.print("<th>Account Number</th>");
                    out.print("<th>Branch Name</th>");
                    out.print("<th>Current balance</th>");
                    out.print("<th>Account Type</th>");
                    out.print("<th>Contact</th>");
                    out.print("<th></th>");
                    out.print("</tr>");

                    if (!(accounts == null)) {
                        while (accounts.next()) {

                        
                            long account_no = accounts.getLong(2);
                            
                            out.print("<tr>");
                            out.print("<td>" + index + "</td>");
                            out.print("<td>" + accounts.getString(1) + "</td>");
                            out.print("<td>" + account_no+ "</td>");
                            out.print("<td>" + accounts.getString(3) + "</td>");
                            out.print("<td>" + accounts.getLong(4) + "</td>");
                            out.print("<td>" + accounts.getString(5) + "</td>");
                            out.print("<td>" + accounts.getLong(6) + "</td>");
                            out.print("<td><a href='View.jsp?account_no="+account_no+"' class='btn btn-success'>view</a><a href='Notice?account_no="+account_no+"' class='btn btn-primary'>Send Notice</a><a href='Delete?account_no="+account_no+"' class='btn btn-danger'>Delete</a>");
                            out.print("</td>");
                            out.print("</tr>");

                            index++;

                        }
                    }
                    out.print("</table>");
                %>
            </div>
        </div>
    </body>
</html>
