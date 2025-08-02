/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

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

        int validation = UserDao.read(u);
        
        switch (validation) {
            case 0:
                {
                    out.print("<h1 class='text-success'>You are not registered users</h1>");
                    RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
                    rd.include(req, res);
                    break;
                }
            case 1:
                Session.setAttribute("email", email);
                res.sendRedirect("index.jsp");
                break;
            default:
                {
                    out.print("<h1 class='text-danger'>Worng password</h1>");
                    RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
                    rd.include(req, res);
                    break;
                }
        }
       
    }
}
