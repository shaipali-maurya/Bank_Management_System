/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankServlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import BankServlet.user;

/**
 *
 * @author shaipali maurya
 */
public class index extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (!(session.getAttribute("utype").equals("user"))) {
            res.sendRedirect("Login");
        }

        user u = UserDao.Account_details((String) session.getAttribute("email"));
        session.setAttribute("u", u);
        RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
        rd.include(req, res);

    }
}
