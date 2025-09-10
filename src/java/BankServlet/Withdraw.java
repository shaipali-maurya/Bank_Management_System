/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankServlet;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 *
 * @author shaipali maurya
 */
public class Withdraw extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.sendRedirect("Cashier.jsp");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        long checkNo = 0, amount = 0, account_no = 0;
        String checkstr = req.getParameter("checkNo");
        String amountstr = req.getParameter("amount");
        String accountstr = req.getParameter("account_no");

        if (checkstr == null || amountstr == null || accountstr == null) {
            out.print("<h1>all fields required</h1>");
            RequestDispatcher rd = req.getRequestDispatcher("/Cashier.jsp");
            rd.include(req, res);
            return;
        } else {
            checkNo = Long.parseLong(checkstr);
            amount = Long.parseLong(amountstr);
            account_no = Long.parseLong(accountstr);
        }

        

        int status = TellerDao.withdraw(checkNo, amount, account_no);
        
        if(status == 3) {
            out.print("<h2 class='text-success'>Check no already used</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("/Cashier.jsp");
            rd.include(req, res);
            return; 
        }  else if (status == 4) {
            out.print("<h2 class='text-success'>Insufficient balance</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("/Cashier.jsp");
            rd.include(req, res);
            return;
        } 

       else if (status == 2) {
            out.print("<h2 class='text-success'>Successfully Withdrawn</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("/Cashier.jsp");
            rd.include(req, res);
            return;
        } else {
            out.print("<h2 class='text-warning'>Failed</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("/Cashier.jsp");
            rd.include(req, res);
        }
    }

}
