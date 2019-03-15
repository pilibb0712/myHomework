package com.example.summer.daoImpl;

import com.example.summer.dao.ProjectDao;
import com.example.summer.domain.ProjectPO;
import com.example.summer.utility.dataUtility.InitTimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class ProjectDaoImpl implements ProjectDao{

    private final static String COLLECTION_NAME = "project";

    private MongoTemplate mongoTemplate;

    @Autowired
    public ProjectDaoImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override

    public List<ProjectPO> listAllProjects() {
        //Query query = new Query(Criteria.where());
        //List<SimpleProjectPO> simpleProjectPOS = mongoTemplate.findAll(SimpleProjectPO.class);
        //List<ProjectPO> projectPOS = new ArrayList<>();
        //projectPOS.addAll(simpleProjectPOS);
        List<ProjectPO> projectPOS = mongoTemplate.findAll(ProjectPO.class,COLLECTION_NAME);
        return projectPOS;
    }


    @Override
    public List<ProjectPO> listAllAccessibleProjects() {
        Query query = new Query(Criteria.where("canBeJoined").is(true));
        List<ProjectPO> projectPOS = mongoTemplate.find(query, ProjectPO.class,  COLLECTION_NAME);
        return projectPOS;
    }

    @Override
    public ProjectPO queryProjectById(String projectId) {
        Query query = new Query(Criteria.where("projectId").is(projectId));
        ProjectPO projectPO = mongoTemplate.findOne(query, ProjectPO.class, COLLECTION_NAME);
        return projectPO;
    }

    @Override
    public List<ProjectPO> listLaunchedProjectsByUserName(String userName) {
        Query query = new Query(Criteria.where("launcher.username").is(userName));
        List<ProjectPO> projectPOS = mongoTemplate.find(query, ProjectPO.class, COLLECTION_NAME);
        return projectPOS;
    }

    @Override
    public List<ProjectPO> listJoinedProjectsByUserName(String userName) {
        Query query = new Query(Criteria.where("markers.username").is(userName));
        List<ProjectPO> projectPOS = mongoTemplate.find(query, ProjectPO.class, COLLECTION_NAME);
        return projectPOS;
    }

    @Override
    public String saveProject(ProjectPO po) {
        String id = InitTimeUtility.getCurrentTime();
        po.setProjectId(id);
        mongoTemplate.save(po, COLLECTION_NAME);
        return id;
    }

    @Override
    public boolean updateProject(ProjectPO po) {
        deleteProject(po.getProjectId());
        mongoTemplate.save(po, COLLECTION_NAME);
        return true;
    }

    @Override
    public boolean deleteProject(String projectId) {
        Query query = new Query(Criteria.where("projectId").is(projectId));
        mongoTemplate.remove(query, ProjectPO.class, COLLECTION_NAME);
        return true;
    }
}
