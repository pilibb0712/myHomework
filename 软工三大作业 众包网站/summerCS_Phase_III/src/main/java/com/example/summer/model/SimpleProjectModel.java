package com.example.summer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleProjectModel extends  ProjectModel {
    private int eachCredit;

    /**
     * tbt
     *提交该用户当前编辑的图片
     * 计算当前批次图片标记的得分
     * 更新rank表
     * @param username 用户名
     * @return 用户的总得分
     * */
    @Override
    public int closeCurImages(String username){
        ImageListModel curImageList =ImageListModel.generate(this.getProjectId(), username);
        if(curImageList.getImageList()==null||curImageList.getImageList().size()==0){return 0;}
        curImageList.closeImages(username);
        int credit = curImageList.calcTotalCreditOfImgs(eachCredit, username);
        ProjectRankModel projectRank =ProjectRankModel.generate(getProjectId());
        int oldCredits = projectRank.getCreditByUsername(username);
        if(oldCredits==-1){return  -1;}
        projectRank.updateByUserId(username, oldCredits+credit);//内部更新
        return credit;
    }

}
