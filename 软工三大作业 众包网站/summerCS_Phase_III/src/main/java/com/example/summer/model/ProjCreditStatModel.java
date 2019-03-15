package com.example.summer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjCreditStatModel {
    private int credit;
    private int count;


    public static List<ProjCreditStatModel> generate(String projectId, String username){
        List<ProjCreditStatModel> ret = new ArrayList<>();
        ImageListModel imageListModel = ImageListModel.generateAllByProjIdAndUsername(projectId, username);
        List<Integer> credits= imageListModel.extractEveryWorkerCredit(username);
        Map<Integer, Integer> map = new HashMap<>();
        ProjectModel projectModel =  ProjectModel.queryProjectById(projectId);
        int[] keys = ((MultiCreditProjectModel)projectModel).getCredits();
        for (int i : keys){
            map.put(i, 0);
        }
        map.put(-keys[0], 0);

        for (Integer i: credits){
            if (map.get(i)==null){continue;}
            map.put(i, map.get(i)+1);
        }

        for (HashMap.Entry<Integer,Integer> entry: map.entrySet()){
            int key= entry.getKey();
            int val= entry.getValue();
            ret.add(new ProjCreditStatModel(key, val));
        }
        return ret;
    }
}
