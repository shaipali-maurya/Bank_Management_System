<%-- 
    Document   : Login
    Created on : 25 Jul 2025, 4:57:43 pm
    Author     : shaipali maurya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Bootstrap.html"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="static/style.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main text-center">
            <div class="login-box">
                <div class="accordion" id="accordionExample">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingOne">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                User Login
                            </button>
                        </h2>
                        <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <form method="post" action="Login">
                                    <input type="hidden" name="utype" value="user">
                                    <input type="email" name="email" class="form-control" required>
                                    <input type="password" name="pass" class="form-control mt-3" required>
                                    <input type="submit" value="Enter" class="btn btn-primary form-control mt-3" required>
                                    
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingTwo">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                Manager Login
                            </button>
                        </h2>
                        <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <form  method="post" action="Login">
                                     <input type="hidden" name="utype" value="manager">
                                    <input type="email" name="email" class="form-control" required>
                                    <input type="password"  name="pass" class="form-control mt-3" required>
                                    <input type="submit" value="Enter" class="btn btn-primary form-control mt-3" required>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingThree">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                Cashier Login
                            </button>
                        </h2>
                        <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <form method="post" action="Login">
                                    <input type="hidden" name="utype" value="cashier">
                                    <input type="email" name="email" class="form-control" required>
                                    <input type="password" name="pass" class="form-control mt-3" required>
                                    <input type="submit" value="Enter" class="btn btn-primary form-control mt-3" required>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>
