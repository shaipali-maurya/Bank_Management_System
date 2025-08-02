<%-- 
    Document   : naw.jsp
    Created on : 1 Aug 2025, 5:39:07 pm
    Author     : shaipali maurya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Bootstrap.html"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <style>
            .nav{
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: black;
                padding: 5px 10px;
            }
            .nav a {
                text-decoration: none;
               
            }
            .left a {
                color: #ffe;
                margin: 0 5px;
            }
        </style>
    </head>
    <body>
        <div class="nav">
            <div class="left">
                <b><span class="text-white">My Bank</span></b>
                <a href="#">Home</a>
                <a href="#">Accounts</a>
                <a href="#">Account Statements</a>
                <a href="#">Fund Transfer</a>
            </div>
            <div class="right">
                <a href="#" class="btn btn-outline-primary">Account Balance: Rs</a>
                <a href="#" class="btn btn-outline-primary"><i class="fa-solid fa-book"></i></a>
                <a href="#" class="btn btn-outline-primary"><i class="fa-solid fa-envelope"></i></a>
                <a href="#" class="btn btn-outline-primary"><i class="fa-solid fa-question"></i></a>
                <a href="#" class="btn btn-outline-danger"><i class="fa-solid fa-arrow-right-from-bracket"></i></a>
            </div>
        </div>
    </body>
</html>
