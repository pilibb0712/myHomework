package com.example.summer.model;

import com.example.summer.dao.ProjectRankDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
@Component
public class ProjectNumOfCreditModel implements Model{
    @Autowired
    private  ProjectRankDao projectRankDaoIn;

    private static ProjectRankDao projectRankDao ;

    @PostConstruct
    public void init(){
        projectRankDao = this.projectRankDaoIn;
    }

public static int getNumOfTheCredit(int min,int max){
    int sum=0;
    ArrayList<String> projectIds=projectRankDao.getAllProjectIds();
    if(projectIds==null){
        return sum;
    }
    for(int i=0;i<=projectIds.size()-1;i++){
        String id=projectIds.get(i);
        ProjectRankModel model=ProjectRankModel.generate(id);
        int credit=model.getProjectCredit();
        if((min<=credit)&&(credit<=max)){
            sum++;
        }
    }
    return sum;
}
}
