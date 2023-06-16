package util;

import Page.MainPage;
import Page.SalesManPage;
import Page.GoodsPage;

import java.util.Scanner;

public class ScannerChoice {
    public static double ScannerInfo(){
        double num = 0.00;
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("保留小数点后两位，输入： ");
            String info = sc.next();

            String regex = "(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})";
            if (info.matches(regex)){
                num = Double.parseDouble(info);

            }else{
                System.err.println("输入有误");
                continue;
            }
            break;

        }while (true);
            return num;

        }
    public static int ScannerNum()
    {
        int num = 0;
        String regex = "([1-9])|([1-9][0-9]+)";//商品数量
        do
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入：");
            String nums = sc.next();

            if (nums.matches(regex))
            {
                num = Integer.parseInt(nums);
            }else
            {
                System.err.println("！输入有误！");
                continue;
            }
            break;
        } while (true);
        return num;}

    public static String ScannerInfoString(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入");
        return sc.next();
    }

    public static void changeInfo(String oper){
        do{
            System.out.println("是否继续进行-当前操作:(Y/N)");
            String choice = ScannerChoice.ScannerInfoString();
            if ("y".equals(choice) || "Y".equals(choice)) {
                if ("updateGoodsPage".equals(oper))
                {
                    GoodsPage.updateGoodsPage();
                }else if("deleteGoodsPage".equals((oper))){
                    GoodsPage.deleteGoodsPage();
                }else if("addGoodsPage".equals(oper)){
                    GoodsPage.addGoodsPage();
                }
            }else if("N".equals(choice)||("n".equals(choice))){
                MainPage.MaintenancePage();
            }
            System.out.println("\n输入有误！请重新输入.");
        }while (true);
    }
    public static void choiceSalesManNext(String oper)
    {
        do
        {
            System.out.println("是否继续进行-当前操作:(Y/N)");
            String choice = ScannerChoice.ScannerInfoString();

            if ( "y".equals(choice) || "Y".equals(choice) )
            {
                //下面的嵌套if-else 是让用户选择继续操作当前步骤所跳转到指定页面。（因为不同函数调用，跳转的指定函数不同）
                if ("updateSalesMan".equals(oper))
                {
                    SalesManPage.updateSalesManPage();
                }else if ("deleteSalesMan".equals(oper))
                {
                    SalesManPage.deleteSalesManPage();
                }else if ("addSalesMan".equals(oper))
                {
                    SalesManPage.addSalesManPage();
                }else if ("querySalesMan".equals(oper))
                {
                    SalesManPage.querySalesManPage();
                }
                //上面的嵌套结束
            }else if ("N".equals(choice) || "n".equals(choice))
            {
                MainPage.salesManManagementPage();
            }
            System.err.println("\t输入有误！");
        } while (true);
    }
}

