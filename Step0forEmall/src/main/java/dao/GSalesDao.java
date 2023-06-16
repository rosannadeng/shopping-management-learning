package dao;

import dbController.DbClose;
import dbController.DbConn;
import entity.GSales;
import entity.SalesMan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GSalesDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs    = null;
    //today's sales
    public ArrayList<GSales> dailyGSales(){
        ArrayList<GSales> GsalesList = new ArrayList<GSales>();
        conn = DbConn.getconn();
        String sql = "SELECT GNAME,GPRICE,GNUM,ALLSUM FROM GOODS, (SELECT GID AS salesid,sum(snum) as allSum from GSALES WHERE TRUNC(SDATE)=TRUNC(SYSDATE) GROUP BY GID) WHERE GID = SALESID";
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                String gname =rs.getString(1);
                double gPrice = rs.getDouble(2);
                int gNum = rs.getInt(3);
                int allsum = rs.getInt("allSum");

                GSales gSales= new GSales(gname,gPrice,gNum,allsum);
                GsalesList.add(gSales);
            }
        }catch (SQLException e1){
            e1.printStackTrace();
        }finally {
            DbClose.queryClose(pstmt,rs,conn);
        }
        return GsalesList;

    }

    //check out
    public boolean shoppingSettlement(GSales gSales){
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "INSERT INTO GSALES(GID,SID,SNUM) VALUES(?,?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,gSales.getgId());
            pstmt.setInt(2,gSales.getsId());
            pstmt.setInt(3,gSales.getsNum());
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

}
