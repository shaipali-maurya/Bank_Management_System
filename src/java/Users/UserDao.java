/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

import java.util.*;
import java.sql.*;

/**
 *
 * @author shaipali maurya
 */
public class UserDao {

    public static Connection getConection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
        } catch (Exception ex) {
            System.out.print("ex");

        }
        return con;
    }

    public static int save(user u) {
        int status = 0;

        try {
            Connection con = UserDao.getConection();
            
            
            PreparedStatement ps = con.prepareStatement("insert into users (utype, email, pass) values (?,?,?)");
            ps.setString(1, u.getUtype());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPass());
            status = ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return status;
    }
    
    public static int read(user u){
        int validation = 0;
        
        try{
            Connection con = UserDao.getConection();
            PreparedStatement ps = con.prepareStatement("select * from users where utype=? and  email=?");
            ps.setString(1, u.getUtype());
            ps.setString(2, u.getEmail());
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                if(rs.getString(3).equals(u.getPass())) {
                    validation = 1;
                } else {
                    validation = 2;
                }
            }
          
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return validation;
    }
}
