package dbController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {
    public static Connection getconn()
    {
        Connection conn = null;

        String user   = "root";
        String passwd = "Rosanna!0";
        String url = "jdbc:mysql://localhost:3306/mallforfirstlearning?useSSL=false&serverTimezone=Asia/Shanghai";

        try
        {
            System.out.println("连接数据库...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,passwd);
            System.out.println("Connect Successfully");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args){
        DbConn d=new DbConn();
        System.out.println(d.getconn());
    }

}
