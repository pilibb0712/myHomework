package com.example.summer.model;

import com.example.summer.domain.RectangleMarkPO;
import com.example.summer.logic.Markable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RectangleMarkModel implements Markable {

    //rectangle的Id
    @JsonIgnore//不给前端 TODO 待定
    private String recId;
    //tag描述
    private String tag;
    //左上角点的坐标
    private PositionModel startDot;
    //宽度
    private double width;
    //长度
    private double height;

    /***************************************************************************
     *                                                                         *
     * Constructor                                                      *
     *                                                                         *
     **************************************************************************/
    @Override
    public String toString(){
        return startDot.getX()+" "+startDot.getY()+" "+width+" "+height+"\n";
    }

    public RectangleMarkModel(double x, double y, double w, double h){
        this.startDot = new PositionModel(x,y);
        this.width = w;
        this.height = h;
    }

    public RectangleMarkModel(RectangleMarkPO po){
        this.height = po.getHeight();
        this.recId = po.getRecId();
        if(po.getStartDot()!=null){this.startDot = new PositionModel(po.getStartDot().getX(),po.getStartDot().getY());}
        this.tag = po.getTag();
        this.width = po.getWidth();
    }
}
