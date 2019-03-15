package com.example.summer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

/*
 * @program: summerCS_Phase_III
 * @description: 将SentencePO的tokens转成json方便存入数据库
 * @author: 161250194 zbb
 * @create: 2018/6/13
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TempSentencePO implements PO{
    private static final long serialVersionUID = 2885183213076601473L;
    private String sentenceId;

    private String rawStr;

    private String tokens;
    //转成json存进数据库，记得VARCHAR不设固定长度

    @Override
    public String getID(){
        return this.sentenceId;
    }
}
