package com.example.summer.daoTest;

import com.example.summer.dao.WorkerDao;
import com.example.summer.domain.WorkerPO;
import com.example.summer.web.StatisticController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerDaoTest {
    @Autowired
    private WorkerDao workerDao;
    String username = "a3";//指定你想修改的用户名

    @Test
    public void updateWorker(){
        WorkerPO po = workerDao.queryWorker(username);
        po.setLabelAcc(0.68*122);
        po.setLabelCnt(122);
        po.setSingleRecAcc(0.26*1520);
        po.setSingleRecCnt(1520);
        po.setMultiRecAcc(0.81*151);
        po.setMultiRecCnt(151);
        workerDao.updateWorker(po);
    }

    @Test
    public void test1(){
        StatisticController s = new StatisticController();
        System.out.println(s.statUserAccRadar("zhangao"));
    }

    @Test
    public void test2(){
        workerDao.saveWorker(username);
    }
}
