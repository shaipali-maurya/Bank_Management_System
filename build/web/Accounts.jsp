<%-- 
    Document   : Accounts
    Created on : 19 Aug 2025, 4:48:07 pm
    Author     : shaipali maurya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="BankServlet.*, java.sql.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounts</title>
        <link rel="stylesheet" href="static/style.css">
    </head>
    <body>
        <%
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String email = request.getParameter("email");
                String pass = request.getParameter("pass");

                if (email == null || pass == null) {
                    out.print("<h4 class='text-danger'>All Fields Required</h4>");
                    response.sendRedirect("Accounts.jsp");
                    return;
                }

                int status = TellerDao.createTeller(email, pass);

                if (status > 0) {
                    out.print("<script>alert('Successfully created')</script>");
                } else {
                    out.print("<script>alert('Not created')</script>");
                    response.sendRedirect("Accounts.jsp");
                    return;
                }
            }
        %>
        <jsp:include page="nav.jsp"/>
        <div class="account-main col-8 offset-2 mt-5">
            <div class="accounts-top">
                <h4>All Staff Accounts</h4>
                <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Add New Account
                </button>
            </div>
            <div class="accounts-hero">
                <table class="table table-bordered table-hover">
                    <tr>
                        <th>Email</th>
                        <th>Password</th>
                        <th>Account Type</th>
                        <th></th>
                    </tr>

                    <%
                        try {
                            ResultSet rs = TellerDao.read();

                            while (rs.next()) {
                                out.print("<tr>");
                                out.print("<td>" + rs.getString(1) + "</td>");
                                out.print("<td>" + rs.getString(2) + "</td>");
                                out.print("<td>" + rs.getString(3) + "</td>");
                                out.print("<td><a href='DeleteTeller.jsp?email="+rs.getString(1)+"'>Delete</a></td>");
                                out.print("</tr>");

                            }

                        } catch (Exception ex) {
                            System.out.print(ex);
                        }
                    %>  

                </table>
            </div>
            <div class="accounts-bottom">
                <h6>My Bank</h6>
            </div>
        </div>




        <!-- Modal -->
        <form method="post">
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">New Cashier Account</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">

                            </button>
                        </div>
                        <div class="modal-body">

                            <div class="form-group mb-3">
                                <label for="email">Email</label>
                                <input type="email" name="email" id="email" placeholder="email" class="form-control" required>
                            </div>

                            <div class="form-group mb-3">
                                <label for="pass">Password</label>
                                <input type="password" name="pass" id="pass" placeholder="password" class="form-control" required>
                            </div>


                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <input type="submit" class="btn btn-primary" value="SavaChanges">
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
