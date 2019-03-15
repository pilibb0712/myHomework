package com.example.summer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProjectStatisticModel {
    private int totalNum ;
    private int doneNum;
    private int doingNum;
    private ArrayList<ProjectCreditModel> credits;

    /***************************************************************************
     *                                                                         *
     * static methods                                                            *
     *                                                                         *
     **************************************************************************/
    /**
     * 生成当前的所有项目的统计信息
     * @return 所有项目的统计信息
     * */
    public static ProjectStatisticModel generate(){
        ArrayList<ProjectModel> projectList = ProjectModel.listAllProjects();
        int totalNum = projectList.size();
        int doingNum = calcDoingNum(projectList);
        int doneNum = totalNum - doingNum;
        ArrayList<ProjectCreditModel> creditModels = getAllCredit();
        ProjectStatisticModel projectStatisticModel
                = ProjectStatisticModel.builder()
                .totalNum(totalNum)
                .doneNum(doneNum)
                .doingNum(doingNum)
                .credits(creditModels).build();
        return projectStatisticModel;
    }

    /**
     * 计算完成项目数目
     * @param projectList 需要计算的项目清单
     * */
    private static int calcDoingNum(ArrayList<ProjectModel> projectList){
        int doneNum = 0;
        for(ProjectModel project: projectList){
            if(project.isCanBeJoined()){doneNum++;}
        }
        return doneNum;
    }

    /**
     * 得到该项目的所有积分
     * @author Mr.Wang
     * @param projectId 项目Id
     * @return int
     */
    private static int getCreditOfProject(String projectId){
        ProjectRankModel projectRankModel = ProjectRankModel.generate(projectId);
        return projectRankModel.getProjectCredit();
    }

    /**
     * 得到所有项目的所有积分情况
     * @author Mr.Wang
     * @param () no param
     * @return int
     */
    private static ArrayList<ProjectCreditModel> getAllCredit(){
        ArrayList<ProjectModel> projectModels = ProjectModel.listCanbeJoinedProjectInfos();
        ArrayList<ProjectCreditModel> result = new ArrayList<>();

        if(projectModels != null){
            for(ProjectModel project : projectModels){
                String projectId = project.getProjectId();
                int credit = getCreditOfProject(projectId);
                ProjectCreditModel creditModel = new ProjectCreditModel(projectId, credit);
                result.add(creditModel);
            }
            return result;
        }
        return null;
    }
}
