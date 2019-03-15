package com.example.summer.logic;

import com.example.summer.model.InferredRectangleMarkModel;
import com.example.summer.model.RecGroupMarksModel;
import com.example.summer.model.RectangleMarkModel;
import com.example.summer.utility.enums.MarkTypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiRecEstimator {
    public static List<InferredRectangleMarkModel> processUpdate(SingleRecWmodel wm
            , RecGroupMarksModel newMarks
            ,List<InferredRectangleMarkModel> inferredRecs){
        //System.out.println(inferredRecs);
        List<RectangleMarkModel> sortedRecs = RecSortUtility.sortRecs(newMarks.getRectangleMarks(), inferredRecs);
        //System.out.println(sortedRecs);
        if (inferredRecs==null){inferredRecs = new ArrayList<>();}
        //更新传入的mark的位置信息
        newMarks.setRectangleMarks( (ArrayList<RectangleMarkModel>) sortedRecs);

        int k = Math.max(newMarks.getRectangleMarks().size(), inferredRecs.size());
        List<InferredRectangleMarkModel> ret = new ArrayList<>();
        for (int i=0; i<k; i++){
            InferredRectangleMarkModel model = i<inferredRecs.size()
                    ?  inferredRecs.get(i): null;
            if (sortedRecs.get(i)!=null){
                model = (InferredRectangleMarkModel) SingleRecEstimator.processUpdate(wm, sortedRecs.get(i), model);
                ret.add(model);
            }else {
                ret.add(model);
            }
        }
        return ret;
    }

    public static List<InferredRectangleMarkModel> finalUpdate(List<SingleRecWmodel> wmodels
            , List<RecGroupMarksModel> marks
            , List<InferredRectangleMarkModel> inferredMarks
            , int [] creditGrade){
        List<InferredRectangleMarkModel> ret = new ArrayList<InferredRectangleMarkModel>();

        for (int i=0; i<inferredMarks.size(); i++){
            List<RectangleMarkModel> markList = new ArrayList<RectangleMarkModel>();
            List<SingleRecWmodel> wms = new ArrayList<>();

            for (int j=0; j<marks.size(); j++){
                List<RectangleMarkModel> userMarks
                        = marks.get(j).getRectangleMarks();
                if (i < userMarks.size() && userMarks.get(i)!=null){
                    markList.add(userMarks.get(i));
                    wms.add(wmodels.get(j));
                }
            }
            InferredRectangleMarkModel retRec = (InferredRectangleMarkModel) SingleRecEstimator
                    .finalUpdate(wms, markList
                            , inferredMarks.get(i), creditGrade, MarkTypeEnum.MULTI_REC);
            ret.add(retRec);
        }
        return ret;
    }


    private static int findK(List<List<RectangleMarkModel>> marks){
        HashMap<Integer, Integer> voteMap = new HashMap<Integer, Integer>();
        for (int i =0; i<marks.size(); i++){
            List<RectangleMarkModel> markModels = marks.get(i);
            int size = markModels.size();
            if (!voteMap.containsKey(size)){
                voteMap.put(size, 0);
            }
            voteMap.put(size, voteMap.get(size)+1);
        }
        //确定聚类的k
        int k = findMaxVote(voteMap);
        return k;
    }

    private static int findMaxVote(HashMap<Integer, Integer> map){
        int max = 0;
        int maxSize = 0;
        for (Integer k: map.keySet()){
            if (max < map.get(k)){
                max = map.get(k);
                maxSize = k;
            }
        }
        return maxSize;
    }
}
