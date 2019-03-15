package com.example.summer.web;

import com.example.summer.model.ProjectStatisticModel;
import com.example.summer.model.UserModel;
import com.example.summer.service.AdminService;
import com.example.summer.service.impl.AdminServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AdminController {

    private AdminService adminService = new AdminServiceImpl();

    /**
     * 得到当前的用户数目
     * */
    @RequestMapping("/admin/userNum")
    public int queryUserNum(){
        return adminService.queryUserNum();
    }

    /**
     * 得到当前的项目统计信息
     * @return 项目的统计信息
     * */
    @RequestMapping("/admin/projectStatistic")
    public ProjectStatisticModel queryProjectStatisticData(){
        return adminService.queryProjectStatisticData();
    }

    /**
     * 管理员关闭项目
     * @param projectId 项目Id
     * @return boolean
     */
    @RequestMapping("/admin/closeProject")
    public boolean closeProject(String projectId,String closeTime){
        return adminService.adminCloseProject(projectId,closeTime);
    }

    /**
     * 管理员登录
     * @author Mr.Wang
     * @param password 密码
     * @return boolean
     */
    @RequestMapping("/admin/login")
    public boolean login(String password){
        return adminService.adminLogin(password);
    }

    /**
     * 获得所有用户列表
     * @author Mr.Wang
     * @param () no param
     * @return java.util.ArrayList<com.example.summer.model.UserModel>
     */
    @RequestMapping("/admin/listUsers")
    public ArrayList<UserModel> listAllUsers() {
        return adminService.listAllUsers();
    }

    /**
     * 删除用户
     * @author Mr.Wang
     * @param username 用户名
     */
    @RequestMapping("/admin/deleteUser")
    public void deleteUser(String username) {
        adminService.deleteUser(username);
    }

    /**
     * 一定时间内注册人数
     * @author Mr.Wang
     * @param beginTime yyyy-MM-dd
     * @param endTime yyyy-MM-dd
     * @return java.util.ArrayList<java.lang.Integer>
     */
    @RequestMapping("/admin/listRegisterNum")
    public ArrayList<Integer> listRegisterNum(String beginTime, String endTime) {
        return adminService.listRegisterNum(beginTime, endTime);
    }

    /**
     * 已完成，未完成 的项目的标注类型的各自比例
     * eg. [[0.1, 0.2, 0.3, 0.4], [0.4, 0.3, 0.2, 0.1]]
     * @author Mr.Wang
     * @param () none
     * @return double[][]
     */
    @RequestMapping("/admin/getPercentageOfMarkType")
    public double[][] getPercentageOfMarkType() {
        return adminService.getPercentageOfMarkType();
    }
}
