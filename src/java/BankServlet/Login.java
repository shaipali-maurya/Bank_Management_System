/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankServlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author shaipali maurya
 */
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
        rd.forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String utype, email, pass;
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        HttpSession Session = req.getSession();

        utype = req.getParameter("utype");
        email = req.getParameter("email");
        pass = req.getParameter("pass");

        user u = new user();
        u.setUtype(utype);
        u.setEmail(email);
        u.setPass(pass);

        if (utype.equals("user")) {

            int validation = UserDao.read(u);

            switch (validation) {
                case 0: {
                    out.print("<h1 class='text-success'>You are not registered users</h1>");
                    RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
                    rd.include(req, res);
                    break;
                }
                case 1: {
                    Session.setAttribute("utype", utype);
                    Session.setAttribute("email", email);
                    res.sendRedirect("index");
                    break;
                }

                case 3: {
                    out.print("<h class='text-info'>Servlet error occured</h1>");
                    RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
                    rd.include(req, res);
                    break;
                }
                default: {
                    out.print("<h1 class='text-danger'>Worng password</h1>");
                    RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
                    rd.include(req, res);
                    break;
                }
            }
        } else if (utype.equals("manager")) {
            if (email.equals("manager@manager.com") && pass.equals("manager")) {
                Session.setAttribute("utype", "manager");
                Session.setAttribute("email", email);
                res.sendRedirect("Manager.jsp");
            } else {
                out.print("<h1 class='text-danger'>Wrong Email password</h1>");
                RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
                rd.include(req, res);
            }
        } else if (utype.equals("cashier")) {
            int validation = TellerDao.login(u);

            if (validation == 1) {
                Session.setAttribute("utype", "cashier");
                Session.setAttribute("email", email);
                res.sendRedirect("Cashier.jsp");
                
            } else {
                out.print("<h1 class='text-danger'>Wrong Email password</h1>");
                RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
                rd.include(req, res);
            }

        }

    }

}
