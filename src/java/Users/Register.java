/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import Users.user;

/**
 *
 * @author shaipali maurya
 */
public class Register extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String utype, email, pass, confirm_pass;
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        utype = "user";
        email = req.getParameter("email");
        pass = req.getParameter("pass");
        confirm_pass = req.getParameter("confirm_pass");

        if (email.equals("") || pass.equals("") || confirm_pass.equals("")) {
            out.print("<h4>All fields necessary </h4>");
            RequestDispatcher rd = req.getRequestDispatcher("/Register.jsp");
            rd.include(req, res);
            return;
        } else if (!(pass.equals(confirm_pass))) {
            out.print("<h4>password not match confirm password</h4>");
            RequestDispatcher rd = req.getRequestDispatcher("/Register.jsp");
            rd.include(req, res);
            return;
        }

        user u = new user();
        u.setUtype(utype);
        u.setEmail(email);
        u.setPass(pass);

        int status = UserDao.save(u);
        if (status > 0) {
            out.print("<h4 class='text-success'>Registration Successful</h4>");
            RequestDispatcher rd = req.getRequestDispatcher("/Register.jsp");
            rd.include(req, res);
            
        } else {
            out.print("<h4>Registrion failed</h4>");
            RequestDispatcher rd = req.getRequestDispatcher("/Register.jsp");
            rd.include(req, res);
        }
    }
}
