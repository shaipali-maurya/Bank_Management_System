<%-- 
    Document   : Cashier
    Created on : 21 Aug 2025, 5:38:51 pm
    Author     : shaipali maurya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, BankServlet.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cashier</title>
        <link href="static/style.css" rel="stylesheet" type="text/css"/>
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

            if (!(utype.equalsIgnoreCase("cashier"))) {
                response.sendRedirect("Login");
            }


        %>
        <jsp:include page="nav.jsp"/>

        <div class="account-main col-8 offset-2 mt-5">
            <div class="accounts-top">
                <h5 class="text-center">Account Information</h5>
            </div>
            <div class="accounts-hero">
                <div class="p-3">
                    <form class="col-8 offset-2 my-3" method="post" action="Cashier.jsp">
                        <h4 class="text-center py-2">Enter account no.</h4>
                        <input type="text" name="account_no" class="form-control" required>
                        <div class="text-center my-3">
                            <input type="submit" value="Get Account Info" class="btn btn-primary">
                        </div>
                    </form>
                </div>
                <div class="row p-3">     
                    <%                        
                        long account_no = 0;
                        ResultSet rs = null;

                        if ("POST".equalsIgnoreCase(request.getMethod())) {
                            account_no = Long.parseLong(request.getParameter("account_no"));

                            try {
                                rs = TellerDao.getDetails(account_no);

                                if (rs.next()) {
                                    out.print("<div class='col-6'>");
                                    out.print("<p class='text-center'>Account no.</p>");
                                    out.print("<input type='text' class='form-control' value='" + rs.getLong(2) + "' disabled>");
                                    out.print("<p class='text-center'>Account Holder Name</p>");
                                    out.print("<input type='text' class='form-control' value='" + rs.getString(1) + "' disabled>");
                                    out.print("<p class='text-center'>Bank Balence</p>");
                                    out.print("<input type='text' class='form-control' value='" + rs.getLong(4) + "' disabled>");
                                    out.print("</div>");

                                    out.print("<div class='col-6 gap-3'>");
                                    out.print("<form method='post' action='Withdraw'>");
                                    out.print("<p class='text-center'>Tranaction Process.</p>");
                                    out.print("<input type='hidden' class='form-control'  name='account_no' value='" + account_no + "'>");
                                    out.print("<input type='text' class='form-control' name='checkNo' placeholder='Write Check No'>");
                                    out.print("<input type='text' class='form-control' name='amount' placeholder='Enter Amount to Withdraw'>");
                                    out.print("<div class='text-center'><input type='submit' class='btn btn-primary' value='Withdraw'></div>");
                                    out.print("</form>");

                                    out.print("<form method='post' action='Deposite'>");
                                    out.print("<input type='hidden' class='form-control' name='account_no' value='" + account_no + "'>");
                                    out.print("<input type='text' class='form-control' name='checkNo' placeholder='Write Check No'>");
                                    out.print("<input type='text' class='form-control' name='amount' placeholder='Write Amount for deposit'>");
                                    out.print("<div class='text-center'><input type='submit' class='btn btn-success' value='Deposite'></div>");
                                    out.print("</form>");

                                    out.print("</div>");
                                }
                            } catch (Exception ex) {
                                System.out.println(ex);

                            }
                        }
                    %>
                </div>
            </div>
            <div class="accounts-bottom">
                <h6 class="text-center">My Bank</h6>
            </div>
        </div>
    </body>
</html>
