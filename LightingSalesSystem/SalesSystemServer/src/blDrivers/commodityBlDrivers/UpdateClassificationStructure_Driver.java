package blDrivers.commodityBlDrivers;

import java.util.ArrayList;

import blImpl.commodityBl.UpdateClassificationStructure;
import vo.ClassificationVO;

//2017.11.06 Beibei Zhang
public class UpdateClassificationStructure_Driver {
public void drive(UpdateClassificationStructure update){
	ClassificationVO classification=new ClassificationVO();
	classification.setName("A");
	classification.setNumber("1");
	classification.setFatherClassification("null");
	ArrayList<String> sonClassifications=new ArrayList<String>();
	sonClassifications.add("AA 11");
	sonClassifications.add("AB 12");
	sonClassifications.add("AC 13");
	classification.setSonClassification(sonClassifications);
	classification.setGoodsList(null);
	
	
	//test addSon(classificationVO vo)
	if(update.addSon(classification)){
		System.out.println("Add son classification successfully.");
	}else{
		System.out.println("Add son classification unsuccessfully.");
	}
	
	
	//test deleteSon(classificationVO vo)
	if(update.deleteSon(classification)){
		System.out.println("Delete son classification successfully.");
	}else{
		System.out.println("Delete son classification unsuccessfully.");
	}
	
}
}
