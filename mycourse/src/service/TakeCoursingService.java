package service;

import entity.Coursing;

import java.util.ArrayList;

public interface TakeCoursingService {
    public ArrayList<Coursing> getCoursingOfSid(String sid);
    public void updateAllCoursingStateChooseToTake();
    //每当获得myCoursing列表(展示选课结果)或者coursing(会展示选课数目）时，根据当时时刻更新coursing状态从choose到take，这一转化必须由程序自己比较时间触发
    public String chooseCoursing(String ccid,String sid);
    //查看选课内容后，按钮触发选课操作，返回成功类型（未开课并成功选择，已开课并成功选择——更新数据表）
    //开课前中选要更新choose表，开课后中选要更新choose和take
    //失败类型（已开课并无名额，已开课并已中选，未开课并已选择，已结课）
    public void cancelCoursing(String ccid,String sid);
    //删除take表记录并更新coursing的选课人数
    public Boolean ifChoosedOrTaken(String ccid,String sid);
    //开课前选择了就返回true，没选择返回false
    //开课后中选了就返回true，没中选返回false
}
