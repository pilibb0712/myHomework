import sun.util.logging.PlatformLogger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args){
        //long startTime=System.currentTimeMillis();   //获取开始时间


        //建议逐一调用各操作方法，而不是一下子全部执行
        //因为没有准备11月的数据，故取消订单的操作需要依赖实时订购的订单数据
        //getOrdersShow();
        //orderPackageShow();
        //cancelOrderShow();
        //callFeeShow();
        //phoneDataFeeShow();
        //monthBillShow();


        //long endTime=System.currentTimeMillis(); //获取结束时间
        //System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }
    public static void getOrdersShow(){
        //通过使用者id获得订购记录--获得2号使用者的记录
        //user_id:1,2,3,4,5,6,7
        int userId=2;
        ArrayList<String[]> orderRecords=Bl.getOrdersByUserId(userId);
        if(orderRecords==null){
            System.out.println("The user has no order record.");
        }else{
            for(int i=0;i<=orderRecords.size()-1;i++){
                String[] order=orderRecords.get(i);
                //order[0]:userId      order[1]:userName   order[2]:packageName
                // order[3]:orderTime   order[4]:ifImmediateExe
                System.out.println("userId: "+order[0]);
                System.out.println("userName: "+order[1]);
                System.out.println("packageName: "+order[2]);
                System.out.println("orderTime: "+order[3]);
                System.out.println("immediateExe: "+order[4]);
                System.out.println();
            }
        }
        System.out.println();
    }
    public static void orderPackageShow(){
        //通过使用者id、套餐id和是否立即生效进行订购
        //package_id:1(没有任何套餐），2（话费套餐），3（短信套餐），4（省内流量套餐），5（国内流量套餐），6（综合套餐）
        int userId1=3;
        int userId2=5;
        int packageId1=5;
        int packageId2=3;
        Bl.addOrderOfUser(userId1,packageId1,false);
        Bl.addOrderOfUser(userId2,packageId2,true);
        System.out.println();
    }
    public static void cancelOrderShow(){
        //通过使用者id和套餐id以及订购时间进行退订，如果该套餐已生效，将无法退订
        //建议使用刚才订购测试的数据来进行取消订购测试，因为没有建立11月的基础数据
        int userId1=3;
        int userId2=5;
        int packageId1=5;
        int packageId2=3;
        String orderTime="2018-10-27 21:38:35";
        Bl.cancelOrder(userId1,packageId1,orderTime);//未生效
        Bl.cancelOrder(userId2,packageId2,orderTime);//已生效
    }
    public static void callFeeShow(){
        //通过使用者id和查看类型（‘通话’）查看此时通话花费
        //建议将time设为10月下旬，有完整基础数据
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.MONTH,9);
        cal.set(Calendar.DAY_OF_MONTH,26);
        Date time=cal.getTime();
        int userId1=1;
        int userId2=2;
        float callCost1=Bl.getFeeOfUser(userId1,time,"call");//1号用户未订购任何套餐
        System.out.println("user_id: "+userId1);
        System.out.println("time: "+sDateFormat.format(time));
        System.out.println("call_cost by now: "+callCost1+" yuan");
        ArrayList<int[]> packageCosts1=Bl.getPackageCostOfUserAndType(userId1,time,"call");
        if(packageCosts1.size()==0){
            System.out.println("no call_package");
        }
        for(int i=0;i<=packageCosts1.size()-1;i++){
            int[] packageCost1=packageCosts1.get(i);
            System.out.println("package_id: "+packageCost1[0]+"     package_price: "+packageCost1[1]+"yuan");
        }
        System.out.println();

        float callCost2=Bl.getFeeOfUser(userId2,time,"call");//2号用户订购过通话套餐
        System.out.println("user_id: "+userId2);
        System.out.println("time: "+sDateFormat.format(time));
        System.out.println("call_cost by now: "+callCost2+" yuan");
        ArrayList<int[]> packageCosts2=Bl.getPackageCostOfUserAndType(userId2,time,"call");
        if(packageCosts2.size()==0){
            System.out.println("no call_package");
        }
        for(int i=0;i<=packageCosts2.size()-1;i++){
            int[] packageCost2=packageCosts2.get(i);
            System.out.println("package_id: "+packageCost2[0]+"     package_price: "+packageCost2[1]+"yuan");
        }
        System.out.println();

    }
    public static void phoneDataFeeShow(){
        //通过使用者id和查看类型（‘省内流量’）查看此时省内流量花费
        //建议将time设为10月下旬，有完整基础数据
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.MONTH,9);
        cal.set(Calendar.DAY_OF_MONTH,26);
        Date time=cal.getTime();
        int userId1=1;
        int userId2=4;

        float localCost1=Bl.getFeeOfUser(userId1,time,"local");//1号用户未订购任何套餐
        float nationwideCost1=Bl.getFeeOfUser(userId1,time,"nationwide");//1号用户未订购任何套餐
        System.out.println("user_id: "+userId1);
        System.out.println("time: "+sDateFormat.format(time));
        System.out.println("local_cost by now: "+localCost1+" yuan");
        System.out.println("nationwide_cost by now: "+nationwideCost1+" yuan");
        ArrayList<int[]> packageCosts1=Bl.getPackageCostOfUserAndType(1,time,"local");
        if(packageCosts1.size()==0){
            System.out.println("no local_data_package");
        }
        for(int i=0;i<=packageCosts1.size()-1;i++){
            int[] packageCost1=packageCosts1.get(i);
            System.out.println("package_id: "+packageCost1[0]+"     package_price: "+packageCost1[1]+"yuan");
        }
        ArrayList<int[]> packageCosts2=Bl.getPackageCostOfUserAndType(userId1,time,"nationwide");
        if(packageCosts2.size()==0){
            System.out.println("no nationwide_data_package");
        }
        for(int i=0;i<=packageCosts2.size()-1;i++){
            int[] packageCost2=packageCosts2.get(i);
            System.out.println("package_id: "+packageCost2[0]+"     package_price: "+packageCost2[1]+"yuan");
        }
        System.out.println();



        float localCost2=Bl.getFeeOfUser(userId2,time,"local");//4号用户订购过本地流量套餐
        float nationwideCost2=Bl.getFeeOfUser(userId2,time,"nationwide");//4号用户订购过本地流量套餐
        System.out.println("user_id: "+userId2);
        System.out.println("time: "+sDateFormat.format(time));
        System.out.println("local_cost by now: "+localCost2+" yuan");
        System.out.println("nationwide_cost by now: "+nationwideCost2+" yuan");
        ArrayList<int[]> packageCosts3=Bl.getPackageCostOfUserAndType(userId2,time,"local");
        if(packageCosts3.size()==0){
            System.out.println("no local_data_package");
        }
        for(int i=0;i<=packageCosts3.size()-1;i++){
            int[] packageCost3=packageCosts3.get(i);
            System.out.println("package_id: "+packageCost3[0]+"     package_price: "+packageCost3[1]+"yuan");
        }
        ArrayList<int[]> packageCosts4=Bl.getPackageCostOfUserAndType(userId2,time,"nationwide");
        if(packageCosts4.size()==0){
            System.out.println("no nationwide_data_package");
        }
        for(int i=0;i<=packageCosts4.size()-1;i++){
            int[] packageCost4=packageCosts4.get(i);
            System.out.println("package_id: "+packageCost4[0]+"     package_price: "+packageCost4[1]+"yuan");
        }
        System.out.println();
    }
    public static void monthBillShow(){
        //通过使用者id和月份查看该月账单
        //如果是本月，将显示月初到此时刻的月账单；如果是之前的月份，将显示之前一整个月的月账单
        //建议使用9月份，有完整基础数据
        int userId=6;
        int month=9;
        float[] monthBill=Bl.getMonthBillOfUser(userId,month);
        //monthBill 0:call_num 1:call_fee 2:message_num 3:message_fee 4:local_num 5:local_fee 6:nationwide_num 7:nationwide_fee
        System.out.println("user_id: "+userId);
        System.out.println("month: "+month);
        System.out.println("call_num: "+monthBill[0]);
        System.out.println("call_fee: "+monthBill[1]+" yuan");
        System.out.println("message_num: "+monthBill[2]);
        System.out.println("message_fee: "+monthBill[3]+" yuan");
        System.out.println("local_num: "+monthBill[4]);
        System.out.println("local_fee: "+monthBill[5]+" yuan");
        System.out.println("nationwide_num: "+monthBill[6]);
        System.out.println("nationwide_fee: "+monthBill[7]+" yuan");
        System.out.println();

        ArrayList<int[]> packageCosts=Bl.getPackageCostOfUserAndMonth(userId,month);
        if(packageCosts.size()==0){
            System.out.println("no any package");
        }
        for(int i=0;i<=packageCosts.size()-1;i++){
            int[] packageCost=packageCosts.get(i);
            System.out.println("package_id: "+packageCost[0]+"     package_price: "+packageCost[1]+"yuan");
        }
    }
}
