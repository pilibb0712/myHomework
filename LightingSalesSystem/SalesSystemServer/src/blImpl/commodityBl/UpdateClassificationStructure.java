package blImpl.commodityBl;

import java.util.ArrayList;

import dataImpl.commodityData.GoodsClassificationData;
import po.ClassificationPO;
import vo.ClassificationVO;

public class UpdateClassificationStructure {
	public boolean addSon(ClassificationVO sonClassification){
		String classificationID=sonClassification.getNumber();
		String classificationName=sonClassification.getName();
		String classificationInfor=classificationID+"-"+classificationName;

		String father=sonClassification.getFatherClassification();
		String[] fatherInfor=father.split("-");
		String fatherID=fatherInfor[0];
		GoodsClassificationData classificationData=new GoodsClassificationData();
		ClassificationPO fatherObj=classificationData.find(fatherID);
		ArrayList<String> sonClassificationList=fatherObj.getSonClassifacation();
		if(sonClassificationList==null){
			ArrayList<String> iniSonClassifications=new ArrayList<String>();
			iniSonClassifications.add(classificationInfor);
			fatherObj.setSonClassification(iniSonClassifications);
		}else{
			sonClassificationList.add(classificationInfor);
			fatherObj.setSonClassification(sonClassificationList);
		}
		classificationData.update(fatherObj);
		return true;
	}
	public boolean deleteSon(ClassificationVO sonClassification){
		String classificationID=sonClassification.getNumber();
		String father=sonClassification.getFatherClassification();
		if(father==null){

		}else{
			String[] fatherInfor=father.split("-");
			String fatherID=fatherInfor[0];
			GoodsClassificationData classificationData=new GoodsClassificationData();
			ClassificationPO fatherObj=classificationData.find(fatherID);
			ArrayList<String> sonClassificationList=fatherObj.getSonClassifacation();
			if(sonClassificationList==null){

			}else{
				for(int i=0;i<=sonClassificationList.size()-1;i++){
					String son=sonClassificationList.get(i);
					String[] sonInfor=son.split("-");
					String sonID=sonInfor[0];
					if(sonID.equals(classificationID)){
						sonClassificationList.remove(i);
						break;
					}
				}
				fatherObj.setSonClassification(sonClassificationList);
	            classificationData.update(fatherObj);
			}
		}

		return true;
	}

}
