package blImpl.commodityBl;

import java.util.ArrayList;

import assistant.convertors.CommodityPOVOConvertor;
import blInteract.commodityInteract.ClassificationListService;
import dataImpl.commodityData.GoodsClassificationData;
import po.ClassificationPO;
import vo.ClassificationVO;

public class ClassificationListImpl implements ClassificationListService{
	public ArrayList<ClassificationVO> getClassificationList(){
           GoodsClassificationData classificationData=new GoodsClassificationData();
           ArrayList<ClassificationPO> classifications=classificationData.finds();
           CommodityPOVOConvertor convertor=new CommodityPOVOConvertor();
           ArrayList<ClassificationVO>  classificationList=new ArrayList<ClassificationVO>();
           for(int i=0;i<=classifications.size()-1;i++){
                  ClassificationPO classification=classifications.get(i);
                  if(classification.getGoodsList()==null){

                  }else{
                	  if((classification.getGoodsList()).size()==0){

                	  }else{
                		  ClassificationVO classificationvo=convertor.classificationPOtoVO(classification);
                		  classificationList.add(classificationvo);
                	  }
                  }
            }
           return classificationList;
     }

}
