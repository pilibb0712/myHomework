package com.example.summer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InferredRectangleMarkModel extends RectangleMarkModel{

    private double totalPrOfX;

    private double totalPrOfY;

    private double totalPrOfW;

    private double totalPrOfH;

    public String toString(){
        String s = super.toString();
        s = s.substring(0, s.length()-1);
        s+=" "+totalPrOfX+" "+totalPrOfW+" "+totalPrOfH+"\n";
        return s;
    }
}
