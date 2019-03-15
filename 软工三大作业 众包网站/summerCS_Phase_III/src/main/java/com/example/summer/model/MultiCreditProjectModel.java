package com.example.summer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MultiCreditProjectModel extends ProjectModel{
    private int[] credits;

    @Override
    public int closeCurImages(String username){
        WorkerModel wm = WorkerModel.queryWorkerModelByUserName(username);

        //如果未标记直接返回
        ImageListModel curImageList =ImageListModel.generate(this.getProjectId(), username);
        if(curImageList.getImageList()==null||curImageList.getImageList().size()==0){return 0;}

        //
        int credit = curImageList.estimateAndClose(wm, credits, this.getMarkType());

        return credit;
    }
}
