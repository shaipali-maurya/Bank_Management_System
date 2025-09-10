/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shaipali maurya
 */
public class Deposite extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.sendRedirect("Cashier.jsp");
    }

    @Override
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

        int status = TellerDao.deposite(checkNo, amount, account_no);

        
        if(status == 3) {
            out.print("<h2 class='text-success'>Check no already used</h2>");
            RequestDispatcher rd = req.getRequestDispatcher("/Cashier.jsp");
            rd.include(req, res);
            return; 
        }

       else if (status == 2) {
            out.print("<h2 class='text-success'>Successfully Deposited</h2>");
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
