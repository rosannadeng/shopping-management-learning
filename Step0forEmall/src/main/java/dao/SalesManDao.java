package dao;

import dbController.DbClose;
import dbController.DbConn;
import entity.SalesMan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalesManDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs    = null;
    //login check
    public ArrayList<SalesMan> checkstandLog(String sName)
    {
        ArrayList<SalesMan> salesManInfo = new ArrayList<SalesMan>();
        conn = DbConn.getconn();
        String sql = "SELECT SID,SPASSWORD FROM SALESMAN WHERE SNAME=?";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,sName);

            rs 	  = pstmt.executeQuery();
            while (rs.next())
            {
                String sPassWord = rs.getString("spassword");
                int sId = rs.getInt("sId");
                SalesMan salesMan = new SalesMan(sId,sPassWord);
                salesManInfo.add(salesMan);
            }
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }finally
        {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return salesManInfo;
    }
    public boolean addSalesMan(SalesMan sName){
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "Insert Into SALESMAN(SNAME,SPASSWORD) VALUES(?,?)";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,sName.getSName());
            pstmt.setString(2,sName.getSPassword());

            int rs = pstmt.executeUpdate();
            if (rs > 0)
            {
                bool = true;
            }}catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbClose.addClose(pstmt,conn);
        }
        return bool;
    }

    public boolean updateSalesMan(int key,SalesMan sName){
        boolean bool = false;
        conn = DbConn.getconn();
        switch (key){
            default:
                break;
            case 1:
                String sqlName = "Update SALESMAN SET SNAME =? WHERE SID =?";
                try
                {
                    pstmt = conn.prepareStatement(sqlName);
                    pstmt.setString(1,sName.getSName());
                    pstmt.setInt(2,sName.getSId());
                    int rs = pstmt.executeUpdate();
                    if (rs>0){
                        bool = true;
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }finally {
                    DbClose.addClose(pstmt,conn);
                }
                break;
            case 2:
                String sqlPs = "UPDATE SALESMAN SET SPASSWORD=? WHERE SID=?";
                try
                {
                    pstmt = conn.prepareStatement(sqlPs);
                    pstmt.setString(1, sName.getSName());
                    pstmt.setInt(2,sName.getSId());

                    int rs = pstmt.executeUpdate();
                    if (rs>0){
                        bool = true;
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }finally {
                    DbClose.addClose(pstmt,conn);
                }
                break;
        }

        return bool;
    }

    public boolean deleteSalesMan(String sName){
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "Delete FROM SALESMAN WHERE SNAME = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            int rs = pstmt.executeUpdate();
            if (rs>0){
                bool = true;
            }
        }catch (SQLException e1){
            e1.printStackTrace();
        }finally {
            DbClose.queryClose(pstmt,rs,conn);
        }
        return bool;
    }
    public  ArrayList<SalesMan> displaySalesMan()
    {
        ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
        conn = DbConn.getconn();
        String sql = "SELECT * FROM SALESMAN";

        try
        {
            pstmt = conn.prepareStatement(sql);
            rs =  pstmt.executeQuery();
            while (rs.next())
            {
                int sId = rs.getInt(1);
                String sName = rs.getString(2);
                String sSpassWord = rs.getString(3);

                SalesMan salesMan = new SalesMan(sId,sName,sSpassWord);
                salesManList.add(salesMan);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return salesManList;
    }
    public ArrayList<SalesMan> querySalesMan(String sName){
        ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
        conn = DbConn.getconn();
        sName = "%"+sName+"%";
        String sql = "SELECT * FROM SALESMAN WHERE SNAME LIKE ?";
        try{pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                int sid = rs.getInt("sid");
                String sname = rs.getString(2);
                String sPassWord = rs.getString(3);

                SalesMan salesMan = new SalesMan(sid,sname,sPassWord);
                salesManList.add(salesMan);
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally {
            DbClose.queryClose(pstmt,rs,conn);
        }
        return salesManList;
    }

}
