/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankServlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 *
 * @author shaipali maurya
 */
public class Transfer extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.sendRedirect("FundTransfer.jsp");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String email;
        long account_no, amount;

        email = req.getParameter("email");
        account_no = Long.parseLong(req.getParameter("account_no"));
        amount = Long.parseLong(req.getParameter("amount"));

        if (email == null || account_no == 0 || amount <= 0) {
            out.print("<h2 class='text-danger'>All fields required and amount must no zero</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("/FundTransfer.jsp");
            rd.include(req, res);
            return;
        }

        int status = UserDao.FundTransfer(email, account_no, amount);

        if (status == 4) {
            out.print("<h2 class='text-success'>Successfully Transfer</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("/FundTransfer.jsp");
            rd.include(req, res);
            return;
        } else if (status == 0) {
            out.print("<h2 class='text-danger'>Failed</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("/FundTransfer.jsp");
            rd.include(req, res);
            return;
        } else if (status == 1) {
            out.print("<h2 class='text-info'>Error occured to Fetch Your Account no</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("/FundTransfer.jsp");
            rd.include(req, res);
            return;
        } else if (status == 2) {
            out.print("<h2 class='text-warning'>Sender and resiver account is same</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("/FundTransfer.jsp");
            rd.include(req, res);
            return;
        } else if (status == 3) {
            out.print("<h2 class='text-danger'>Failed to Transfer</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("/FundTransfer.jsp");
            rd.include(req, res);
            return;
        }else if (status == 5) {
            out.print("<h2 class='text-danger'>Insufficient Balance</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("/FundTransfer.jsp");
            rd.include(req, res);
            return;
        }
        res.sendRedirect("FundTransfer.jsp");
    }
}
