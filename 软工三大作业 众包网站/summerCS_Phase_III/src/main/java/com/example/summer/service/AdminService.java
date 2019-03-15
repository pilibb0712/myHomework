package com.example.summer.service;

import com.example.summer.model.ProjectStatisticModel;
import com.example.summer.model.UserModel;

import java.util.ArrayList;

public interface AdminService {

    /**
     * 得到当前的用户数目
     * */
    int queryUserNum();

    /**
     * 得到当前的项目统计信息
     * @return 项目的统计信息
     * */
    ProjectStatisticModel queryProjectStatisticData();

    /**
     * 管理员关闭项目
     * @author Mr.Wang
     * @param projectId 项目Id
     * @return boolean
     */
    boolean adminCloseProject(String projectId,String closeTime);

    /**
     * 管理员登录
     * @author Mr.Wang
     * @param newPw 密码
     * @return boolean
     */
    boolean adminLogin(String newPw);

    /**
     * @author Mr.Wang
     * @param () no param
     * @return java.util.ArrayList<com.example.summer.model.UserModel>
     */
    ArrayList<UserModel> listAllUsers();

    /**
     * @author Mr.Wang
     * @param username 用户名
     */
    void deleteUser(String username);

    /**
     * 一定时间内注册人数
     * @author Mr.Wang
     * @param beginTime yyyy-MM-dd
     * @param endTime yyyy-MM-dd
     * @return java.util.ArrayList<java.lang.Integer>
     */
    ArrayList<Integer> listRegisterNum(String beginTime, String endTime);

    /**
     * 已完成，未完成 的项目的标注类型的各自比例
     * [[0.1, 0.2, 0.3, 0.4], [0.4, 0.3, 0.2, 0.1]]
     * @author Mr.Wang
     * @param () none
     * @return double[][]
     */
    double[][] getPercentageOfMarkType();
}
