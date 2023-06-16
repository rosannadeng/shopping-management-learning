package Page;

import com.sun.tools.javac.Main;
import dao.GoodsDao;
import dbController.DbClose;
import entity.Goods;
import util.QueryPrint;
import util.ScannerChoice;

import java.sql.SQLException;
import java.util.ArrayList;

import static util.ScannerChoice.*;

public class GoodsPage {
    public static void displayGoodsPage(){
        System.out.println("\t\t\t\t\t所有商品列表\n\n");
        ArrayList<Goods> goodsList = new GoodsDao().displayGoods();

        if (goodsList.size() <= 0)
        {
            System.err.println("！库存为空！");
            MainPage.MaintenancePage();
        }else
        {
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            for (int i = 0,length = goodsList.size(); i < length; i++) //避免重复计算变量，浪费资源！
            {
                Goods goods = goodsList.get(i);
                System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+" $\t\t"+goods.getGnum());

                int gNum = goods.getGnum();
                if (gNum==0)
                {
                    System.out.println("\t\t该商品已售空！");
                }else if (gNum<10)
                {
                    System.out.println("\t\t该商品已不足10件");
                }else
                {
                    System.out.println("\t\t-");
                }
                System.out.println("\t");
            }
            //下一步
            System.out.println("---------------------");
            do
            {
                System.out.println("输入 0 返回上一级菜单");
                String choice = ScannerInfoString();
                if ("0".equals(choice))
                {
                    MainPage.MaintenancePage();
                }
                System.out.println("输入有误！");
            } while (true);
        }
    }


    public static void updateGoodsPage(){
        System.out.println("\t 正在更改商品");
        System.out.println("请输入目标商品名字");

        int gid = QueryPrint.query("updateGoodsPage");
        System.out.println("\n--------请选择您要更改的内容\n");
        System.out.println("\t1.更改商品-名称");
        System.out.println("\t2.更改商品-价格");
        System.out.println("\t3.更改商品-数量");
        System.out.println("\n请输入选项,或者按0返回上一级菜单.");
        do{
            String choice = ScannerInfoString();
            String regex = "[0-3]";
            if (choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        MainPage.MaintenancePage();
                        break;
                    case 1:
                        System.out.println("请输入商品新名字：");
                        String gname =  ScannerInfoString();
                        Goods goodsName = new Goods(gid,gname);
                        boolean boolname = new GoodsDao().updateGoods(1,goodsName);
                        if (boolname){
                            System.out.println("\n\t !!成功更新商品!!\n");
                        }else{
                            System.err.println("\n\t！！更新商品失败！！");
                        }
                        changeInfo("updateGoodsPage");
                        break;
                    case 3:
                        System.out.println("请输入商品新数量");
                        int gnum = ScannerNum();
                        Goods goodsnum = new Goods(gid,gnum);
                        boolean boolnum = new GoodsDao().updateGoods(3,goodsnum);
                        if(boolnum){
                            System.out.println("\n\t !!成功更新商品!!\n");
                        }else{
                            System.err.println("\n\t！！更新商品失败！！");
                        }changeInfo("updateGoodsPage");
                        break;
                    case 2:
                        System.out.println("请输入商品新价格");
                        double gprice = ScannerInfo();
                        Goods goodsprice = new Goods(gid,gprice);
                        boolean boolprice = new GoodsDao().updateGoods(2,goodsprice);
                        if(boolprice){
                            System.out.println("\n\t !!成功更新商品!!\n");
                        }else{
                            System.err.println("\n\t！！更新商品失败！！");
                        }changeInfo("updateGoodsPage");
                        break;
                }
            }
        }while(true);

    }
    public static void deleteGoodsPage(){
        System.out.println("\t 正在删除商品");
        System.out.println("请输入目标商品名字");

        int gid = QueryPrint.query("deleteGoodsPage");
        do{
            System.out.println("\n确认删除改商品:Y/N");
            String choice = ScannerInfoString();
            if("y".equals(choice) || "Y".equals(choice)){
                boolean boolDelete=new GoodsDao().deleteGoods(gid);
                if (boolDelete){
                    System.err.println("\t ！！已成功删除改商品！！\n");
                }else{
                    System.err.println("\n\t!!删除商品失败！！");
                }
                changeInfo("deleteGoodsPage");
            } else if ("n".equals(choice) || "N".equals(choice)) {
                MainPage.MaintenancePage();
            }
        System.out.println("\t!!输入有误，请重新输入！！\n");
        }while(true);
    }
    public static void addGoodsPage(){

        System.out.println("\t正在执行添加商品操作\n");

        System.out.println("\n請輸入添加商品-名称");
        String goodsName = ScannerInfoString();

        System.out.println("\n請輸入添加商品-价格");
        double goodsPrice = ScannerInfo();

        System.out.println("\n請輸入添加商品-数量");
        int goodsNumber = ScannerNum();

        Goods goods  = new Goods(goodsName,goodsPrice,goodsNumber);
        boolean bool = new GoodsDao().addGoods(goods);

        if (bool)
        {
            System.out.println("\n\t!您已成功添加商品到数据库!");
        }else
        {
            System.out.println("添加商品失败");
        }
        changeInfo("addGoodsPage");//选择下一步
    }
    public static void queryGoodsPage(){
        System.out.println("\t 正在执行查询商品操作");
        System.out.println("\t\t1.按照商品 数量升序 查询");
        System.out.println("\t\t2.按照商品 价格升序 查询");
        System.out.println("\t\t3.输入商品  关键字  查询");
        System.out.println("\n请输入选项,或者按0返回上一级菜单.");
        do{
            String info = ScannerInfoString();
            String regex = "[0-3]";
            if (info.matches(regex)){
                int choice = Integer.parseInt(info);
                switch(choice){
                    case 0:
                        MainPage.MaintenancePage();
                        break;
                    case 1:
                    case 2:
                    case 3:
                        if (choice == 3)//当用户使用3（即关键字查询）时，需要打印此项目。
                        {
                            System.out.println("\t\t正在执行商品  关键字  查询操作\n");
                            System.out.println("\n请输入商品关键字");
                        }
                        ArrayList<Goods> goodsList = new GoodsDao().queryGoods(choice);
                        if (goodsList==null || goodsList.size()<=0){
                            System.err.println("\n\t no this product \n");
                            queryGoodsPage();
                        }else{
                            if (choice ==1){
                                System.out.println("\t\t\t\t\t商品按照 数量升序 列表\n\n");
                            }else if (choice ==2){
                                System.out.println("\t\t\t\t\t商品按照 价格升序 列表\n\n");
                            }else
                            {
                                System.out.println("\t\t\t\t\t您所查找的商品如下\n\n");
                            }
                            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");

                            //遍历数组（存放用户查找的信息）
                            for (int i = 0,length = goodsList.size(); i < length; i++)
                            {
                                Goods goods = goodsList.get(i);
                                System.out.println("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
                                int gnum = goods.getGnum();
                                if (gnum ==0){
                                    System.out.println("商品已售空");
                                }else if (gnum<10)
                                {
                                    System.out.println("\t\t该商品已不足10件");
                                }else
                                {
                                    System.out.println("\t\t-");
                                }
                                System.out.println("\t");
                            }
                            System.out.println("---------------------");
                            do{
                                System.out.println("输入 0 返回上一级菜单");
                                String choiceNext = ScannerInfoString();

                                if ("0".equals(choiceNext))
                                {
                                    MainPage.MaintenancePage();
                                }
                                System.err.println("输入有误！");
                            } while (true);
                        }
                    break;
                    default:
                        break;
                }
                break;
            }
            System.err.println("wrong input");
            System.out.println("请重新选择,或者按0返回上一级菜单.");
        }while (true);
        System.out.println("\n\n输入 0 返回上一级菜单");
        boolean boolNext = true;
        do
        {
            String choice = ScannerInfoString();
            if ("0".equals(choice))
            {
                boolNext = false;
                queryGoodsPage();
            }
            System.err.println("!输入有误!");
            System.out.println("请输入 0 返回上一级菜单");
        } while (boolNext);
    }
}
