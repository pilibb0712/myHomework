package com.example.summer.model;

import java.util.List;

public class UserCreditsInProjectStatModel {
    public static List<Integer> queryUserCreditsInProj(String projectId,String username){
       ImageListModel imageListModel = ImageListModel.generateAllByProjIdAndUsername(projectId, username);
       return imageListModel.extractEveryWorkerCredit(username);
    }

}
