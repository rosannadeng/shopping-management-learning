package Page;

import dao.SalesManDao;
import entity.SalesMan;
import util.QueryPrint;
import util.ScannerChoice;
import java.util.ArrayList;

public class SalesManPage extends ScannerChoice {
    public static void addSalesManPage(){
        System.out.println("Adding Sales Man");
        System.out.println("Input the name: ");
        String smName = ScannerInfoString();
        System.out.println("Input the password");
        String sPassword = ScannerInfoString();

        SalesMan salesMan = new SalesMan(smName,sPassword);
        boolean bool =new SalesManDao().addSalesMan(salesMan);
        if (bool){
            System.out.println("Successful");
        }else{
            System.out.println("Sorry, we lost your info");
        }
        choiceSalesManNext("addSalesManPage");

    }
    public static void updateSalesManPage(){
        System.out.println("You are updating Sales Man Info");
        System.out.println("Input the name");
        String smName = ScannerInfoString();
        ArrayList<SalesMan> salesmanList= new QueryPrint().querySalesMan(smName);
        if (salesmanList.size()<=0){
            System.out.println("No Info for this Person");
            choiceSalesManNext("updateSalesMan");
        }else{
            System.out.println("\t\t\t售货员信息\n\n");
            System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");

            SalesMan salesMan = salesmanList.get(0); //上面的精确查找中，只能返回一组数值。无需遍历！
            System.out.println("\t"+salesMan.getSId()+"\t\t\t"+salesMan.getSName()+"\t\t\t"+salesMan.getSPassword());
            System.out.println();
            System.out.println("\n--------请选择您要更改的内容\n");
            System.out.println("\t1.更改售货员-姓名");
            System.out.println("\t2.更改售货员-密码");
            do{
                String choice = ScannerInfoString();
                String regex = "[0-2]";
                if (choice.matches(regex)) {
                    int info = Integer.parseInt(choice);
                    switch (info) {
                        case 0:
                            MainPage.salesManManagementPage();
                            break;
                        case 1:
                            System.out.println("update the name:");
                            String newSmName = ScannerInfoString();
                            SalesMan salesmanname = new SalesMan(salesMan.getSId(), newSmName, null);
                            boolean boolname = new SalesManDao().updateSalesMan(1, salesmanname);
                            if (boolname) {
                                System.out.println("Success for changing name");
                            } else {
                                System.out.println("Dismiss");
                            }
                            choiceSalesManNext("updateSalesMan");

                            break;
                        case 2:
                            System.out.println("update the password:");
                            String newPassword = ScannerInfoString();
                            SalesMan SMPassword = new SalesMan(salesMan.getSId(), null, newPassword);
                            boolean boolpassword = new SalesManDao().updateSalesMan(2, SMPassword);
                            if (boolpassword) {
                                System.out.println("Success for changing password");
                            } else {
                                System.out.println("Dismiss");

                            }
                            choiceSalesManNext("updateSalesMan");
                            break;
                        default:
                            break;
                    }
                }
                    System.out.println("\t Wrong Input");
                    System.out.println("\n请选择选项.或者按 0 返回上一级菜单.");

            }while(true);

        }
    }
    public static void deleteSalesManPage(){
        System.out.println("You are deleting Sales Man Info");
        System.out.println("Input the name");
        String smName = ScannerInfoString();
        ArrayList<SalesMan> salesmanList= new QueryPrint().querySalesMan(smName);
        if (salesmanList.size()<=0){
            System.out.println("No Info for this Person");
            choiceSalesManNext("deleteSalesMan");
        }else{
            System.out.println("\t\t\t删除售货员信息\n\n");
            System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");
            for (int i =0; i <  salesmanList.size();i++){
                SalesMan salesMan =  salesmanList.get(i);
                System.out.println("\t"+salesMan.getSId()+"\t\t\t"+salesMan.getSName()+"\t\t\t"+salesMan.getSPassword());
                System.out.println();

            }
            do{
                System.out.println("\n确认删除该售货员：Y/N");
                String choice = ScannerInfoString();
                if ("y".equals(choice) || "Y".equals(choice))
                {
                    //进行刪除-数据库操作
                    boolean boolDeleteSalesMan = new SalesManDao().deleteSalesMan(smName);//調用刪除功能

                    if (boolDeleteSalesMan)
                    {
                        System.err.println("\t！！已成功刪除该售货员！！\n");
                    }else
                    {
                        System.err.println("\t！！刪除该售货员失敗！！");
                    }
                    choiceSalesManNext("deleteSalesMan");
                }else if ("N".equals(choice) || "n".equals(choice))
                {
                    MainPage.salesManManagementPage();
                }
                System.err.println("\t!!输入有误,请重新输入!!");
            } while (true);
        }
    }
    public static void querySalesManPage(){
        System.out.println("You are querying Sales Man Info");
        System.out.println("Input the name");
        String smName = ScannerInfoString();
        ArrayList<SalesMan> salesmanList= new QueryPrint().querySalesMan(smName);
        if (salesmanList.size()<=0){
            System.out.println("No Info for this Person");
        }else{
            System.out.println("\t\t\t所有售货员列表\n\n");
            System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");

            for (int i = 0,length = salesmanList.size(); i < length; i++)
            {
                SalesMan salesMan = salesmanList.get(i);
                System.out.println("\t"+salesMan.getSId()+"\t\t\t"+salesMan.getSName()+"\t\t\t"+salesMan.getSPassword());
                System.out.println();
            }
        }
        choiceSalesManNext("querySalesMan");
    }
    public static void displaySalesManPage(){
        ArrayList<SalesMan> salesmanList= new SalesManDao().displaySalesMan();
        if (salesmanList.size()<=0){
            System.out.println("No Info for salesman");
        }else{
            System.out.println("\t\t\t所有售货员列表\n\n");
            System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");

            for (int i = 0,length = salesmanList.size(); i < length; i++){
                SalesMan salesMan = salesmanList.get(i);
                System.out.println("\t"+salesMan.getSId()+"\t\t\t"+salesMan.getSName()+"\t\t\t"+salesMan.getSPassword());
                System.out.println();
            }
            do{
                System.out.println("\n\n输入 0 返回上一级菜单");
                String choice = ScannerInfoString();

                if ("0".equals(choice)){
                    MainPage.salesManManagementPage();
                }
                System.out.println("Wrong Input");
            }while (true);
        }

    }
}
