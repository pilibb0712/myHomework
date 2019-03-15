package com.example.summer.model;

import com.example.summer.dao.ImageDao;
import com.example.summer.domain.ImagePO;
import com.example.summer.logic.MultiRecEstimator;
import com.example.summer.logic.SingleRecEstimator;
import com.example.summer.logic.SingleRecWmodel;
import com.example.summer.logic.Wmodel;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import com.example.summer.utility.enums.MarkTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ImageModel implements  Model{
    //id
    private String imageId;

    //用户最大上限
    @JsonIgnore
    private int upUserLimit;

    //标记者们
    private ArrayList<MarkerInfoModel> markers;
    //图片标记们
    private ArrayList<MarkModel> marks;
    //对应项目id
    private String projectId;
    //图片名称
    private String fileName;
    //是train还是test
    private String split;

    @JsonIgnore
    private InferredRectangleMarkModel inferredSingleRecMark;
    @JsonIgnore
    private ArrayList<InferredRectangleMarkModel> inferredRecGroupMarks;

    /***************************************************************************
     *                                                                         *
     * static methods                                                   *
     *                                                                         *
     *************************************************************************/
    @JsonIgnore
    @Autowired
    private ImageDao imageDaoIn;
    @JsonIgnore
    private static ImageDao imageDao;

    @PostConstruct
    public void init(){
        imageDao = this.imageDaoIn;
    }

    public static ArrayList<ImageModel> listCurrentImagesByUserIdAndProjectId(String username, String projectId, int begin, int num){
        ArrayList<ImageModel> imageModels = new ArrayList<>();
        ArrayList<ImagePO> imagePOS = (ArrayList<ImagePO>) imageDao.listCurrentImagesByProjectIdAndUserId(username, projectId, begin, num);
        imagePOS.forEach(e->imageModels.add(DozerMapSingleton.getInstance().map(e, ImageModel.class)));
        return imageModels;
    }

    /**
     * @author zhangao
     * @param projectId projectId
     * @return
     */
    public static ArrayList<ImageModel> listCurrentImagesByUserIdAndPorjectId(String projectId, String username){
        ArrayList<ImageModel> currentImagesModels = new ArrayList<>();
        ArrayList<ImagePO> pos = (ArrayList<ImagePO>) imageDao.listCurrentImagesByProjectIdAndUserId(projectId, username);
        if(pos==null){return null;}
        pos.forEach(e->currentImagesModels.add(DozerMapSingleton.getInstance().map(e, ImageModel.class)));
        return currentImagesModels;
    }

    /**
     * @author Mr.Wang
     * @param projectId projectId
     * @param begin 开始位置
     * @param num 图片数量
     * @return java.util.ArrayList<com.example.summer.model.ImageModel>
     */
    static ArrayList<ImageModel> listImageModelByProjectId(String projectId, int begin, int num){
        ArrayList<ImageModel> imageModels = new ArrayList<>();
        ArrayList<ImagePO> imagePOS = (ArrayList<ImagePO>) imageDao.listLauncherImagesByProjectId(projectId, begin, num);
        if (imagePOS==null){return null;}
        imagePOS.forEach(e->imageModels.add(DozerMapSingleton.getInstance().map(e, ImageModel.class)));
        return imageModels;
    }

    /**
     * 根据项目Id查询ArrayList<ImageModel>
     * @author Mr.Wang
     * @param projectId projectId
     * @return java.util.ArrayList<com.example.summer.model.ImageModel>
     */
    static ArrayList<ImageModel> listImagesByProjectId(String projectId){
        ArrayList<ImageModel> imageModels = new ArrayList<>();
        ArrayList<ImagePO> imagePOS = (ArrayList<ImagePO>) imageDao.listImagesByProjectId(projectId);
        if(imagePOS!=null){
            imagePOS.forEach(e->imageModels.add(DozerMapSingleton.getInstance().map(e, ImageModel.class)));
            return imageModels;
        }
        return null;
    }

    static ArrayList<ImageModel> listImagesByProjectIdAndUsername(String projectId, String username){
        ArrayList<ImageModel> imageModels = new ArrayList<>();
        ArrayList<ImagePO> imagePOS = (ArrayList<ImagePO>) imageDao.listImagesByProjectIdAndUserId(projectId, username);
        if(imagePOS!=null){
            imageModels = imagePOS.stream()
                    .map(e->DozerMapSingleton.getInstance().map(e, ImageModel.class))
                    .collect(Collectors.toCollection(ArrayList::new));
            return imageModels;
        }
        return null;
    }

    /**
     * 根据图片Id查询ImageModel
     * @author bb
     * @param imageId
     * @return java.util.ArrayList<com.example.summer.model.ImageModel>
     */
    public static ImageModel queryImageModelByImageId(String imageId){
        ImagePO image=imageDao.queryImageByImageId(imageId);
        ImageModel imageModel=DozerMapSingleton.getInstance().map(image, ImageModel.class);
        return imageModel;
    }


    /***************************************************************************
     *                                                                         *
     * non static methods                                                   *
     *                                                                         *
     *************************************************************************/

    /**更新存储ImageModel（ImagePO）
     * @author bb
     * @param () ImageModel model
     * @return boolean
     */
    public boolean update(){
        ImagePO po= DozerMapSingleton.getInstance().map(this, ImagePO.class);
        return imageDao.updateImage(po);
    }


    /**判断这个图片是否还可以加新的标记者
     * @author bb
     * @param () 空
     * @return boolean
     */
    public  boolean canAddNewMarker(){
        if(this.markers==null){
            return true;
        }
        if(this.markers.size()<this.upUserLimit){
            return true;
        }
        return false;
    }

    /**
     * 判断markers之中是否有这个人
     * @author bb
     * @param username username
     * @return boolean
     */
    public boolean markerAlreadyIn(String username){
        //TODO
        if(markers==null){
            return false;
        }
        for(int i=0;i<=markers.size()-1;i++){
            MarkerInfoModel marker=markers.get(i);
            String name=marker.getUsername();
            if(username.equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * 将某一个标记者的图片提交(置为非current批次)
     * 更新后台存储数据
     * @param username 用户名
     * @return 是否提交成功
     * */
    public boolean closeByMarkerName(String username){
        for (MarkerInfoModel markerInfo: markers){
            if(markerInfo.getUsername().equals(username)){
                markerInfo.setCurrentImg(false);
                this.update();
            }
        }
        return true;
    }

    /**
     * tbt
     *判断当前图片是否被某个人标记
     *必须要有具体标记才能够算被标记过
     * @param username 用户名
     * @return 是否被该用户标记过
     * */
    public boolean markedByMarker(String username){
        if(marks==null){return false;}
        for (MarkModel mark: marks){
            if (mark.getUsername().equals(username)){return true;}
        }
        return false;
    }

    public void deleteMarker(String username){
        int r = -1;
        for (int i=0; i<markers.size();i++ ){
            if (markers.get(i).getUsername().equals(username)){
                r = i;
            }
        }
        if (r!=-1){markers.remove(r);}
        this.update();
    }


    public int estimateMark(Wmodel wmodel, int[] creditGrade, MarkTypeEnum type){
        WorkerModel workerModel = (WorkerModel) wmodel;
        for (MarkModel mark: marks){
            if (mark.getUsername().equals(((WorkerModel) wmodel).getUsername())){
                switch (type){
                    case TAG:
                        break;
                    case SINGLE_REC:
                        return estimateSingleRec(workerModel, creditGrade, type,mark );
                    case MULTI_REC:
                        return estimateMultiRec(workerModel, creditGrade, type, mark);
                    case DOTS:
                        break;
                }
            }
        }
        return 0;
    }

    private int estimateMultiRec(WorkerModel wmodel, int[] creditGrade, MarkTypeEnum type, MarkModel mark){
        int credit = 0;
        this.inferredRecGroupMarks = (ArrayList<InferredRectangleMarkModel>)
                MultiRecEstimator.processUpdate(wmodel, mark.getRecGroupMark(),
                        this.inferredRecGroupMarks);
        if (this.upUserLimit == marks.size()){
            List<SingleRecWmodel> workerModels = extractAllWmodels();
            this.inferredRecGroupMarks = (ArrayList<InferredRectangleMarkModel>)
                    MultiRecEstimator.finalUpdate(workerModels
                            , extractAllRecGroupMarks(), inferredRecGroupMarks, creditGrade);
            credit = updateProjectRank(workerModels, wmodel.getUsername());
        }
        return credit;
    }

    public int estimateSingleRec(WorkerModel wmodel, int[] creditGrade, MarkTypeEnum type, MarkModel mark){
        int credit = 0;
        this.inferredSingleRecMark = (InferredRectangleMarkModel)
                SingleRecEstimator.processUpdate(wmodel,
                        mark.getSingleRecMark(), this.inferredSingleRecMark);
        List<SingleRecWmodel> workerModels = extractAllWmodels();
        //System.out.println(this.upUserLimit);
        //System.out.println(marks.size());
        if (this.upUserLimit == marks.size()){
            this.inferredSingleRecMark = (InferredRectangleMarkModel)
                    SingleRecEstimator.finalUpdate(workerModels, extractAllSingleRecs(),
                            inferredSingleRecMark, creditGrade, type);
            //System.out.println("infM:"+JsonStringUitility.toString(inferredSingleRecMark));
            //System.out.println(workerModels);
            if (inferredSingleRecMark == null){
                this.markers.clear();
                this.marks.clear();
                this.update();
                return 0;
            }
            credit = updateProjectRank(workerModels, wmodel.getUsername());
        }
        this.update();
        return credit;
    }

    private  List<SingleRecWmodel> extractAllWmodels(){
        List<SingleRecWmodel> ret= new ArrayList<>();
        for (MarkModel markModel: this.marks){
            ret.add(WorkerModel.queryWorkerModelByUserName(markModel.getUsername()));
        }
        return ret;
    }

    private List<RecGroupMarksModel> extractAllRecGroupMarks(){
        List<RecGroupMarksModel> ret = new ArrayList<>();
        for (MarkModel markModel: this.marks){
            ret.add(markModel.getRecGroupMark());
        }
        return ret;
    }

    private List<RectangleMarkModel> extractAllSingleRecs(){
        List<RectangleMarkModel> ret= new ArrayList<>();
        for (MarkModel markModel: this.marks){
            ret.add(markModel.getSingleRecMark());
        }
        return ret;
    }

    private int updateProjectRank(List<SingleRecWmodel> wms, String markUsername){
        int credit = 0;
        //更新rank
        ProjectRankModel projectRankModel = ProjectRankModel.generate(projectId);
        for (SingleRecWmodel wm: wms){
            String username = ((WorkerModel)wm).getUsername();
            int addCredit = ((WorkerModel)wm).getCredit();
            projectRankModel.updateByUserId(username
                    , projectRankModel.getCreditByUsername(username)+addCredit);
            //设定用户得分
            this.getMarkers().forEach(e->{
                if (e.getUsername().equals(username)){e.setCredit(addCredit);}
            });

            //更新wmodel
            ((WorkerModel)wm).update();
            if (markUsername.equals(username)){
                credit = ((WorkerModel) wm).getCredit();
            }
        }
        return credit;
    }


    public SingleUserImageModel toMarkedImage(){
        SingleUserImageModel ret = new SingleUserImageModel();
        ret.setFileName(this.fileName);
        ret.setImageId(this.imageId);
        ret.setProjectId(this.projectId);
        MarkModel mark = new MarkModel();
        mark.setTag(-1);
        mark.setSingleRecMark(this.inferredSingleRecMark);

        RecGroupMarksModel recGroup =new RecGroupMarksModel();
        ArrayList<RectangleMarkModel> recs = new ArrayList<>();
        if(this.inferredRecGroupMarks != null){
            recs.addAll( this.inferredRecGroupMarks);
        }


        recGroup.setRectangleMarks(recs);
        mark.setRecGroupMark(recGroup);
        ret.setMark(mark);
        return ret;
    }

    public boolean done(){
        boolean isDone = true;
        if (this.marks==null || this.marks.size()==0){return false;}
        if (this.markers.size()<this.upUserLimit){return false;}
        for (MarkerInfoModel marker: this.markers){
            if (marker.isCurrentImg()){isDone = false;}
        }
        return isDone;
    }

    public int queryWorkerCreditByUsername(String username){
        if (markers==null){return 0;}
        for (MarkerInfoModel infoModel: markers){
            if (infoModel.getUsername().equals(username)){
                return infoModel.getCredit();
            }
        }
        return 0;
    }
}


