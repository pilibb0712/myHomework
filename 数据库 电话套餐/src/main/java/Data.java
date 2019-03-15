import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Data {
    public static Connection con;
    private static Statement statement;
    private static SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static void connect(){
        String driver="com.mysql.jdbc.Driver";
        //这里我的数据库是gradethree2
        String url="jdbc:mysql://localhost:3306/gradethree2";
        String user="root";
        String password="Bb199861317";
        try {
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                statement = con.createStatement();
            }
        }catch (SQLException e) {
            System.out.println("fail to connect database");
        }
    }

    public static ResultSet getOrdersByUserId(int userId){
        connect();
        ResultSet resultSet=null;
        String sql="select * from orders where user_id="+String.valueOf(userId);
        try {
            resultSet = statement.executeQuery(sql);
            if(resultSet==null){
                System.out.println("fail to select orders data");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet getUniqueOrder(int userId,int packageId,String orderTime){
        connect();
        String sql="select * from orders where user_id="+String.valueOf(userId)+" and package_id="+String.valueOf(packageId)+" and order_time='"+orderTime+"'";
        ResultSet resultSet=null;
        try {
            resultSet = statement.executeQuery(sql);
            if(resultSet==null){
                System.out.println("fail to select orders data");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    private static String[] getTimes(boolean immediateExe){
        String[] result={"",""};
        //result[0]:ordertime    result[1]:starttime
        Calendar cal=Calendar.getInstance();
        Date orderDate=cal.getTime();
        result[0]=sDateFormat.format(orderDate);
        if(immediateExe){
            result[1]=result[0];
        }else{
            cal.add(Calendar.MONTH,1);
            cal.set(Calendar.DAY_OF_MONTH,1);
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            Date startDate=cal.getTime();
            result[1]=sDateFormat.format(startDate);
        }
        return result;
    }

    public static void insertOrder(int userId,int packageId,boolean immediateExecute){
        connect();
        String[] times=getTimes(immediateExecute);
        String orderTime=times[0];
        String startTime=times[1];
        String sql="insert into orders values (0,"+String.valueOf(userId)+","+String.valueOf(packageId)+",'"+orderTime+"',"+String.valueOf(immediateExecute)+",'"+startTime+"')";
        try {
            statement.executeUpdate(sql);
            //!!!insert, update need to use executeUpdate
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(immediateExecute){
            insertPackageStartRecord(userId,packageId,startTime,5,10,50,50);
        }
        //如果立刻生效，要增加套餐生效记录！！！
        //默认使用量：5,10,50,50
        //下个月才生效的套餐，会有一个每月第一天的查询方法将所有尚未生效的套餐生效，即加入套餐生效记录
        //默认使用量：0,0,0,0
    }

    public static void deleteOrder(int userId,int packageId,String orderTime){
        connect();
        String deleteSql="delete from orders where user_id="+String.valueOf(userId)+" and package_id="+String.valueOf(packageId)+" and order_time='"+orderTime+"'";
        try {
            statement.executeUpdate(deleteSql);
            con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    public static ResultSet getUserOfId(int userId){
        connect();
        ResultSet resultSet=null;
        String sql="select * from users where id="+String.valueOf(userId);
        try {
            resultSet = statement.executeQuery(sql);
            if(resultSet==null){
                System.out.println("fail to select users data");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet getPackageOfId(int packageId){
        connect();
        ResultSet resultSet=null;
        String sql="select * from packages where id="+String.valueOf(packageId);
        try {
            resultSet = statement.executeQuery(sql);
            if(resultSet==null){
                System.out.println("fail to select packages data");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet getRecordOfUserAndMonth(int userId,int month){
        connect();
        ResultSet resultSet=null;
        String sql="select * from records where user_id="+String.valueOf(userId)+" and month="+String.valueOf(month);
        try {
            resultSet = statement.executeQuery(sql);
            if(resultSet==null){
                System.out.println("fail to select records data");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet getPackageStartRecord(int userId,int packageId,String startTime){
        connect();
        ResultSet resultSet=null;
        String sql="select * from packagestartrecords where user_id="+String.valueOf(userId)+" and package_id="+String.valueOf(packageId)+" and start_time='"+startTime+"'";
        try {
            resultSet = statement.executeQuery(sql);
            if(resultSet==null){
                System.out.println("fail to select packagestartrecords data");
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet getStartPackagesRecordsOfUserIdByTime(int userId,String beginTime,String endTime){
        //生效时间或者失效时间在月初到time这个范围内即符合要求可以返回
        connect();
        String sql="select * from packagestartrecords where user_Id="+String.valueOf(userId)+" and start_time between cast('"+beginTime+"' as datetime) and cast('"+endTime+"' as datetime) order by start_time asc ";
        ResultSet resultSet=null;
        try {
            resultSet = statement.executeQuery(sql);
            if(resultSet==null){
                System.out.println("fail to select packagestartrecords data");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }


    public static void insertPackageStartRecord(int userId,int packageId,String startTime,int call,int message,int local,int nationwide){
        connect();
        String sql="insert into packagestartrecords values (0,"+String.valueOf(userId)+","+String.valueOf(packageId)+",'"+startTime+"',"+String.valueOf(call)+","+String.valueOf(message)+","+String.valueOf(local)+","+String.valueOf(nationwide)+")";
        try {
            statement.executeUpdate(sql);
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
