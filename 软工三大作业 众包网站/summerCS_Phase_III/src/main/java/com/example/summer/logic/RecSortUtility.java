package com.example.summer.logic;

import com.example.summer.model.InferredRectangleMarkModel;
import com.example.summer.model.PositionModel;
import com.example.summer.model.RectangleMarkModel;

import java.util.ArrayList;
import java.util.List;

public class RecSortUtility {
    public static List<RectangleMarkModel> sortRecs(List<RectangleMarkModel> recs
            , List<InferredRectangleMarkModel> stdRecs){
        if(stdRecs == null){stdRecs = new ArrayList<InferredRectangleMarkModel>();}
        if (stdRecs.size() == 0){return recs;}

        List<RectangleMarkModel> resRec = new ArrayList<RectangleMarkModel>();
        List<RectangleMarkModel> ret = new ArrayList<RectangleMarkModel>();
        double [] dis = new double[stdRecs.size()];
        int [] pos = new int[stdRecs.size()];
        for (int i=0;i<pos.length;i++){pos[i]=-1;}

        for (int i=0; i<recs.size(); i++){
            Pair resultPair = calcNewCenter(stdRecs, recs.get(i));
            int curPos = resultPair.pos;
            double curDis = resultPair.dis;
            //System.out.println("pos:"+curPos+"  dis:"+curDis);
            //更新当前位置
            if(pos[curPos]==-1){
                pos[curPos] = i;
                dis[curPos] = curDis;
            }
            else {
                if (curDis < dis[curPos]){
                    pos[curPos] = i;
                    dis[curPos] = curDis;
                }
                //else{resRec.add(recs.get(i));}
            }
        }

        for (int i=0;i<pos.length;i++){System.out.print(pos[i]+"  ");}
        System.out.println();

        //装填返回顺序
        for (int i=0; i<stdRecs.size(); i++){
            if (pos[i]==-1){
                ret.add(null);
            }
            else {
                ret.add(recs.get(pos[i]));
            }
        }

        ret.addAll(resRec);
        return ret;
    }


    private static Pair calcNewCenter(List<InferredRectangleMarkModel> stdRecs, RectangleMarkModel rec){
        double minDis = Double.MAX_VALUE;
        int pos = -1;
        for (int i=0; i<stdRecs.size(); i++){
            double dis = calcDis(rec, (InferredRectangleMarkModel) stdRecs.get(i));
            //System.out.println(dis);
            if (dis < minDis){
                minDis = dis;
                pos = i;
            }
        }
        return new Pair(pos, minDis);
    }

    private static double calcDis(RectangleMarkModel r1, InferredRectangleMarkModel infR2){
        PositionModel p1 = r1.getStartDot();
        PositionModel p2 = new PositionModel();
        p2.setX(infR2.getStartDot().getX()/infR2.getTotalPrOfX());
        p2.setY(infR2.getStartDot().getY()/infR2.getTotalPrOfY());
        //PrintUtility.printPoint(p2);
        double x = p1.getX() - p2.getX();
        x = x*x;
        double y = p1.getY() - p2.getY();
        y = y*y;
        return Math.sqrt(x+y);
    }

    private static class Pair{
        int pos;
        double dis;
        Pair(int pos, double dis){
            this.pos = pos;
            this.dis = dis;
        }
    }
}
