package dataStubs.commodityDataStubs;

import java.util.ArrayList;

import dataService.commodityDataService.GoodsClassificationDataService;
import po.ClassificationPO;

public class GoodsClassificationDataService_Stub implements GoodsClassificationDataService{
	public ArrayList<ClassificationPO> finds(){
		ArrayList<ClassificationPO> allClassifications=new ArrayList<ClassificationPO>();
		ClassificationPO classification=new ClassificationPO();
		classification.setName("A");
		classification.setNumber("1");
		classification.setFatherClassification("null");
		ArrayList<String> sonClassification=new ArrayList<String>();
		sonClassification.add("AA 11");
		sonClassification.add("AB 12");
		sonClassification.add("AC 13");
		allClassifications.add(classification);
		return allClassifications;
		
	}
	public ClassificationPO find(String classificationTag){
		ClassificationPO classification=new ClassificationPO();
		classification.setName("A");
		classification.setNumber("1");
		classification.setFatherClassification("null");
		ArrayList<String> sonClassification=new ArrayList<String>();
		sonClassification.add("AA 11");
		sonClassification.add("AB 12");
		sonClassification.add("AC 13");
		return classification;
	}
	public boolean insert(ClassificationPO po){
		return true;
	}
	public boolean delete(ClassificationPO po){
		return true;
	}
	public boolean update(ClassificationPO po){
		return true;
	}

}
