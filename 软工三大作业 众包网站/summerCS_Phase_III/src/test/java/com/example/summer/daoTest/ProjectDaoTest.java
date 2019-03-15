package com.example.summer.daoTest;

import com.example.summer.dao.ProjectDao;
import com.example.summer.domain.ProjectPO;
import com.example.summer.domain.SimpleProjectPO;
import com.example.summer.domain.UserInfoPO;
import com.example.summer.utility.enums.ProjectTypeEnum;
import com.example.summer.utility.json.JsonStringUitility;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectDaoTest {

    @Autowired
    ProjectDao projectDao;

    ProjectPO po = new SimpleProjectPO();

    @Before
    public void setUp(){
        ArrayList<UserInfoPO> markers = new ArrayList<>();
        markers.add(new UserInfoPO("zsd"));

        po.setCanBeJoined(true);
        po.setDescription("project1");
        po.setUpUserLimit(4);
        po.setTotalNumOfImgs(45);
        po.setRequirements("black");
        po.setLauncher(new UserInfoPO("zhangao"));
        po.setProjectName("proeject1");
        po.setProjectType(ProjectTypeEnum.SIMPLE);
        po.setMarkers(markers);
        ((SimpleProjectPO) po).setEachCredit(4);
    }

    //@Test
    public void testListAll(){
        List<ProjectPO> pos = projectDao.listAllProjects();
        for(ProjectPO po:pos){
            System.out.println(JsonStringUitility.toString(po));
            System.out.println(po.getClass().toString());
        }
    }

    //@Test
    public void testListAllAccessible(){
        List<ProjectPO> pos = projectDao.listAllAccessibleProjects();
        for(ProjectPO po:pos){
            System.out.println(JsonStringUitility.toString(po));
            System.out.println(po.getClass().toString());
        }
    }

    //@Test
    public void testQueryProjectById(){
        ProjectPO po = projectDao.queryProjectById("2018-06-16_10-44-01-626");
        System.out.println(JsonStringUitility.toString(po));
    }

    //@Test
    public void testListLaunchedProjectsByUserName(){
        List<ProjectPO> pos = projectDao.listLaunchedProjectsByUserName("zhangao");
        for(ProjectPO po:pos){
            System.out.println(JsonStringUitility.toString(po));
            System.out.println(po.getClass().toString());
        }
    }

    //@Test
    public void testListJoinedProjectsByUserName(){
        List<ProjectPO> pos = projectDao.listJoinedProjectsByUserName("abc");
        for(ProjectPO po:pos){
            System.out.println(JsonStringUitility.toString(po));
            System.out.println(po.getClass().toString());
        }
    }

    //@Test
    public void testSaveProject(){
        po.getMarkers().get(0).setUsername("abc");
        projectDao.saveProject(po);
    }

    //@Test
    public void testUpdate(){
        po.setCanBeJoined(false);
        po.setProjectId("2018-06-16_10-47-33-114");
        System.out.println(JsonStringUitility.toString(po));
        projectDao.updateProject(po);
    }

    //@Test
    public void testDelete(){
        projectDao.deleteProject("2018-06-16_10-44-01-626");
    }
}
