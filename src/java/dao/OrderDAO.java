/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Order;
import dto.Plant;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import utils.DBUtils;

/**
 *
 * @author Tri Thinh
 */
public class OrderDAO {
    public static boolean insertOrder(String email, HashMap<Integer, Integer> cart){
        Connection con = null;
        boolean result = false;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                int accid = 0, orderid = 0;
                con.setAutoCommit(false); //off autocommit
                
                //get account id by email
                String sql = "select accID from Accounts where Accounts.email=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if(rs != null && rs.next()) accid = rs.getInt("accID");
                
                //insert a new order
                System.out.println("accountid:" + accid);
                Date d = new Date(System.currentTimeMillis());
                System.out.println("order date:"+d);
                sql = "insert Orders(OrdDate,status,AccID) values(?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setDate(1,d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                
                //get order id that is the largest number
                sql = "select top 1 orderID from Orders order by orderID desc";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if(rs != null && rs.next()) orderid = rs.getInt("orderID");
                
                //insert order details
                System.out.println("orderid:" + orderid);
                for (int pid : cart.keySet()) {
                    sql = "insert OrderDetails values(?,?,?)";
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, pid);
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    con.commit();
                    con.setAutoCommit(true);
                }
                result = true;
            }else{
                System.out.println("Ko chen order duoc");
                result = false;
            }
        }catch (Exception e) {
            result = false;
        }finally{
            try{
                if(con != null) con.close();
            }catch(Exception e){
            }
            return result;
        }
    }
    
    public static ArrayList<Order> getOrders(int accId, int status){
        ArrayList<Order> list = null;
        Connection con = null;
        System.out.println(accId+" "+status);
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "select OrderID, OrdDate, shipDate,status,AccID from Orders where accId = ?";
                PreparedStatement ptm = null;
                if(status != 0){
                    sql += " and status = ?";
                    ptm = con.prepareStatement(sql);
                    ptm.setInt(1, accId);
                    ptm.setInt(2, status);                    
                }else{
                    ptm = con.prepareStatement(sql);
                    ptm.setInt(1, accId);
                }
                ResultSet rs = ptm.executeQuery();
                if(rs != null){
                    list = new ArrayList<>();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    while(rs.next()){
                        int id = rs.getInt("OrderID");
                        String ordDate = dateFormat.format(new java.util.Date(rs.getDate("OrdDate").getTime()));
                        String shipDate = null;
                        if(rs.getDate("shipDate") != null){
                            shipDate = dateFormat.format(new java.util.Date(rs.getDate("shipDate").getTime()));
                        }
                        int statusOrd = rs.getInt("status");
                        int accID = rs.getInt("AccID");                        
                        list.add(new Order(id,ordDate,shipDate,statusOrd,accID));
                    }
                }                
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(con != null) con.close();
            }catch(Exception e){
            }
        }
        return list;
    }
    
    public static boolean cancelOrder(int orderId){
        Connection con = null;
        boolean check = false;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "update Orders set status = 3 where orderId = ?";
                PreparedStatement ptm = con.prepareStatement(sql);
                ptm.setInt(1,orderId);
                check = ptm.executeUpdate()>0;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(con != null) con.close();
            }catch(Exception e){
            }
        }
        return check;
    }
    
    public static HashMap<Integer,Integer> getOrderDetails(int orderId){
        HashMap<Integer,Integer> orderdetails = new HashMap<>();
        Connection con = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "select PID, quantity from OrderDetails where orderId = ?";
                PreparedStatement ptm = con.prepareStatement(sql);
                ptm.setInt(1,orderId);
                ResultSet rs = ptm.executeQuery();
                if(rs != null){                    
                    while(rs.next()){
                        int pid = rs.getInt("PID");
                        int quantity = rs.getInt("quantity");
                        orderdetails.put(pid, quantity);
                    }
                }                
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(con != null) con.close();
            }catch(Exception e){
            }
        }
        return orderdetails;
    }
}
