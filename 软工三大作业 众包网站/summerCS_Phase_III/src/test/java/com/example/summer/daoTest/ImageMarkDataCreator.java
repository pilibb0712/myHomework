package com.example.summer.daoTest;

import com.example.summer.dao.ImageDao;
import com.example.summer.dao.ProjectDao;
import com.example.summer.dao.ProjectRankDao;
import com.example.summer.domain.*;
import com.example.summer.model.MultiCreditProjectModel;
import com.example.summer.model.ProjCreditStatModel;
import com.example.summer.utility.json.JsonStringUitility;
import com.example.summer.web.StatisticController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageMarkDataCreator {
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectRankDao  projectRankDao;

    String projectId = "2018-06-19_16-48-24-146";//制定项目Id
    String username = "EstellaGu"; //指定用户名
    int []ratio = new int[]{1,3,2,1};//指定如[3,2,1,-3]的比例


    double sum = Arrays.stream(ratio).sum();

    @Test
    public void addNewMark(){
        List<ImagePO> pos=imageDao.listImagesByProjectId(projectId);
        int size = pos.size();
        int []credits = ((MultiCreditProjectPO)projectDao.queryProjectById(projectId)).getCredits();

        int[] realRatios =new int[4];
        double x = 0.0;
        for (int i =0; i<4; i++){
            x += ratio[i]/sum*size;
            realRatios[i] = (int) x;
        }

        int i = 0;
        for (ImagePO po : pos){
            int credit = 0;
            if (i<realRatios[0]){credit = credits[0];}
            else if (i<realRatios[1]){credit = credits[1];}
            else if (i<realRatios[2]){credit = credits[2];}
            else if (i<realRatios[3]){credit = -credits[0];}
            else{credit = credits[2];}
            if (po.getMarkers() == null){po.setMarkers(new ArrayList<>());}
            po.getMarkers().add(new MarkerInfoPO(username,credit,false));
            i++;
            //System.out.println(JsonStringUitility.toString(po));
            imageDao.updateImage(po);
        }
    }

    //@Test
    public void test1(){
        StatisticController s = new StatisticController();
        List<Integer> ss=s.statUserCreditsBox(projectId, username);
        for (Integer i: ss){System.out.println(i);}
    }

    //@Test
    public void test2(){
        StatisticController s= new StatisticController();
        List<ProjCreditStatModel> x = s.statUserCreditsInProjPolar(projectId, username);
        for (ProjCreditStatModel m: x){
            System.out.println(JsonStringUitility.toString(m));
        }
    }

    //@Test
    public void test3(){
        ProjectPO po = new ProjectPO();
        po.setUpUserLimit(4);
        po.setRequirements("3333");
        projectDao.saveProject(po);
    }
}
