package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerPO {
    private     String username;
    private   double [] cvectorX ;
    private   double [] cvectorY ;
    private   double [] cvectorW ;
    private   double [] cvectorH ;
    private   int credit;

    private   int singleRecCnt;
    private   double singleRecAcc;

    private   int multiRecCnt;
    private   double multiRecAcc;

    private   int labelCnt;
    private   double labelAcc;

}
