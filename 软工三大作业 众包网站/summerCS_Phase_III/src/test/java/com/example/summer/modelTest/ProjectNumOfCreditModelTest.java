package com.example.summer.modelTest;

import com.example.summer.dao.ProjectDao;
import com.example.summer.daoImpl.ProjectRankDaoImpl;
import com.example.summer.domain.ProjectPO;
import com.example.summer.domain.ProjectRankPO;
import com.example.summer.domain.UserInfoPO;
import com.example.summer.model.ProjectNumOfCreditModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectNumOfCreditModelTest {


    @Autowired
    private ProjectRankDaoImpl rankdao;


    @Autowired
    private ProjectDao projectdao ;


    //根据所有project，每个对应建立一个projectrank，所以必须先跑ProjectNeedTimeModelTest的initData造出project的数据
    //每个projectrank的标记用户来自project的markers，标记分数10,20,30...
    @Test
    public void initData(){
        List<ProjectPO> projects=projectdao.listAllProjects();
        if(projects!=null) {
            for (int i = 0; i <= projects.size() - 1; i++) {
                ProjectRankPO po = new ProjectRankPO();
                po.setProjectId(projects.get(i).getProjectId());
                LinkedHashMap<String,Integer> map=new LinkedHashMap<String,Integer>();
                ArrayList<UserInfoPO> users=projects.get(i).getMarkers();
                if(users!=null){
                    for(int j=0;j<=users.size()-1;j++){
                        Random rand=new Random();
                            map.put(users.get(j).getUsername(),rand.nextInt(51));
                    }
                }
                po.setRankMap(map);
                rankdao.save(po);
            }
        }
    }

    @Test
    public void testGetNumOfTheCredit(){

        //initData();
       /* ProjectRankPO po1=new ProjectRankPO();
        po1.setProjectId("rank1");
        LinkedHashMap<String,Integer> map1=new LinkedHashMap<String,Integer>();
        map1.put("user1",20);
        map1.put("user2",25);
        map1.put("user3",30);
        po1.setRankMap(map1);

        ProjectRankPO po2=new ProjectRankPO();
        po2.setProjectId("rank2");
        LinkedHashMap<String,Integer> map2=new LinkedHashMap<String,Integer>();
        map2.put("user1",50);
        po2.setRankMap(map2);

        ProjectRankPO po3=new ProjectRankPO();
        po3.setProjectId("rank3");
        LinkedHashMap<String,Integer> map3=new LinkedHashMap<String,Integer>();
        map3.put("user2",30);
        map3.put("user3",40);
        po3.setRankMap(map3);

        rankdao.save(po1);
        rankdao.save(po2);
        rankdao.save(po3);
*/
        int num= ProjectNumOfCreditModel.getNumOfTheCredit(60,80);

        Assert.assertEquals(2,num);
    }
}
