package com.example.summer.model;

import lombok.*;

/**
 * 对应projectId和credit
 * @author Mr.Wang
 * @version 2018/6/6
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProjectCreditModel {
    private String projectId;
    private int credit;
}
