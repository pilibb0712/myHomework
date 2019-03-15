package com.example.summer.logic;

import com.example.summer.configInfo.EstimatorConfig;
import com.example.summer.model.InferredRectangleMarkModel;
import com.example.summer.model.PositionModel;
import com.example.summer.model.RectangleMarkModel;
import com.example.summer.model.WorkerModel;
import com.example.summer.utility.enums.MarkTypeEnum;
import com.example.summer.utility.json.JsonStringUitility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleRecEstimator {
    private static final double k_adj = EstimatorConfig.k_adj_single_rec;
    private static final double bad_threshold = EstimatorConfig.bad_threshod_single_rec;
    private static final double k_update = EstimatorConfig.k_update_single_rec;

    private static final double [] cvector_x = EstimatorConfig.cvector_rec_x;
    private static final double [] cvector_y = EstimatorConfig.cvector_rec_y;
    private static final double [] cvector_w = EstimatorConfig.cvector_rec_w;
    private static final double [] cvector_h = EstimatorConfig.cvector_rec_h;

    private static final double [] dis_x = EstimatorConfig.dis_rec_x;
    private static final double [] dis_y = EstimatorConfig.dis_rec_y;
    private static final double [] dis_w = EstimatorConfig.dis_rec_w;
    private static final double [] dis_h = EstimatorConfig.dis_rec_h;


    public static Markable processUpdate(SingleRecWmodel wm
            , RectangleMarkModel newMark
            , InferredRectangleMarkModel inferredMark){
        //int lenOfCvector = cvector.length;
        if (inferredMark == null){inferredMark = new InferredRectangleMarkModel();}
        if (inferredMark.getStartDot() == null){inferredMark.setStartDot(new PositionModel());}
        updateStartDotX(wm, newMark, inferredMark);
        updateStartDotY(wm, newMark, inferredMark);
        updateWidth(wm, newMark, inferredMark);
        updateHeight(wm, newMark, inferredMark);
        return inferredMark;
    }

    public static Markable finalUpdate(List<SingleRecWmodel> wms
            , List<RectangleMarkModel> allMarks
            , InferredRectangleMarkModel inferredMark
            , int [] creditGrade
            , MarkTypeEnum markType){
        List<Double> disList = calcDisList(allMarks, inferredMark);
        int [] sortedIndex = MySort.argsort(disList, false);
        int i=0;
        for (i=0; i<disList.size()/3; i++){
            //System.out.println(sortedIndex[i]);
            undoInferredModel(inferredMark, allMarks.get(sortedIndex[i]), wms.get(sortedIndex[i]));
            //disList = calcDisList(allMarks, inferredMark);
            //sortedIndex = MySort.argsort(disList, false);
        }
        //disList = calcDisList(allMarks, inferredMark);
        //System.out.println(inferredMark.getStartDot().getX()/inferredMark.getTotalPrOfX());
        //System.out.println(disList.get(0));
        //if (calcDisOfTwoMark(allMarks.get(sortedIndex[i]), inferredMark)>bad_threshold){return null;}


        //更新积分
        //int creditLen = creditGrade.length;
        int []indexes = MySort.argsort(disList, true);
        updateGrade(wms, creditGrade, indexes);

        //生成最后推测结果
        finalCalcInferredModel(inferredMark);

        //更新模型wm
        updateWorkerModel(wms, allMarks, inferredMark, markType);

        //返回结果
        return inferredMark;
    }

    static void updateStartDotX(SingleRecWmodel wm
            , RectangleMarkModel newMark
            , InferredRectangleMarkModel inferredMark){
        //System.out.println("curMark:"+JsonStringUitility.toString(newMark));
        double[] cvector = wm.getXContributionVec();
        double accuracy = calcAccuracy(cvector);
        double x = newMark.getStartDot().getX();
        double incrementX = x + k_adj*(cvector[3]-cvector[1])*dis_x[1];
        double infX = inferredMark.getStartDot().getX();
        double infTotalAccuracy = inferredMark.getTotalPrOfX();

        infX += incrementX*accuracy;
        infTotalAccuracy += accuracy;

        inferredMark.setTotalPrOfX(infTotalAccuracy);
        inferredMark.getStartDot().setX(infX);
    }

    static void updateStartDotY(SingleRecWmodel wm
            , RectangleMarkModel newMark
            , InferredRectangleMarkModel inferredMark){
        double[] cvector = wm.getYContributionVec();
        double accuracy = calcAccuracy(cvector);
        double y = newMark.getStartDot().getY();
        double incrementY = y + k_adj*(cvector[3]-cvector[1])*dis_y[1];
        double infY = inferredMark.getStartDot().getY();
        double infTotalAccuracy = inferredMark.getTotalPrOfY();

        infY += incrementY*accuracy;
        infTotalAccuracy += accuracy;

        inferredMark.setTotalPrOfY(infTotalAccuracy);
        inferredMark.getStartDot().setY(infY);
    }

    static void updateWidth(SingleRecWmodel wm
            , RectangleMarkModel newMark
            , InferredRectangleMarkModel inferredMark){
        double[] cvector = wm.getWContributionVec();
        double accuracy = calcAccuracy(cvector);
        double y = newMark.getWidth();
        double increment = y + k_adj*(cvector[3]-cvector[1])*dis_w[1];
        double inf = inferredMark.getWidth();
        double infTotalAccuracy = inferredMark.getTotalPrOfW();

        inf += increment*accuracy;
        infTotalAccuracy += accuracy;

        inferredMark.setTotalPrOfW(infTotalAccuracy);
        inferredMark.setWidth(inf);
    }

    static void updateHeight(SingleRecWmodel wm
            , RectangleMarkModel neHMark
            , InferredRectangleMarkModel inferredMark){
        double[] cvector = wm.getHContributionVec();
        double accuracy = calcAccuracy(cvector);
        double y = neHMark.getHeight();
        double increment = y + k_adj*(cvector[3]-cvector[1])*dis_h[1];
        double inf = inferredMark.getHeight();
        double infTotalAccuracy = inferredMark.getTotalPrOfH();

        inf += increment*accuracy;
        infTotalAccuracy += accuracy;

        inferredMark.setTotalPrOfH(infTotalAccuracy);
        inferredMark.setHeight(inf);
    }

    static double calcAccuracy(double [] cvector){
        int len = cvector.length;
        double sum = Arrays.stream(cvector).sum();
        double r = 0;
        for (int i=0; i<3; i++){r+=cvector[len/2+i];}
        for (int i=0; i<3; i++){r+=cvector[len/2-i];}
        return r/sum;
    }

    static double calcDisOfTwoMark1(RectangleMarkModel rec1,InferredRectangleMarkModel rec2){
        double x1 = rec1.getStartDot().getX();
        double y1 = rec1.getStartDot().getY();
        double w1 = rec1.getWidth();
        double h1 = rec1.getHeight();


        //推测模型要恢复到非累加模式
        double x2 = rec2.getStartDot().getX()/rec2.getTotalPrOfX();
        double y2 = rec2.getStartDot().getY()/rec2.getTotalPrOfY();
        double w2 = rec2.getWidth()/ rec2.getTotalPrOfW();
        double h2 = rec2.getHeight()/rec2.getTotalPrOfH();
        return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)+(w1-w2)*(w1-w2)+(h1-h2)*(h1-h2);
    }

    //两个矩形的留白和重合比
    static double calcDisOfTwoMark(RectangleMarkModel rec1,InferredRectangleMarkModel rec2){

        double x1 = rec1.getStartDot().getX();
        double y1 = rec1.getStartDot().getY();
        double w1 = rec1.getWidth();
        double h1 = rec1.getHeight();
        double s1 = w1*h1;

        //推测模型要恢复到非累加模式
        double x2 = rec2.getStartDot().getX()/rec2.getTotalPrOfX();
        double y2 = rec2.getStartDot().getY()/rec2.getTotalPrOfY();
        double w2 = rec2.getWidth()/ rec2.getTotalPrOfW();
        double h2 = rec2.getHeight()/rec2.getTotalPrOfH();
        double s2 = w2*h2;
        //System.out.println("s2:"+x2+" "+y2+" "+w2+" "+h2);
        double width = Math.min(x1+w1, x2+w2) - Math.max(x1, x2);
        if (width < 0){return Double.MAX_VALUE;}

        double height = Math.min(y1+h1, y2+h2) - Math.max(y1, y2);
        if (height < 0){return Double.MAX_VALUE;}

        return (s1+s2)/(width*height)-2;
    }

    private static List<Double> calcDisList(List<RectangleMarkModel> allMarks
            , InferredRectangleMarkModel inferredMark){
        int len = allMarks.size();
        ArrayList<Double> result = new ArrayList<Double>();
        for (int i =0; i<len; i++){
            RectangleMarkModel r = allMarks.get(i);
            result.add(calcDisOfTwoMark1(r, inferredMark));
        }
        return result;
    }

    private static void undoInferredModel(InferredRectangleMarkModel inferredModel
            , RectangleMarkModel rectangleMarkModel
            , SingleRecWmodel wm){
        inferredModel.getStartDot().setX(
                calcUndoData(inferredModel.getStartDot().getX()
                        , rectangleMarkModel.getStartDot().getX()
                        , calcAccuracy(wm.getXContributionVec()))
        );

        inferredModel.setTotalPrOfX(calcUndoPr(inferredModel.getTotalPrOfX()
                , calcAccuracy(wm.getXContributionVec())));


        inferredModel.getStartDot().setY(
                calcUndoData(inferredModel.getStartDot().getY()
                        , rectangleMarkModel.getStartDot().getY()
                        , calcAccuracy(wm.getYContributionVec()))
        );
        inferredModel.setTotalPrOfY(calcUndoPr(inferredModel.getTotalPrOfY()
                , calcAccuracy(wm.getYContributionVec())));

        inferredModel.setWidth(
                calcUndoData(inferredModel.getWidth()
                        , rectangleMarkModel.getWidth()
                        , calcAccuracy(wm.getWContributionVec()))
        );
        inferredModel.setTotalPrOfW(calcUndoPr(inferredModel.getTotalPrOfW()
                , calcAccuracy(wm.getWContributionVec())));

        inferredModel.setHeight(
                calcUndoData(inferredModel.getHeight()
                        , rectangleMarkModel.getHeight()
                        , calcAccuracy(wm.getHContributionVec()))
        );
        inferredModel.setTotalPrOfH(calcUndoPr(inferredModel.getTotalPrOfH()
                , calcAccuracy(wm.getHContributionVec())));
    }

    private static double calcUndoData(double infData, double data, double pr){
        return infData - data*pr;
    }

    private static double calcUndoPr(double infPr, double pr){
        return infPr-pr;
    }

    private static InferredRectangleMarkModel finalCalcInferredModel(InferredRectangleMarkModel markModel){
        double x = markModel.getStartDot().getX() / markModel.getTotalPrOfX();
        double y = markModel.getStartDot().getY() / markModel.getTotalPrOfY();
        double w = markModel.getWidth() / markModel.getTotalPrOfW();
        double h = markModel.getHeight() / markModel.getTotalPrOfH();

        markModel.getStartDot().setX(x);
        markModel.getStartDot().setY(y);
        markModel.setWidth(w);
        markModel.setHeight(h);
        return markModel;
    }

    static void updateGrade(List<SingleRecWmodel> wms
            , int [] creditGrade
            , int [] indexes){
        //各组人数
        int num = indexes.length/creditGrade.length;
        //更新分数
        for(int i=0; i<creditGrade.length; i++){
            for (int j=0; j<num; j++){
                wms.get(indexes[j+i*num]).updateGrade(creditGrade[i]);
            }
        }
        //更新剩下人的分数
        for (int i=num*creditGrade.length; i<indexes.length; i++){
            wms.get(indexes[i]).updateGrade(creditGrade[creditGrade.length-1]);
        }
    }

    private static double calcAcc(RectangleMarkModel rec, InferredRectangleMarkModel inf){
        double x1 = rec.getStartDot().getX();
        double y1 = rec.getStartDot().getY();
        double w1 = rec.getWidth();
        double h1 = rec.getHeight();
        double s1 = w1*h1;

        //推测模型要恢复到非累加模式
        double x2 = inf.getStartDot().getX();
        double y2 = inf.getStartDot().getY();
        double w2 = inf.getWidth();
        double h2 = inf.getHeight();
        double s2 = w2*h2;
        //System.out.println("s2:"+x2+" "+y2+" "+w2+" "+h2);
        double width = Math.min(x1+w1, x2+w2) - Math.max(x1, x2);
        if (width < 0){return 0;}

        double height = Math.min(y1+h1, y2+h2) - Math.max(y1, y2);
        if (height < 0){return 0;}

        return (width*height)/(s1+s2-width*height);
    }

    private static void updateWorkerModel(List<SingleRecWmodel> wms
            , List<RectangleMarkModel> allMarks
            , InferredRectangleMarkModel inferredMark
            , MarkTypeEnum markType){
        for (int i=0; i<wms.size(); i++){
            WorkerModel wmodel = (WorkerModel) wms.get(i);
            RectangleMarkModel markModel= allMarks.get(i);

            if (markType==MarkTypeEnum.SINGLE_REC){wmodel.updateSingleRecAcc(calcAcc(markModel, inferredMark));}
            else if (markType==MarkTypeEnum.MULTI_REC){wmodel.updateMultiRecAcc(calcAcc(markModel, inferredMark));}

            //update x contribution vec
            wmodel.setXContributionVec(calcUpdateVal(wmodel.getXContributionVec()
                    , dis_x , inferredMark.getStartDot().getX()
                    ,markModel.getStartDot().getX()));
            //update y contribution vec
            wmodel.setYContributionVec(calcUpdateVal(wmodel.getYContributionVec()
                    , dis_y , inferredMark.getStartDot().getY()
                    ,markModel.getStartDot().getY()));
            //update w contribution vec
            wmodel.setWContributionVec(calcUpdateVal(wmodel.getWContributionVec()
                    , dis_w , inferredMark.getWidth()
                    ,markModel.getWidth()));
            //update h contribution vec
            wmodel.setHContributionVec(calcUpdateVal(wmodel.getHContributionVec()
                    , dis_h , inferredMark.getHeight()
                    ,markModel.getHeight()));
            //System.out.println("wms"+i+": "+JsonStringUitility.toString(wmodel));
        }
    }

    /**
     * @param y contribution vector
     * @param dis 两个向量位间的距离
     * @param inf 推测的正确结果
     * @param actual 员工标注的结果
     * 比如 {1,1,1,1,1} 更新后在-1处+1 -> {1,2,1,1,1}
     * */
    static double[] calcUpdateVal(double [] y, double [] dis, double inf, double actual){
        double []x = y.clone();
        int len = x.length;
        int mid = len/2; //向量中间位置
        double d = actual-inf;//距离
        int lOrR = d>0 ? 1: -1;
        d = Math.abs(d);

        //计算偏移量
        int offset = dis.length-1;//距离中间位置的偏移量
        for (int i=0; i<dis.length; i++){
            if (d<dis[i]){
                offset=i;
                break;
            }
        }
        //if (Math.abs(d)>dis[dis.length-1]){offset=dis.length;}

        //double change = offset>0 ? dis[offset]:0;
        if (d<dis[0]){x[mid] += Math.abs(dis[0]-d)*k_update;}
        else {x[mid+offset*lOrR] += Math.abs(dis[offset-1]-d)*k_update;}
        //if (mid+offset*lOrR==4){System.out.println(x[4]);}
        return x;
    }
}
