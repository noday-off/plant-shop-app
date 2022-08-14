
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtils {
    public static Connection makeConnection() throws ClassNotFoundException, SQLException{
        Connection con = null;
        String IP = "localhost";
        String instanceName ="DELLONATH";
        String port = "1433";
        String uid = "sa";
        String pwd = "admin";
        String db = "PlantShop";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://"+IP+"\\"+instanceName+":"+port+";databasename="+db+";user="+uid+";password="+pwd;
        Class.forName(driver);
        con = DriverManager.getConnection(url);
        return con;
    }
}
