<%-- 
    Document   : FundTransfer
    Created on : 4 Sept 2025, 6:01:02 pm
    Author     : shaipali maurya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, BankServlet.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fund Transfer</title>
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

            if (!(utype.equalsIgnoreCase("user"))) {
                response.sendRedirect("Login");
            }


        %>
        <jsp:include page="nav.jsp"/>

        <div class="account-main col-8 offset-2 mt-5">
            <div class="accounts-top">
                <h5 class="text-center">New Transfer</h5>
            </div>
            <div class="accounts-hero">
                
                <div class="p-3 m-5 bg-success">
                    <form class="col-8 offset-2 my-3" method="post" action="FundTransfer.jsp">
                        <h4 class="text-center py-2 text-white">Enter account no.</h4>
                        <input type="text" name="account_no" class="form-control" required>
                        <div class="text-center my-3">
                            <input type="submit" value="Get Account Info" class="btn btn-primary">
                        </div>
                    </form>
                </div>
                
                <div class="row p-3 m-5 bg-success">     
                    <%                       
                        long account_no = 0;
                        ResultSet rs = null;

                        if ("POST".equalsIgnoreCase(request.getMethod())) {
                            account_no = Long.parseLong(request.getParameter("account_no"));

                            try {
                                rs = TellerDao.getDetails(account_no);

                                if (rs.next()) {
                                    out.print("<form class='col-8 offset-2 my-3' method='post' action='Transfer'>");
                                    
                                    out.print("<input type='hidden' name='email' value='"+email+"'>");
                                    
                                    out.print("<div class='form-group mb-2'>");
                                    out.print("<label for='account_no'>Account No</label>");
                                    out.print("<input type='number' value='"+account_no+"' id='account_no' name='account_no' class='form-control' readonly>");
                                    out.print("</div>");
                                    
                                     out.print("<div class='form-group mb-2 '>");
                                    out.print("<label for='name'>Account Holder Name</label>");
                                    out.print("<input type='text' value='"+rs.getString(1)+"' id='name' class='form-control' disabled>");
                                    out.print("</div>");
                                    
                                     out.print("<div class='form-group mb-2'>");
                                    out.print("<label for='branch'>Branch Name</label>");
                                    out.print("<input type='text' value='"+rs.getString(3)+"' id='branch' class='form-control' disabled>");
                                    out.print("</div>");
                                    
                                      out.print("<div class='form-group mb-2'>");
                                    out.print("<label for='amount'>Enter Amount Transfer</label>");
                                    out.print("<input type='number'  id='amount' name='amount' class='form-control'>");
                                    out.print("</div>");
                                     
                                    out.print("<div class='text-center mb-2'>");
                                    out.print("<input type='submit' value='Transfer' class='btn btn-primary'>");
                                    out.print("<div>");
                                    
                                    out.print("</form>");
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
