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
public class Register extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String email, pass, confirm_pass, name, branch, account_type;
        long account_no, balance, contact;
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        name = req.getParameter("name");
        account_no = Long.parseLong(req.getParameter("account_no"));
        branch = req.getParameter("branch");
        balance = Long.parseLong(req.getParameter("balance"));
        account_type = req.getParameter("account_type");
        contact = Long.parseLong(req.getParameter("contact"));
        email = req.getParameter("email");
        pass = req.getParameter("pass");
        confirm_pass = req.getParameter("confirm_pass");

        if (!(pass.equals(confirm_pass))) {
            out.print("<h4>password not match confirm password</h4>");
            RequestDispatcher rd = req.getRequestDispatcher("/Register.jsp");
            rd.include(req, res);
            return;
        }
        
        if(balance < 500) {
             out.print("<h4>minimum value 500</h4>");
            RequestDispatcher rd = req.getRequestDispatcher("/Register.jsp");
            rd.include(req, res);
            return;
        }

        user u = new user();
        u.setEmail(email);
        u.setPass(pass);
        u.setName(name);
        u.setBranch(branch);
        u.setAccount_type(account_type);
        u.setAccount_no(account_no);
        u.setBalance(balance);
        u.setContact(contact);

        int status = UserDao.save(u);
        if (status > 1) {
            out.print("<h4 class='text-success'>User id and password successfully created</h4>");
            RequestDispatcher rd = req.getRequestDispatcher("/Register.jsp");
            rd.include(req, res);
        } else {
            out.print("<h4>not created</h4>");
            RequestDispatcher rd = req.getRequestDispatcher("/Register.jsp");
            rd.include(req, res);
        }
    }
}
