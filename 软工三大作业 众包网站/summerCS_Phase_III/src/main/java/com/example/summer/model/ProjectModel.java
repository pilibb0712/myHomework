package com.example.summer.model;

import com.example.summer.dao.ProjectDao;
import com.example.summer.domain.ProjectPO;
import com.example.summer.utility.convertorUtility.ProjectMapClassUtility;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import com.example.summer.utility.enums.MarkTypeEnum;
import com.example.summer.utility.enums.ProjectTypeEnum;
import com.example.summer.utility.json.deserialzer.MarkTypeEnumDeserializer;
import com.example.summer.utility.json.deserialzer.ProjectTypeEnumDeserializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProjectModel implements  Model{
    private String projectId;
    private String projectName;
    private String description;
    private String closeTime;
    private String requirements;
    private UserInfoModel launcher;
    private ArrayList<UserInfoModel> markers;
    private int totalNumOfImgs;
    @JsonDeserialize(using = ProjectTypeEnumDeserializer.class)
    private ProjectTypeEnum projectType;
    @JsonDeserialize(using = MarkTypeEnumDeserializer.class)
    private MarkTypeEnum markType;
    private boolean canBeJoined;
    private int upUserLimit;

    private List<String> tags;
    //private DateTime createDate;

    /***************************************************************************
     *                                                                         *
     * static methods                                                            *
     *                                                                         *
     **************************************************************************/
    @JsonIgnore
    @Autowired
    private ProjectDao projectDaoIn ;

    private static ProjectDao projectDao;

    @PostConstruct
    public void init(){
        projectDao = this.projectDaoIn;
    }

    /**
     * 查询所有的项目
     * @return 当下所有的项目
     */
    public static ArrayList<ProjectModel> listAllProjects(){
        ArrayList<ProjectPO> projectPOs = (ArrayList<ProjectPO>) projectDao.listAllProjects();
        ArrayList<ProjectModel> projectModels = new ArrayList<>();
        if (projectPOs==null){return null;}
        //依次加入所有项目
        projectPOs.forEach(e->{
            ProjectModel model = (ProjectModel) DozerMapSingleton.getInstance()
                    .map(e, ProjectMapClassUtility.getDestinationClass(e));
            projectModels.add(model);
        });
        return projectModels;
    }

    public static ProjectModel queryProjectById(String projectId){
        if(projectDao.queryProjectById(projectId)!=null){
            ProjectPO po = projectDao.queryProjectById(projectId);
            return (ProjectModel) DozerMapSingleton.getInstance()
                    .map(po, ProjectMapClassUtility.getDestinationClass(po));
        }

        return null;
    }

    /**
     * 获得所有可参加项目的列表
     * @param () 无
     * @return java.util.ArrayList<com.example.summer.model.ProjectModel>
     * @author Mr.Wang
     */
    public static ArrayList<ProjectModel> listCanbeJoinedProjectInfos(){
        ArrayList<ProjectModel> projects = new ArrayList<>();
        if(projectDao.listAllAccessibleProjects()!=null){
            //TODO 暂时用accessible这个方法也不知道对不对
            ArrayList<ProjectPO> projectPOS = (ArrayList<ProjectPO>) projectDao.listAllAccessibleProjects();
            projectPOS.forEach(project->projects.add((ProjectModel)DozerMapSingleton.getInstance()
                    .map(project, ProjectMapClassUtility.getDestinationClass(project))));
            return projects;
        }
        return null;
    }

    /**
     * 已完成，未完成
     * [[0.1, 0.2, 0.3, 0.4], [0.4, 0.3, 0.2, 0.1]]
     * @author Mr.Wang
     * @param () none
     * @return double[][]
     */
    public static double[][] getPercentageOfMarkType(){
        ArrayList<ProjectModel> allProjects = listAllProjects();
        ArrayList<ProjectModel> undoneProjects = listCanbeJoinedProjectInfos();
        int[] allNumArray, undoneNumArray;
        int[] doneNumArray = new int[4];
        if(allProjects!=null && undoneProjects!=null){
            allNumArray = calculateNum(allProjects);
            undoneNumArray = calculateNum(undoneProjects);
            for(int i=0; i<4; i++){
                doneNumArray[i] = allNumArray[i] - undoneNumArray[i];
            }

            double result[][] = new double[2][4];
            for(int j=0; j<4; j++){
                //已完成的数据统计
                result[0][j] = calculatePercentage(doneNumArray, j);
            }
            for(int k=0; k<4; k++){
                //未完成的数据统计
                result[1][k] = calculatePercentage(undoneNumArray, k);
            }

            return result;
        }

        return null;
    }

    private static double calculatePercentage(int[] array, int k){
        int sum = 0;
        for(int num : array){
            sum += num;
        }
        return (float)array[k] / (float)sum;
    }

    private static int[] calculateNum(ArrayList<ProjectModel> projectModels){
        if(projectModels != null){
            int tagNum = 0;
            int singleRecNum = 0;
            int multiRecNum = 0;
            int dotsNum = 0;
            for(ProjectModel project: projectModels){
                switch (project.getMarkType()){
                    case TAG: tagNum++; break;
                    case SINGLE_REC: singleRecNum++; break;
                    case MULTI_REC: multiRecNum++; break;
                    case DOTS: dotsNum++; break;
                }
            }
            int result[] = {tagNum, singleRecNum, multiRecNum, dotsNum};
            return result;
        }
        return null;
    }

    /**
     * 获取“我发布的项目”的列表
     * @author Mr.Wang
     * @param username username
     * @return java.util.ArrayList<com.example.summer.model.ProjectModel>
     */
    public static ArrayList<ProjectModel> listLaunchedProjectsByUserName(String username){
        ArrayList<ProjectModel> projects = new ArrayList<>();
        if(projectDao.listLaunchedProjectsByUserName(username)!=null){
            ArrayList<ProjectPO> projectPOS = (ArrayList<ProjectPO>) projectDao.listLaunchedProjectsByUserName(username);
            projectPOS.forEach(project->projects.add((ProjectModel) DozerMapSingleton.getInstance()
                    .map(project, ProjectMapClassUtility.getDestinationClass(project))));
            return projects;
        }
        return null;
    }

    /**
     * 获取“我参与的项目”的列表
     * @author Mr.Wang
     * @param username username
     * @return java.util.ArrayList<com.example.summer.model.ProjectModel>
     */
    public static ArrayList<ProjectModel> listJoinedProjectsByUserName(String username){
        ArrayList<ProjectModel> projects = new ArrayList<>();
        ArrayList<ProjectPO> projectPOS = (ArrayList<ProjectPO>) projectDao.listJoinedProjectsByUserName(username);
        if(projectPOS!=null){
            projectPOS.forEach(project->projects.add((ProjectModel) DozerMapSingleton.getInstance()
                    .map(project, ProjectMapClassUtility.getDestinationClass(project))));
            return projects;
        }
        return null;
    }

    /**
     * 获取“当下我参与的项目”的列表(不包括未完成部分)
     * @author zhangao
     * @param username username
     * @return java.util.ArrayList<com.example.summer.model.ProjectModel>
     */
    public static ArrayList<ProjectModel> listNowJoinedProjectsByUserName(String username){
        //改成直接使用数据库方法
        ArrayList<ProjectModel> joinedProjects = listJoinedProjectsByUserName(username);
        ArrayList<ProjectModel> nowJoinedProjects = new ArrayList<>();
        if(joinedProjects==null){return null;}
        nowJoinedProjects = joinedProjects.stream()
                .filter(projectModel -> projectModel.isCanBeJoined())
                .collect(Collectors.toCollection(ArrayList::new));
        return nowJoinedProjects;
    }



    /***************************************************************************
     *                                                                         *
     * non static methods                                                            *
     *                                                                         *
     **************************************************************************/

    /**
     * tbt
     *提交该用户当前编辑的图片
     * 计算当前批次图片标记的得分
     * 更新rank表
     * @param username 用户名
     * @return 用户的总得分
     * */
    public int closeCurImages(String username){
        System.out.println("projectClose");
        return 0;
    }

    /**
     * 在这个项目中新增一个marker
     * @author bb
     * @param userName
     * @return java.util.ArrayList<com.example.summer.model.ProjectModel>
     */
    public boolean addNewMarker(String userName){
        UserInfoModel marker=new UserInfoModel();
        marker.setUsername(userName);

        if (this.markers == null){ this.markers = new ArrayList<>(); }

        this.markers.add(marker);
        return this.update();
    }

    /**
     * @param username 用户名
     * @return 用户是否在项目当中
     * */
    @JsonIgnore
    public boolean isUserAlreadyIn(String username){
        ArrayList<UserInfoModel> markers= this.markers;
        if (markers == null) {return  false;}
        for (UserInfoModel inUser : markers){
            if(username.equals(inUser.getUsername())) {return true;}
        }
        return false;
    }

    /**
     * 判断这个项目是否可以新增这个marker
     * @author bb
     * @param username
     * @return java.util.ArrayList<com.example.summer.model.ProjectModel>
     */
    public boolean canBeJoinedByTheUser(String username){
        if(!canBeJoined){
            return false;
        }
        if(markers==null||markers.size()==0){
            return true;
        }
        if (isUserAlreadyIn(username)){
            return false;
        }
        return true;
    }
    public String save(){
        ProjectPO po = (ProjectPO) DozerMapSingleton.getInstance()
                .map(this, ProjectMapClassUtility.getDestinationClass(this));
        return  projectDao.saveProject(po);
    }

    public boolean update() {
        ProjectPO po = (ProjectPO) DozerMapSingleton.getInstance()
                .map(this, ProjectMapClassUtility.getDestinationClass(this));
        return projectDao.updateProject(po);
    }

    public ArrayList<SingleUserImageModel> listMarkedImgs(){
        ArrayList<SingleUserImageModel> imgs = new ArrayList<>();
        ArrayList<ImageModel> rawImgs = ImageModel.listImagesByProjectId(this.projectId);
        if (rawImgs==null){return null;}
        rawImgs.forEach(x->imgs.add(x.toMarkedImage()));
        return imgs;
    }

}
