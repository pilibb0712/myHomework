package com.example.summer.logicTest;

import com.example.summer.logic.MultiRecEstimator;
import com.example.summer.logic.SingleRecWmodel;
import com.example.summer.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class multiRecEstimatorTest {
    List<SingleRecWmodel> wms = new ArrayList<>();
    List<RecGroupMarksModel> recGoups = new ArrayList<>();
    List<InferredRectangleMarkModel> infRecs = new ArrayList<>();

    @Before
    public void setup(){
        wms.add(new WorkerModel());
        wms.add(new WorkerModel());
        wms.add(new WorkerModel());
        wms.add(new WorkerModel());

        RecGroupMarksModel rec1 = new RecGroupMarksModel();
        rec1.setRectangleMarks(gnerateRecs1());
        recGoups.add(rec1);

        RecGroupMarksModel rec2 = new RecGroupMarksModel();
        rec2.setRectangleMarks(gnerateRecs2());
        recGoups.add(rec2);

        RecGroupMarksModel rec3= new RecGroupMarksModel();
        rec3.setRectangleMarks(gnerateRecs3());
        recGoups.add(rec3);

        RecGroupMarksModel rec4 = new RecGroupMarksModel();
        rec4.setRectangleMarks(gnerateRecs4());
        recGoups.add(rec4);

    }

    public ArrayList<RectangleMarkModel> gnerateRecs1(){
        ArrayList<RectangleMarkModel> recs = new ArrayList<>();
        //recs.add(new RectangleMarkModel(20,50,60,60) );
        recs.add(new RectangleMarkModel(30,50,60,60) );
        recs.add(new RectangleMarkModel(40,50,60,60) );
        recs.add(new RectangleMarkModel(50,50,60,60) );
        return recs;
    }

    public ArrayList<RectangleMarkModel> gnerateRecs2(){
        ArrayList<RectangleMarkModel> recs = new ArrayList<>();
        recs.add(new RectangleMarkModel(20,50,60,60) );
        recs.add(new RectangleMarkModel(30,50,60,60) );
        recs.add(new RectangleMarkModel(40,50,60,60) );
        recs.add(new RectangleMarkModel(50,50,60,60) );
        return recs;
    }

    public ArrayList<RectangleMarkModel> gnerateRecs3(){
        ArrayList<RectangleMarkModel> recs = new ArrayList<>();
        recs.add(new RectangleMarkModel(20,50,60,60) );
        //recs.add(new RectangleMarkModel(30,50,60,60) );
        recs.add(new RectangleMarkModel(40,50,60,60) );
        recs.add(new RectangleMarkModel(700,50,60,60) );
        return recs;
    }

    public ArrayList<RectangleMarkModel> gnerateRecs4(){
        ArrayList<RectangleMarkModel> recs = new ArrayList<>();
        recs.add(new RectangleMarkModel(20,50,60,60) );
        recs.add(new RectangleMarkModel(30,50,60,60) );
        recs.add(new RectangleMarkModel(40,50,60,60) );
        recs.add(new RectangleMarkModel(50,50,60,60) );
        return recs;
    }

    @Test
    public void testMultiRec(){
        for (int i=0; i<4; i++){
            infRecs
                    = MultiRecEstimator.processUpdate(wms.get(i), recGoups.get(i), infRecs);
            System.out.println(infRecs);
        }
        List<InferredRectangleMarkModel> infs = MultiRecEstimator.finalUpdate(wms
                , recGoups, infRecs, new int[]{1,2,3});
        System.out.println(infs);
    }
}
