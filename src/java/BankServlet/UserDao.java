/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankServlet;

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

    //creating cashier account
    public static int createTeller(String email, String pass) {
        int status = 0;
        try {
            Connection con = getConection();
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

    public static int save(user u) {
        int status = 0;

        try {
            Connection con = UserDao.getConection();

            PreparedStatement ps1 = con.prepareStatement("insert into users (account_no, email, pass) values (?,?,?)");
            ps1.setLong(1, u.getAccount_no());
            ps1.setString(2, u.getEmail());
            ps1.setString(3, u.getPass());

            status += ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement("insert into accounts (name, account_no, branch, balance, account_type, contact) values (?,?,?,?,?,?)");

            ps2.setString(1, u.getName());
            ps2.setLong(2, u.getAccount_no());
            ps2.setString(3, u.getBranch());
            ps2.setLong(4, u.getBalance());
            ps2.setString(5, u.getAccount_type());
            ps2.setLong(6, u.getContact());

            status += ps2.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return status;
    }

    public static user Account_details(String email) {
        user u = new user();

        try {
            Connection con = UserDao.getConection();

            PreparedStatement ps = con.prepareStatement("select * from accounts where account_no = (select account_no from users where email = ?)");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u.setName(rs.getString(1));
                u.setAccount_no(rs.getLong(2));
                u.setBranch(rs.getString(3));
                u.setBalance(rs.getLong(4));
                u.setAccount_type(rs.getString(5));
                u.setContact(rs.getLong(6));
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return u;

    }

    public static int read(user u) {
        int validation = 0;

        try {
            Connection con = UserDao.getConection();
            PreparedStatement ps = con.prepareStatement("select * from users where email=?");
            ps.setString(1, u.getEmail());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString(3).equals(u.getPass())) {
                    validation = 1;
                } else {
                    validation = 2;
                }
            }

        } catch (Exception ex) {
            validation = 3;
            ex.printStackTrace();
        }

        return validation;
    }

    public static ResultSet account_data() {
        ResultSet rs = null;

        try {
            Connection con = UserDao.getConection();
            PreparedStatement ps = con.prepareStatement("select * from accounts");
            rs = ps.executeQuery();
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return rs;
    }

    public static int DeleteUser(long account_no) {

        int status = 0;

        try {
            Connection con = UserDao.getConection();

            PreparedStatement ps1 = con.prepareStatement("delete from accounts where account_no=?");
            ps1.setLong(1, account_no);

            status += ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement("delete from users where account_no=?");

            ps2.setLong(1, account_no);

            status += ps2.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return status;
    }

    //statement page
    public static ResultSet statement(String email) {
        ResultSet rs = null;
        try {
            Connection con = UserDao.getConection();
            PreparedStatement ps = con.prepareStatement("select * from transactions"
                    + " where account_no = (select account_no from users where email=?)");
            ps.setString(1, email);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return rs;
    }

    //FundTransfer
    public static int FundTransfer(String email, long account_no, long amount) {
        int status = 0;
        try {
            Connection con = UserDao.getConection();
            PreparedStatement ps = con.prepareStatement("select account_no from users where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                long user = rs.getLong(1);

                if (user == account_no) {
                    return 2;
                }
                PreparedStatement ps1 = con.prepareStatement("update accounts set balance = balance - ? where account_no=? AND (balance - ?) >= 0");
                PreparedStatement ps2 = con.prepareStatement("update accounts set balance = balance + ? where account_no=?");
                ps1.setLong(1, amount);
                ps1.setLong(2, user);
                ps1.setLong(3, amount);
                ps2.setLong(1, amount);
                ps2.setLong(2, account_no);

                int i = ps1.executeUpdate();

                if (i > 0) {
                    int j = ps2.executeUpdate();
                    if (j > 0) {
                         PreparedStatement ps4 = con.prepareStatement("create table if not exists transfer"
                    + "(account_no bigint not null, "
                    + "receiver bigint, "
                    + "amount bigint not null,"
                    + "transaction_type varchar(50) not null, "
                    + "date date default(current_date),"
                    + " foreign key(account_no) references accounts(account_no))");
                    ps4.execute();
            
                        PreparedStatement ps3 = con.prepareStatement("insert into transfer (account_no, receiver, amount, transaction_type) values(?,?,?,?)");
                        ps3.setLong(1, user);
                        ps3.setLong(2, account_no);
                        ps3.setLong(3, amount);
                        ps3.setString(4, "transfer");
                        ps3.executeUpdate();
                        return 4;
                    }

                } else {
                    status = 3;
                }
            } else {
                status = 1;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return status;
    }

    //getBalance
    public static Long getBalance(String email) {
        Long balance = null;
        try {
            Connection con = UserDao.getConection();
            PreparedStatement ps = con.prepareStatement("select balance from accounts where account_no = (select account_no from users where email=?)");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                balance = rs.getLong(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return balance;
    }

}
