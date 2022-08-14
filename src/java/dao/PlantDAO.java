/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtils;

/**
 *
 * @author Tri Thinh
 */
public class PlantDAO {
    public static ArrayList<Plant> getPlants(String keyword, String searchby){
        ArrayList<Plant> list = new ArrayList<>();
        Connection con = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null && searchby != null){
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                        +"from Plants join Categories on Plants.CateID = Categories.CateID\n";
                
                if(searchby.equalsIgnoreCase("byname")){
                    sql = sql + "where Plants.PName like ?";
                }else if(searchby.equalsIgnoreCase("bycate")){
                    sql = sql + "where CateName like ?";
                }
                
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1,"%"+keyword+"%");
                ResultSet rs = pst.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant =new Plant(id,name,price,imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    
    public static Plant getPlant(int id){
        Plant plant = null;
        Connection con = null;
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName "
                        + "from Plants join Categories on Plants.CateID = Categories.CateID where PID = ?\n";
                
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1,Integer.toString(id));
                ResultSet rs = pst.executeQuery();
                if(rs != null){
                    if(rs.next()){
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        plant =new Plant(id,name,price,imgpath, description, status, cateid, catename);
                    }
                }
            }
        } catch (Exception e) {
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return plant;
    }
}
