package com.example.summer.service.impl;

import com.example.summer.model.ProjectModel;
import com.example.summer.model.ProjectStatisticModel;
import com.example.summer.model.UserModel;
import com.example.summer.service.AdminService;
import com.example.summer.service.LauncherService;

import java.util.ArrayList;

public class AdminServiceImpl implements AdminService{
    @Override
    public int queryUserNum() {
        return UserModel.listAllUsers().size();
    }

    @Override
    public ProjectStatisticModel queryProjectStatisticData() {
        return ProjectStatisticModel.generate();
    }

    @Override
    public boolean adminCloseProject(String projectId,String closeTime){
        LauncherService service = new LauncherServiceImpl();
        return service.closeProject(projectId,closeTime);
    }

    @Override
    public boolean adminLogin(String newPw){
        if(newPw.equals("root")){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<UserModel> listAllUsers() {
        if(UserModel.listAllUsers()!=null){
            return UserModel.listAllUsers();
        }
        return null;
    }

    @Override
    public void deleteUser(String username) {
        UserModel.deleteUser(username);
    }

    @Override
    public ArrayList<Integer> listRegisterNum(String beginTime, String endTime) {
        ArrayList<Integer> registerNum = UserModel.listRegisterNum(beginTime, endTime);
        if(registerNum!=null){
            return registerNum;
        }
        return null;
    }

    @Override
    public double[][] getPercentageOfMarkType() {
        if(ProjectModel.getPercentageOfMarkType()!=null){
            return ProjectModel.getPercentageOfMarkType();
        }
        return null;
    }
}
