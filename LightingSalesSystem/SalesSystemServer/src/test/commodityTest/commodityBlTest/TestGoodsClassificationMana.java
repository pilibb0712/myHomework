package test.commodityTest.commodityBlTest;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blImpl.commodityBl.GoodsClassificationController;
import po.ClassificationPO;
import po.GoodsPO;
import vo.ClassificationTreeVO;
import vo.ClassificationVO;

public class TestGoodsClassificationMana {
public void testGetClassificationTree(){
	try{
	GoodsClassificationController service=new GoodsClassificationController();
	ClassificationTreeVO tree=service.getClassificationTree();
	ArrayList<String[]> layers=tree.getClassificationLayer();
	for(int i=0;i<=layers.size()-1;i++){
		String[] layer=layers.get(i);
		for(int j=0;j<=layer.length-1;j++){
			System.out.print(layer[j]+"   ");
		}
		System.out.println();
	}
	}catch(RemoteException e){
		e.printStackTrace();
	}

}
public void testIfClassificationExist(){
	try{
		GoodsClassificationController service=new GoodsClassificationController();
		boolean exist=service.ifClassificationExist("C010");
		System.out.println("If the classification exists: "+exist);
		}catch(RemoteException e){
			e.printStackTrace();
		}
}
public void testAddNewClassification(){
	try{
		GoodsClassificationController service=new GoodsClassificationController();

		 ClassificationVO classification=new ClassificationVO();
		 classification.setName("±´±´µÆ2ºÅ");
		 classification.setFatherClassification(null);
		 classification.setSonClassification(null);
		 classification.setGoodsList(null);

		 service.addNewClassification(classification);
		}catch(RemoteException e){
			e.printStackTrace();
		}
}
public void testClassificationInfor(){
	try{
		GoodsClassificationController service=new GoodsClassificationController();
		ClassificationVO classification=service.classificationInfor("±´±´µÆ1ºÅ");
		System.out.println("name: "+classification.getName());
		 System.out.println("id: "+classification.getNumber());
		 if(classification.getFatherClassification()==null){
			 System.out.println("fatherClassificationId: "+"");
			 System.out.println("fatherClassificationName: "+"");

		 }else{
		 System.out.println("fatherClassificationId: "+classification.getFatherClassification().split("-")[0]);
		 System.out.println("fatherClassificationName: "+classification.getFatherClassification().split("-")[1]);
		 }
		 if(classification.getSonClassifacation()==null){
			 System.out.println("There are no son classifications.");
		 }else{
			 ArrayList<String> sons=classification.getSonClassifacation();
			 for(int j=0;j<=sons.size()-1;j++){
				 System.out.println("son:"+sons.get(j));
			 }
		 }
		 if(classification.getGoodsList()==null){
			 System.out.println("There are no goods.");
		 }else{
			 ArrayList<String> goods=classification.getGoodsList();
			 for(int k=0;k<=goods.size()-1;k++){
				 System.out.println("goods:"+goods.get(k));
			 }
		 }
		 System.out.println();
		}catch(RemoteException e){
			e.printStackTrace();
		}
}
public void testAddClassification(){
	try{
		GoodsClassificationController service=new GoodsClassificationController();

		 ClassificationVO classification=new ClassificationVO();
		 classification.setName("Ó«¹â±´±´µÆ");
		 classification.setFatherClassification("C01-±´±´µÆ");
		 classification.setSonClassification(null);
		 classification.setGoodsList(null);

		 service.addClassification(classification);
		}catch(RemoteException e){
			e.printStackTrace();
		}
}
public void testDeleteClassification(){
	try{
		GoodsClassificationController service=new GoodsClassificationController();

		ClassificationVO classification=new ClassificationVO();
		 classification.setName("±´±´µÆ12ºÅ");
		 classification.setNumber("C0102");
		 classification.setFatherClassification("C01-±´±´µÆ1ºÅ");
		 classification.setSonClassification(null);
		 classification.setGoodsList(null);

		 service.deleteClassification(classification);
		}catch(RemoteException e){
			e.printStackTrace();
		}
}
public void testModifyClassification(){
	try{
		GoodsClassificationController service=new GoodsClassificationController();

		 ClassificationVO classification=new ClassificationVO();
		 classification.setName("±´±´µÆ3ºÅ");
		 classification.setNumber("C03");
		 classification.setFatherClassification("C01-±´±´µÆ1ºÅ");
		 classification.setSonClassification(null);
		 classification.setGoodsList(null);

		 service.modifyClassificationInfor(classification);
		}catch(RemoteException e){
			e.printStackTrace();
		}
}
public static void main(String[] args){
	TestGoodsClassificationMana test=new TestGoodsClassificationMana();
//	test.testAddNewClassification();
//	test.testGetClassificationTree();
//	test.testIfClassificationExist();
//	test.testClassificationInfor();
//	test.testAddClassification();
//	test.testDeleteClassification();
//	test.testModifyClassification();
}
}
