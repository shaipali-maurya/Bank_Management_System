/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankServlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author shaipali maurya
 */
public class Logout  extends HttpServlet{
     public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
         HttpSession session = req.getSession();
         session.removeAttribute("utype");
         session.removeAttribute("email");
         res.sendRedirect("Login");
    }
    
}
