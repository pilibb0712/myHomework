package com.example.summer.model;

import com.example.summer.configInfo.EstimatorConfig;
import com.example.summer.dao.WorkerDao;
import com.example.summer.daoImpl.WorkerDaoImpl;
import com.example.summer.domain.WorkerPO;
import com.example.summer.logic.SingleRecEstimator;
import com.example.summer.logic.SingleRecWmodel;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component

public class WorkerModel  implements SingleRecWmodel{
    private     String username;
    private   double [] cvectorX = EstimatorConfig.cvector_rec_x;
    private   double [] cvectorY = EstimatorConfig.cvector_rec_x;
    private   double [] cvectorW = EstimatorConfig.cvector_rec_x;
    private   double [] cvectorH = EstimatorConfig.cvector_rec_x;
    private   int credit;
    private   int singleRecCnt;
    private   double singleRecAcc;

    private   int multiRecCnt;
    private   double multiRecAcc;

    private   int labelCnt;
    private   double labelAcc;

    private static WorkerDao workerDao;
    @Autowired
    private WorkerDaoImpl workerDaoIn;
    @PostConstruct
    public void  init(){
        workerDao=this.workerDaoIn;
    }

    public static WorkerModel queryWorkerModelByUserName(String username){
        WorkerPO po =workerDao.queryWorker(username);
        WorkerModel model = DozerMapSingleton.getInstance().map(po, WorkerModel.class);
        return model;
    }

    public void update(){
        WorkerPO po = DozerMapSingleton.getInstance().map(this, WorkerPO.class);
        workerDao.updateWorker(po);
    }

    public void updateSingleRecAcc(double curAcc){
        this.singleRecCnt++;
        this.singleRecAcc += curAcc;
    }

    public void updateMultiRecAcc(double curAcc){
        this.multiRecCnt++;
        this.multiRecAcc += curAcc;
    }

    @Override
    public double[] getXContributionVec() {
        return cvectorX;
    }

    @Override
    public void setXContributionVec(double[] x) {
        setCvectorX(x);
    }

    @Override
    public double[] getYContributionVec() {
        return cvectorY;
    }

    @Override
    public void setYContributionVec(double[] x) {
        setCvectorY(x);
    }

    @Override
    public double[] getWContributionVec() {
        return cvectorW;
    }

    @Override
    public void setWContributionVec(double[] x) {
        setCvectorW(x);
    }

    @Override
    public double[] getHContributionVec() {
        return cvectorH;
    }

    @Override
    public void setHContributionVec(double[] x) {
        setCvectorH(x);
    }

    @Override
    public void updateGrade(int x) {
        this.credit += x;
    }
}
