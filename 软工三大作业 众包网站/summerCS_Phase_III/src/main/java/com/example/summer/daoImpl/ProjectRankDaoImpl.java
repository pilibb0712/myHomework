package com.example.summer.daoImpl;

import com.example.summer.dao.ProjectRankDao;
import com.example.summer.domain.ProjectRankPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRankDaoImpl implements ProjectRankDao{

    private MongoTemplate mongoTemplate;

    @Autowired
    public ProjectRankDaoImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean save(ProjectRankPO projectRank) {
        //Query query = new Query();
        mongoTemplate.save(projectRank);
        return true;
    }

    @Override
    public boolean update(ProjectRankPO projectRank) {
        Query query = new Query(Criteria.where("projectId").is(projectRank.getProjectId()));
        mongoTemplate.remove(query, ProjectRankPO.class);
        mongoTemplate.save(projectRank);
        return true;
    }

    @Override
    public ProjectRankPO queryProjectRankByProjectId(String projectId) {
        Query query = new Query(Criteria.where("projectId").is(projectId));
        return mongoTemplate.findOne(query, ProjectRankPO.class);
    }

    @Override
    public ArrayList<String> getAllProjectIds() {
        Query query = new Query();
        List<ProjectRankPO> pos=mongoTemplate.findAll(ProjectRankPO.class);
        ArrayList<String> projectIds=new ArrayList<String>();
        if(pos==null){
            return null;
        }
        for(int i=0;i<=pos.size()-1;i++){
            projectIds.add(pos.get(i).getProjectId());
        }
        return projectIds;
    }


}
