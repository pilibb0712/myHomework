package com.example.summer.model;

import com.example.summer.utility.enums.MarkTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AllWorkerAccInProjectStatModel {
    public AllWorkerAccInProjectStatModel() {
        super();
    }

    public static List<Double> queryWorkerAccs(String projectId, MarkTypeEnum type){
        ProjectRankModel rankModel = ProjectRankModel.generate(projectId);
        Set<String> keys = rankModel.getAllUsers();
        List<Double> ret = new ArrayList<>();
        for (String key: keys){
            double acc = 0;
            WorkerModel wm  = WorkerModel.queryWorkerModelByUserName(key);
            if (type==MarkTypeEnum.TAG){acc = wm.getLabelAcc();}
            else if(type==MarkTypeEnum.SINGLE_REC){acc = wm.getSingleRecAcc();}
            else if (type==MarkTypeEnum.MULTI_REC){acc = wm.getMultiRecAcc();}
            ret.add(acc);
        }
        return ret;
    }
}
