package util;

import dao.GoodsDao;
import dbController.DbClose;
import dbController.DbConn;
import entity.Goods;
import entity.SalesMan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryPrint {
    Connection conn= null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    public static int query(String opr){
        int gid = -1;
        String shopping = ScannerChoice.ScannerInfoString();
        ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(-1,shopping);
        if (goodsList == null || goodsList.size() <= 0)
        {
            System.err.println("\t！！查无此商品 ！！");
            //調用选择下一步函数
            ScannerChoice.changeInfo(opr);
        }else{
            Goods goods = goodsList.get(0);

            System.out.println("\t\t\t\t\t商品列表\n\n");
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
            if (goods.getGnum()==0){
                System.out.println("\t\t该商品已售空");
            }else if (goods.getGnum()<10)
            {
                System.out.println("\t\t该商品已不足10件");}
            else{
                System.out.println("\t\t-");
            }
            gid = goods.getGid();
        }
        return gid;
    }
    public static int querySettlement(){
        int gid = 01;
        ArrayList<Goods> goodssettlement = new GoodsDao().queryGoods(3);
        if (goodssettlement == null || goodssettlement.size() <= 0)
        {
            System.err.println("\t！！查无此商品 ！！\n");
            gid = -3;
        }else{
            System.out.println("\t\t\t\t\t商品列表\n\n");
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            for (int i = 0; i < goodssettlement.size(); i++){
                Goods goods = goodssettlement.get(i);
                if (goods.getGnum()>0){
                    System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());

                    if (goods.getGnum()==0)
                    {
                        System.out.println("\t\t该商品已售空");
                    }else if (goods.getGnum()<10)
                    {
                        System.out.println("\t\t该商品已不足10件");
                    }else
                    {
                        System.out.println("\t\t-");
                    }
                    if (goodssettlement.size()==1)
                    {
                        gid = goods.getGid(); //将商品编号返回给调用者
                    }else
                    {
                        gid = -2;
                    }
                }
            }
        }

        return gid;
    }
    public ArrayList<Goods> queryGoodsKey(int gId,String gName){
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        conn = DbConn.getconn();
        String sql = "SELECT * FROM GOODS WHERE GNAME=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,gId);
            pstmt.setString(2,gName);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int gid = rs.getInt("gId");
                String gname = rs.getString(2);
                double gprice = rs.getInt(3);
                int gnum = rs.getInt(4);

                Goods goods = new Goods(gid,gname,gprice,gnum);
                goodsList.add(goods);
            }
        }catch (SQLException e1){
            e1.printStackTrace();
        }finally {
            DbClose.queryClose(pstmt,rs,conn);
        }
        return goodsList;
    }
    public ArrayList<SalesMan> querySalesMan(String SalesManName){
        ArrayList<SalesMan> SalesManList = new ArrayList<SalesMan>();
        conn = DbConn.getconn();
        String sql = "SELECT * FROM SALESMAN WHERE SNAME=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,SalesManName);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int sId = rs.getInt("sId");
                String sname = rs.getString(2);
                String spassword = rs.getString(3);
                SalesMan salesMan = new SalesMan(sId,sname,spassword);
                SalesManList.add(salesMan);

            }
        }catch (SQLException e1){
            e1.printStackTrace();
        }finally {
            DbClose.queryClose(pstmt,rs,conn);
        }
        return SalesManList;
    }
}
