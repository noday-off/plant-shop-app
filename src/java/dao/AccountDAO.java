/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtils;

/**
 *
 * @author Tri Thinh
 */
public class AccountDAO {
    public static Account getAccount(String email, String password){
        Connection con = null;
        Account acc = null;
        try {
            con = DBUtils.makeConnection();
            
            if(con != null){
                String sql = "SELECT accID,email,password,fullname,phone,status,role\n"
                        +"FROM dbo.Accounts\n"
                        +"WHERE status = 1 and email = ? and password = ? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs !=null && rs.next()){
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }
    
    public static ArrayList<Account> getAccounts(String keyword){
        Connection con = null;
        ArrayList<Account> accounts = null;
        Account acc = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "SELECT accID,email,password,fullname,phone,status,role\n"
                        +"FROM dbo.Accounts WHERE status = 1 AND fullname LIKE ?\n";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1,"%"+keyword+"%");
                ResultSet rs = pst.executeQuery();
                accounts = new ArrayList<Account>();
                if(rs != null){
                    while ( rs.next()){
                        int AccID = rs.getInt("accID");
                        String Email = rs.getString("email");
                        String Password = rs.getString("password");
                        String Fullname = rs.getString("fullname");
                        String Phone = rs.getString("phone");
                        int Status = rs.getInt("status");
                        int Role = rs.getInt("role");
                        acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role);
                        accounts.add(acc);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return accounts;
    }
    
    public static boolean updateAccountStatus(String email, int status){
        Connection con = null;
        Account acc = null;
        boolean check = false;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "UPDATE dbo.Accounts\n"
                        +"SET status = ?\n"
                        +"WHERE email = ? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, Integer.toString(status));
                pst.setString(2, email);
                check = pst.executeUpdate()>0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return check;
    }
    public static boolean updateAccount(String email, String newPassword, String newFullname, String newPhone, int newStatus){
        Connection con = null;
        Account acc = null;
        boolean check = false;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "UPDATE dbo.Accounts\n"
                        +"SET password = ?, fullname = ?, phone = ?, status = ?\n"
                        +"WHERE email = ? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, newPassword);
                pst.setString(2, newFullname);
                pst.setString(3, newPhone);
                pst.setString(4, Integer.toString(newStatus));
                pst.setString(5, email);
                check = pst.executeUpdate()>0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return check;
    }
    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newStatus, int newRole){
        Connection con = null;
        Account acc = null;
        boolean check = false;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "insert into Accounts values (?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, newEmail);
                pst.setString(2, newPassword);
                pst.setString(3, newFullname);
                pst.setString(4, newPhone);                
                pst.setString(5, Integer.toString(newStatus));
                pst.setString(6, Integer.toString(newRole));
                check = pst.executeUpdate()>0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return check;
    }
    
}
