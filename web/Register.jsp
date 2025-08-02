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
        <div class="main">
            <form method="post" action="Register">
                <input type="email" name="email" class="form-control" placeholder="Email">
                <input type="password" name="pass" class="form-control" placeholder="Password">
                <input type="password" name="confirm_pass" class="form-control" placeholder="Confirm_Password">
                <input type="submit" value="register" class="form-control btn btn-primary">
                 <p><a href="Login.jsp">Already Registered</a></p>
            </form>
        </div>
    </body>
</html>
