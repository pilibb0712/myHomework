package com.example.summer.model;

import com.example.summer.dao.ProjectDao;
import com.example.summer.dao.ProjectRankDao;
import com.example.summer.daoImpl.ProjectDaoImpl;
import com.example.summer.domain.ProjectPO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProjectNeedTimeModel implements Model{
    private int minTime;
    private int maxTime;
    private List<Integer> numsOfDiffTimes;

    @JsonIgnore
    @Autowired
    private ProjectDao projectDaoIn ;

    private static ProjectDao projectDao;

    @PostConstruct
    public void init(){
        projectDao = this.projectDaoIn;
    }

    private int getTotalTime(ProjectPO po){
        String beginTime=po.getID();
        String closeTime=po.getCloseTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int distance=0;
        try {
            Date beginDate = format.parse(beginTime);
            Date closeDate = format.parse(closeTime);
            distance = (int) ((closeDate.getTime() - beginDate.getTime()) / (1000 * 3600 * 24));
        }catch (ParseException e){
            e.printStackTrace();
        }
        return distance;
    }

    public  ProjectNeedTimeModel getProjectNeedTime(int minSize,int maxSize,int minWorkers,int maxWorkers){
     // projectDao=this.projectDaoIn;
      List<ProjectPO> projects=projectDao.listAllProjects();
      minTime=1000;
      maxTime=0;
      numsOfDiffTimes=new ArrayList<Integer>();
      ArrayList<Integer> needTimes=new ArrayList<Integer>();
      if(projects==null){
          return null;
      }
      for(int i=0;i<=projects.size()-1;i++){
          ProjectPO project=projects.get(i);
          if(project.getCloseTime()==null){
              continue;
          }
          if(project.getMarkers()==null){
              continue;
          }
          if(project.getTotalNumOfImgs()==0){
              continue;
          }
          int markerNum=project.getMarkers().size();
          int size=project.getTotalNumOfImgs();
          if((minSize<=size)&&(size<=maxSize)&&(minWorkers<=markerNum)&&(markerNum<=maxWorkers)) {
              int needTime = getTotalTime(project);
              needTimes.add(needTime);
              if (needTime >= maxTime) {
                  maxTime = needTime;
              }
              if (needTime <= minTime) {
                  minTime = needTime;
              }
          }

      }
      double distance=(maxTime-minTime)*1.0/10.0;
      double tempMin=(double)minTime;
      double tempMax=(double)(minTime)+distance;
      int tempNum=0;
      if(needTimes==null){
          return null;
      }
       for(int i=1;i<=10;i++){
           for(int j=0;j<=needTimes.size()-1;j++){
               int needTime=needTimes.get(j);
               if((needTime<=tempMax)&&(tempMin<=needTime)){
                   tempNum++;
               }
           }
           numsOfDiffTimes.add(tempNum);
           tempNum=0;
           tempMin=tempMax;
           tempMax=tempMax+distance;
       }
       ProjectNeedTimeModel model=new ProjectNeedTimeModel();
       model.setMaxTime(maxTime);
       model.setMinTime(minTime);
       model.setNumsOfDiffTimes(numsOfDiffTimes);
       return model;
    }
}
