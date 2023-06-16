package Page;

import dao.GSalesDao;
import entity.GSales;
import util.ScannerChoice;

import java.util.ArrayList;

public class GsalesPage {
    public static void dailySaleGoodsPage(){
        System.out.println("\t正在执行列出当日售出商品列表操作\n");
        ArrayList<GSales> GsalesList = new GSalesDao().dailyGSales();//当日售出商品数组集

        if (GsalesList.size() <= 0)
        {
            System.err.println("\t！！今日无商品售出！！");
            MainPage.commodityManagementPage();
        }else{
            System.out.println("\t\t\t\t今日售出商品列表\n");
            System.out.println("\t商品名称\t\t商品价格\t\t商品数量\t\t销量\t\t备注\n");
            for (int i = 0; i < GsalesList.size(); i++) {
                GSales gSales = GsalesList.get(i);
                System.out.print("\t"+gSales.getgName()+"\t\t"+gSales.getgPrice()+" $\t\t"+gSales.getgNum()+"\t\t"+gSales.getTotalNum());
                int gNUm = gSales.getsNum();
                if (gNUm==0){
                    System.out.println("\t\t该商品已售空");
                }else if(gNUm<10){
                    System.out.println("\t\t该商品已不足10件");
                }else{
                    System.out.println("\t\t-");
                }
                System.out.println("\t");
            }
            do
            {
                System.out.println("\n\n输入 0 返回上一级菜单");
                String choice = ScannerChoice.ScannerInfoString();
                if ("0".equals(choice))
                {
                    MainPage.salesManManagementPage();
                }
                MainPage.commodityManagementPage();
            } while (true);
        }
    }
}
