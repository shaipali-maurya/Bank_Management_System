/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankServlet;
import java.sql.*;

/**
 *
 * @author shaipali maurya
 */
public class Dao {
    public static ResultSet getDetails(long account_no){
        ResultSet rs = null;
        
        try{
            Connection con = UserDao.getConection();
            PreparedStatement ps = con.prepareStatement("select * from accounts where account_no=?");
            ps.setLong(1, account_no);
            
            rs = ps.executeQuery();
            
        } catch(Exception ex) {
            System.out.print(ex);
        }
        return rs;
    }
    
    public static int sendNotice (long account_no, String notice) {
        int status = 0;
        if (notice.equals("")) {
            return status;
        }
        try{
            Connection con = UserDao.getConection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("create table if not exists notice (account_no bigint not null, notice varchar(50) not null, foreign key (account_no) references users(account_no))");
            
            PreparedStatement ps = con.prepareStatement("insert into notice(account_no, notice) values(?, ?)");
            ps.setLong(1, account_no);
            ps.setString(2, notice);
            
            status = ps.executeUpdate();
            
        }catch(Exception ex){
            System.out.print(ex);
        }
        
        return status;
        
    }
    
    public static ResultSet getNotification(String email) {
        ResultSet rs = null;
        
        try{
            Connection con = UserDao.getConection();
             PreparedStatement ps = con.prepareStatement("select * from notice where account_no = (select account_no from users where email = ?)");
             ps.setString(1, email);
             
             rs = ps.executeQuery();
        }catch(Exception ex) {
            System.out.print(ex);
        }
        return rs;
    }
    
}
