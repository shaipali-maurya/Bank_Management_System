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
public class TellerDao {
    //creating cashier account

    public static int createTeller(String email, String pass) {
        int status = 0;
        try {
            Connection con = UserDao.getConection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("Create table if not exists teller(email varchar(50) primary key, pass varchar(50) not null, account_type varchar(50))");

            PreparedStatement ps = con.prepareStatement("insert into teller(email, pass, account_type) values (?,?,?)");
            ps.setString(1, email);
            ps.setString(2, pass);
            ps.setString(3, "cashier");

            status = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return status;

    }

    public static ResultSet read() {
        ResultSet rs = null;
        try {
            Connection con = UserDao.getConection();
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("select * from teller");
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return rs;
    }

    public static void delete(String email) {
        try {
            Connection con = UserDao.getConection();
            PreparedStatement ps = con.prepareStatement("delete from teller where email=?");
            ps.setString(1, email);

            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static int login(user u) {
        int validation = 0;
        try {
            Connection con = UserDao.getConection();
            PreparedStatement ps = con.prepareStatement("select *  from teller where email=?");
            ps.setString(1, u.getEmail());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString(2).equals(u.getPass())) {
                    validation = 1;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return validation;
    }

    public static ResultSet getDetails(long account_no) {
        ResultSet rs = null;
        try {
            Connection con = UserDao.getConection();
            PreparedStatement ps = con.prepareStatement("select * from accounts where account_no=?");
            ps.setLong(1, account_no);
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rs;

    }

    //Withdraw
    public static int withdraw(long checkNo, long amount, long account_no) {
        int status = 0;
        try {
            Connection con = UserDao.getConection();

            PreparedStatement ps = con.prepareStatement("create table if not exists transactions"
                    + "(account_no bigint not null,"
                    + " checkNo bigint primary key, "
                    + "amount bigint not null,"
                    + "transaction_type varchar(50) not null, "
                    + "date date default(current_date),"
                    + " foreign key(account_no) references accounts(account_no))");
            ps.execute();

            //validation
            PreparedStatement psmt = con.prepareStatement("select * from transactions where checkNo=?");
            psmt.setLong(1, checkNo);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return 3;
            }

            PreparedStatement psmt1 = con.prepareStatement("select * from accounts where account_no=?");
            psmt1.setLong(1, account_no);
            ResultSet rs1 = psmt1.executeQuery();

            if (rs1.next()) {
                long balance = rs1.getLong(4);
                if ((balance - amount) < 0) {
                    return 4;
                }
            }

            PreparedStatement ps1 = con.prepareStatement("update accounts set balance = (balance - ?)"
                    + " where account_no=?");
            ps1.setLong(1, amount);
            ps1.setLong(2, account_no);
            int i = ps1.executeUpdate();

            if (i > 0) {
                status = 1;
            }

            PreparedStatement ps2 = con.prepareStatement("insert into transactions (account_no, checkNo, amount, transaction_type) values(?,?,?,?)");
            ps2.setLong(1, account_no);
            ps2.setLong(2, checkNo);
            ps2.setLong(3, amount);
            ps2.setString(4, "withdraw");

            int j = ps2.executeUpdate();

            if (j > 0) {
                status = 2;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return status;
    }

    //Deposite
    public static int deposite(long checkNo, long amount, long account_no) {
        int status = 0;
        try {
            Connection con = UserDao.getConection();
            PreparedStatement ps = con.prepareStatement("create table if not exists transactions"
                    + "(account_no bigint not null, "
                    + "checkNo bigint primary key, "
                    + "amount bigint not null,"
                    + "transaction_type varchar(50) not null, "
                    + "date date default(current_date),"
                    + " foreign key(account_no) references accounts(account_no))");
            ps.execute();

            PreparedStatement psmt = con.prepareStatement("select * from transactions where checkNo=?");
            psmt.setLong(1, checkNo);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return 3;
            }

            PreparedStatement ps1 = con.prepareStatement("update accounts set balance = (balance + ?)"
                    + " where account_no=?");
            ps1.setLong(1, amount);
            ps1.setLong(2, account_no);
            int i = ps1.executeUpdate();

            if (i > 0) {
                status = 1;
            }

            PreparedStatement ps2 = con.prepareStatement("insert into transactions (account_no, checkNo, amount, transaction_type) values(?,?,?,?)");
            ps2.setLong(1, account_no);
            ps2.setLong(2, checkNo);
            ps2.setLong(3, amount);
            ps2.setString(4, "deposite");

            int j = ps2.executeUpdate();

            if (j > 0) {
                status = 2;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return status;
    }

}
