package utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    public static String getCurrentTime(){
        String time=df.format(new Date());// new Date()为获取当前系统时间
        return time;
    }
    /*
    public static void main(String[] args){
        Date date=new Date();
        System.out.println(df.format(date));
    }*/
    public static Date getDateOfStringTime(String time){
        try {
            Date date = df.parse(time);
            return date;
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getTimeOfDay(String day){
        String time=" 00:00:00";
        return day+time;
    }

    public static Boolean ifCurrentAfterTargetTime(String targetTime){
        Boolean ifAfter=false;
        try {
            System.out.println(targetTime);
            Date targetDate = df.parse(targetTime);
            Date currentDate = new Date();
            System.out.print(targetDate+"  "+currentDate);
            if(targetDate.before(currentDate)){
                ifAfter=true;
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return ifAfter;
    }

    public static String getCurrentTerm(){
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期

        String yearStr = calendar.get(Calendar.YEAR)+"";//获取年份
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        if((3<=month)&&(month<=8)){
            String term=yearStr+" spring";
            return term;
        }else{
            String term=yearStr+" autumn";
            return term;
        }
    }

    /*
    public static void main(String[] args){
        ifCurrentAfterTargetTime("2019-12-30 00:00:00.0");
        System.out.print(getCurrentTime());
    }*/
}
