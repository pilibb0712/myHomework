package com.example.summer.modelTest;

import com.example.summer.dao.ProjectDao;
import com.example.summer.dao.ProjectRankDao;
import com.example.summer.domain.ProjectPO;
import com.example.summer.domain.UserInfoPO;
import com.example.summer.model.ProjectNeedTimeModel;
import com.example.summer.utility.enums.MarkTypeEnum;
import com.example.summer.utility.enums.ProjectTypeEnum;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectNeedTimeModelTest {


    @Autowired
    private  ProjectDao dao ;


//建了100个project，开始日期为当天，结束日期每五个递增一天
//每个有2,4,6,8...个标记用户
//每个有10,20,30...张图片
   @Test
    public void initData(){
        for(int i=1;i<=100;i++){
            ProjectPO po=new ProjectPO();
            Random rand=new Random();
            po.setCloseTime("2018-07-"+String.valueOf(12+rand.nextInt(20))+"_24-00-00-000");
            ArrayList<UserInfoPO> markers=new ArrayList<UserInfoPO>();
            for(int j=1;j<=i*2;j++){
                UserInfoPO user=new UserInfoPO();
                user.setUsername("user"+"-"+String.valueOf(i)+"-"+String.valueOf(j));
                markers.add(user);
            }
            po.setMarkers(markers);
            po.setTotalNumOfImgs(10*i);
            po.setDescription("project"+String.valueOf(i)+"  description");
            po.setCanBeJoined(false);
            UserInfoPO launcher=new UserInfoPO();
            launcher.setUsername("launcher"+String.valueOf(i));
            po.setLauncher(launcher);
            po.setMarkType(MarkTypeEnum.values()[i%4]);
            po.setProjectName("project"+String.valueOf(i));
            po.setProjectType(ProjectTypeEnum.SIMPLE);
            po.setRequirements("no requirements");
            po.setTags(null);
            po.setUpUserLimit(markers.size());
            dao.saveProject(po);
        }
    }

    @Test
    public void testGetProjectNeedTime(){
/*
        //dao=projectDaoIn;
       //initData();
        ProjectPO po1=new ProjectPO();
        po1.setTotalNumOfImgs(50);
        ArrayList<UserInfoPO> markers1=new ArrayList<UserInfoPO>();
        for(int i=1;i<=10;i++){
            UserInfoPO po=new UserInfoPO();
            po.setUsername(String.valueOf(i));
            markers1.add(po);
        }
        po1.setMarkers(markers1);
        po1.setCloseTime("2018-07-20_24-00-00-000");
        dao.saveProject(po1);

        ProjectPO po2=new ProjectPO();
        po2.setTotalNumOfImgs(60);
        ArrayList<UserInfoPO> markers2=new ArrayList<UserInfoPO>();
        for(int i=1;i<=15;i++){
            UserInfoPO po=new UserInfoPO();
            po.setUsername(String.valueOf(i));
            markers2.add(po);
        }
        po2.setMarkers(markers2);
        po2.setCloseTime("2018-07-15_24-00-00-000");
        dao.saveProject(po2);
*/
        ProjectNeedTimeModel model=new ProjectNeedTimeModel();
        ProjectNeedTimeModel result=model.getProjectNeedTime(0,10000,0,10000);



        Assert.assertEquals(10,result.getNumsOfDiffTimes().get(7).intValue());
        Assert.assertEquals(10,result.getNumsOfDiffTimes().get(5).intValue());
        Assert.assertEquals(10,result.getNumsOfDiffTimes().get(4).intValue());
        Assert.assertEquals(10,result.getNumsOfDiffTimes().get(6).intValue());
        Assert.assertEquals(10,result.getNumsOfDiffTimes().get(3).intValue());
        Assert.assertEquals(10,result.getNumsOfDiffTimes().get(2).intValue());
        Assert.assertEquals(10,result.getNumsOfDiffTimes().get(1).intValue());
        Assert.assertEquals(10,result.getNumsOfDiffTimes().get(0).intValue());
        Assert.assertEquals(10,result.getNumsOfDiffTimes().get(9).intValue());
        }
}
