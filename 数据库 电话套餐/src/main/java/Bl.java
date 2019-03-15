import sun.security.krb5.internal.crypto.RsaMd5CksumType;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;


public class Bl {
    private static SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static  String getPackageNameById(int packageId){
        ResultSet resultSet=Data.getPackageOfId(packageId);
        String packageName="";
        try {
            resultSet.next();
            packageName=resultSet.getString("name");
            Data.con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return packageName;
    }

    private static  String getUserNameById(int userId){
        ResultSet resultSet=Data.getUserOfId(userId);
        String userName="";
        try {
            resultSet.next();
            userName=resultSet.getString("name");
            Data.con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userName;
    }

    public static ArrayList<String[]> getOrdersByUserId(int userId){
          ResultSet resultSet=Data.getOrdersByUserId(userId);
          ArrayList<String[]> orders=new ArrayList<String[]>();
          String userName=getUserNameById(userId);
          try {
              while (resultSet.next()) {
                  String[] order={"","","","",""};
                  //order[0]:userId      order[1]:userName   order[2]:packageName
                  // order[3]:orderTime   order[4]:ifImmediateExe
                  int packageId=resultSet.getInt("package_id");
                  String packageName=getPackageNameById(packageId);
                  Date orderDate=resultSet.getDate("order_time");
                  String orderTime=sDateFormat.format(orderDate);
                  Boolean immediateExe=resultSet.getBoolean("immediate_execute");

                  order[0]=String.valueOf(userId);
                  order[1]=userName;
                  order[2]=packageName;
                  order[3]=orderTime;
                  order[4]=String.valueOf(immediateExe);
                  orders.add(order);
              }
              Data.con.close();
          }catch (SQLException e){
              e.printStackTrace();
          }
          return orders;
      }

      public static void addOrderOfUser(int userId,int packageId,boolean immediateExecute){
        //一定要先增加一条11月份才生效的订单
            Data.insertOrder(userId,packageId,immediateExecute);
            System.out.println("order successfully!");
      }

      public static void cancelOrder(int userId,int packageId,String orderTime){
            ResultSet resultSet=Data.getUniqueOrder(userId,packageId,orderTime);
            boolean canBeCanceled=true;
            if(resultSet==null){
                canBeCanceled=false;
                System.out.println("no the order");
            }
            try {
                resultSet.next();
                String startTime=resultSet.getString("start_time");
                Calendar cal=Calendar.getInstance();
                Date now=cal.getTime();
                Date orderStartTime=sDateFormat.parse(startTime);
                if(orderStartTime.getTime()<now.getTime()){
                    canBeCanceled=false;
                    System.out.println("The package "+packageId+" of the user "+userId+" has been started and can not be canceled");
                }
                Data.con.close();
            }catch (ParseException e1){
              e1.printStackTrace();
            }catch (SQLException e2){
              e2.printStackTrace();
            }

            if(canBeCanceled){
                Data.deleteOrder(userId,packageId,orderTime);
                System.out.println("cancel order successfully!");
            }
    }


    public static float[] getMonthBillOfUser(int userId,int month){
        //if the month is the current month,get the bill of the month by now
        //if the month is before the current month, get the bill of the whole month
        Calendar cal=Calendar.getInstance();
        int currentMonth=cal.get(Calendar.MONTH)+1;
        Date endTime=null;
        if(currentMonth==month){
            endTime=cal.getTime();
        }else if(currentMonth<month){
            System.out.println("the month is over the current month");
            return null;
        }else{
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            endTime=cal.getTime();
        }

        float call_num=0;
        float call_fee=0;
        float message_num=0;
        float message_fee=0;
        float local_num=0;
        float local_fee=0;
        float nationwide_num=0;
        float nationwide_fee=0;

        try{
            ResultSet record=Data.getRecordOfUserAndMonth(userId,month);
            record.next();
            call_num=record.getFloat("call_num");
            message_num=record.getFloat("message_num");
            local_num=record.getFloat("local_num");
            nationwide_num=record.getFloat("nationwide_num");
            Data.con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        call_fee=getFeeOfUser(userId,endTime,"call");
        message_fee=getFeeOfUser(userId,endTime,"message");
        local_fee=getFeeOfUser(userId,endTime,"local");
        nationwide_fee=getFeeOfUser(userId,endTime,"nationwide");

        float[] result={call_num,call_fee,message_num,message_fee,local_num,local_fee,nationwide_num,nationwide_fee};
        return result;
    }

    public static ArrayList<int[]> getPackageCostOfUserAndType(int userId,Date endTime,String type) {
        ArrayList<int[]> packageRecords=new ArrayList<int[]>();
        Date beginningTime=null;
        Calendar cal=Calendar.getInstance();
        cal.setTime(endTime);
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        beginningTime=cal.getTime();
        ResultSet resultSet = Data.getStartPackagesRecordsOfUserIdByTime(userId, sDateFormat.format(beginningTime), sDateFormat.format(endTime));
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int packageId = resultSet.getInt("package_id");
                    ResultSet packageSet = Data.getPackageOfId(packageId);
                    packageSet.next();
                    String typeOfThePackage = packageSet.getString("type");
                    if (typeOfThePackage.equals(type)||typeOfThePackage.equals("all")) {
                        //the type if the package is the type we want to get
                        int packagePrice=packageSet.getInt("price");
                        int[] packageRecord={packageId,packagePrice};
                        packageRecords.add(packageRecord);
                    }
                    }
                Data.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return packageRecords;
    }

    public static ArrayList<int[]> getPackageCostOfUserAndMonth(int userId,int month) {
        ArrayList<int[]> packageRecords=new ArrayList<int[]>();

        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.MONTH,month-1);
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Date beginningTime=cal.getTime();

        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        Date endTime=cal.getTime();

        ResultSet resultSet = Data.getStartPackagesRecordsOfUserIdByTime(userId, sDateFormat.format(beginningTime), sDateFormat.format(endTime));
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int packageId = resultSet.getInt("package_id");
                    ResultSet packageSet = Data.getPackageOfId(packageId);
                    packageSet.next();
                    int packagePrice=packageSet.getInt("price");
                    int[] packageRecord={packageId,packagePrice};
                    packageRecords.add(packageRecord);
                }
                Data.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return packageRecords;
    }

    public static float getFeeOfUser(int userId,Date endTime,String type){
        //当该用户套餐生效时间重叠时，支持叠加
          Date beginningTime=null;
          Calendar cal=Calendar.getInstance();
          cal.setTime(endTime);
          int month= cal.get(Calendar.MONTH)+1;
          cal.set(Calendar.DAY_OF_MONTH,1);
          cal.set(Calendar.HOUR_OF_DAY,0);
          cal.set(Calendar.MINUTE,0);
          cal.set(Calendar.SECOND,0);
          beginningTime=cal.getTime();

          ArrayList<float[]> usingRecords=new ArrayList<float[]>();
          //String[]  0:start_num  1:free_num  2:price
          //the first record of the array: beginning of the month, no package, String[]={0,0,priceOfNoPackage}
          //the last record of the array: endTime, no package, String[]={currentNum,0,0}
          float standardPrice=0;
          try {
              ResultSet packageSet = Data.getPackageOfId(1);
              //get the standard price of each
              packageSet.next();
              standardPrice=packageSet.getFloat(type+"_fee");
              Data.con.close();
          }catch (SQLException e){
              e.printStackTrace();
          }
          float[] firstRecord={0,0,standardPrice};
          usingRecords.add(firstRecord);

          ResultSet resultSet=Data.getStartPackagesRecordsOfUserIdByTime(userId,sDateFormat.format(beginningTime),sDateFormat.format(endTime));
          if(resultSet!=null) {
              try {
                  while (resultSet.next()) {
                      int packageId = resultSet.getInt("package_id");
                      ResultSet packageSet = Data.getPackageOfId(packageId);
                      packageSet.next();
                      String typeOfThePackage = packageSet.getString("type");
                      if (typeOfThePackage.equals(type)||typeOfThePackage.equals("all")) {
                          //the type if the package is the type we want to get
                          int freeNum=packageSet.getInt("free_"+type);
                          float price=packageSet.getFloat(type+"_fee");
                          int currentCost=resultSet.getInt("current_"+type);
                          float[] record = {currentCost,freeNum,price};
                          //float[]  0:start_num  1:free_num  2:price
                          usingRecords.add(record);
                      }

                  }
                  Data.con.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }

          ResultSet recordSet=Data.getRecordOfUserAndMonth(userId,month);
          int currentNum=0;
          try{
              recordSet.next();
              currentNum=recordSet.getInt(type+"_num");
              Data.con.close();
          }catch (SQLException e){
              e.printStackTrace();
          }
          float[] finalRecord={currentNum,0,0};
          usingRecords.add(finalRecord);

          float fee=calculateFee(usingRecords);
          return fee;
    }

    private static float getTheCurrentMinPrice(ArrayList<Float> prices){
        float result=1000;
        if(prices.size()==1){
            result=prices.get(0);
        }else {
            //when there exists package, the standard price will not join the competition of minPrice
            //because there has been package now, not without package
            for (int i = 1; i <= prices.size() - 1; i++) {
                if (prices.get(i) < result) {
                    result = prices.get(i);
                }
            }
        }
        return result;
    }

    private static float calculateFee(ArrayList<float[]> usingRecords){
        ArrayList<Float> prices=new ArrayList<Float>();
        //the start_num column should grow, because when select packagestartrecord, it has been ordered
        float leftFreeNum=0;
        float wholeFee=0;
        for(int i=0;i<=usingRecords.size()-2;i++){
            float[] record=usingRecords.get(i);
            //float[]  0:start_num  1:free_num  2:price
            float[] nextRecord=usingRecords.get(i+1);

            float price = record[2];
            if(price!=0){
                //the error:price==0 should not exist because i<=usingRecords.size() and only the last record has price=0
                prices.add(price);
            }

            float nowNum=record[0];
            float nextNum=nextRecord[0];
            float usedNum=nextNum-nowNum;
            if(usedNum<0){
                //The error should not exist.
                System.out.println("Data-using records of the user are not ordered by time!!! ");
                return 0;
            }
            float freeNum=record[1];
            float totalFreeNum=leftFreeNum+freeNum;
            if(totalFreeNum>=usedNum){
                //no fee
                leftFreeNum=totalFreeNum-usedNum;
            }else {
                leftFreeNum=0;
                float payNum=usedNum-totalFreeNum;
                float minPrice=getTheCurrentMinPrice(prices);
                float fee=payNum*minPrice;
                wholeFee=wholeFee+fee;
            }
        }
        return wholeFee;
    }

}
