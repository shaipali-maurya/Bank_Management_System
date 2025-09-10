<%-- 
    Document   : Register
    Created on : 28 Jul 2025, 5:47:39 pm
    Author     : shaipali maurya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Bootstrap.html"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
          <%
            String utype, email;
            utype = (String) session.getAttribute("utype");
            email = (String) session.getAttribute("email");

            if (utype == null || email == null) {
                response.sendRedirect("Login");
            }

            if (!(utype.equals("manager"))) {
                response.sendRedirect("Login");
            }
        %>
        <jsp:include page="nav.jsp"/>
        <div class="main">
            <form method="post" action="Register">
                
                <input type="text" name="name" class="form-control" placeholder="Full Name" required>
                <input type="text" name="account_no" class="form-control" placeholder="Account number" required>
                <input type="text" name="branch" class="form-control" placeholder="Branch Name" required>
                <input type="number" min="500" name="balance" class="form-control" placeholder="balance" required>
                <select  name="account_type" class="form-control" placeholder="Account Type" required>
                    <option value="savings">Savings</option>
                    <option value="current">Current</option>
                </select>
                <input type="number" min="6666666666" max="9999999999" name="contact" class="form-control" placeholder="Contact" required>
                
                
                <input type="email" name="email" class="form-control" placeholder="Email" required>
                <input type="password" name="pass" class="form-control" placeholder="Password" required>
                <input type="password" name="confirm_pass" class="form-control" placeholder="Confirm_Password" required>
                <input type="submit" value="register" class="form-control btn btn-primary" required>
            </form>
            <a href="Manager.jsp">Back to home</a>
        </div>
    </body>
</html>
