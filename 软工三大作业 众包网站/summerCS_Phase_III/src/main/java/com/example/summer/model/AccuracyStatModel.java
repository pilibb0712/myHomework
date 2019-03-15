package com.example.summer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccuracyStatModel {
    private int count;
    private double acc;

    public static List<AccuracyStatModel> generate(String username){
        WorkerModel workerModel
                = WorkerModel.queryWorkerModelByUserName(username);
        int labelCnt = workerModel.getLabelCnt();
        double labelAcc = workerModel.getLabelAcc()/(double)( labelCnt);
        int singleRecCnt = workerModel.getSingleRecCnt();
        double singleRecAcc = workerModel.getSingleRecAcc()/singleRecCnt;
        int multiRecCnt = workerModel.getMultiRecCnt();
        double multiRecAcc = workerModel.getMultiRecAcc()/multiRecCnt;
        List<AccuracyStatModel>accs = Arrays.asList(new AccuracyStatModel[] {
                new AccuracyStatModel(labelCnt, labelAcc),
                new AccuracyStatModel(singleRecCnt, singleRecAcc),
                new AccuracyStatModel(multiRecCnt, multiRecAcc)
        });
        return accs;
    }

    @Override
    public String toString(){
        return this.count+":"+this.acc;
    }
}
