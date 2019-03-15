package dataDrivers.commodityDataDrivers;

import java.util.ArrayList;

import dataService.commodityDataService.GoodsClassificationDataService;
import po.ClassificationPO;

public class GoodsClassificationDataService_Driver {
	//到底是模仿上层模块来测下层，但上层还没写啊啊啊，我知道上层会调它，但不知道上层具体会怎么调啊，还是自己写一个驱动，单纯测下层呢
	//是为真实代码准备的桩和驱动，还是只是测接口，不对啊，接口有什么好测的
	public void drive(GoodsClassificationDataService classificationData){
		ArrayList<ClassificationPO> allClassifications=classificationData.finds();
		//test finds()
		System.out.println("Get All Classifications:");
		if(allClassifications.size()==0){
			System.out.println("There are no classifications.");
		}else{
		for(int i=0;i<=allClassifications.size()-1;i++){
			System.out.println(allClassifications.get(i).getName());
		}
		}
		ClassificationPO oneClassification=classificationData.find("A");
		//test find(String tag)
		System.out.println("The infor of Classification A is:");
		System.out.println("name:"+oneClassification.getName());
		System.out.println("number:"+oneClassification.getNumber());
		System.out.println("Father Classification:"+oneClassification.getFatherClassification());
		ArrayList<String> sonClassification=oneClassification.getSonClassifacation();
		ArrayList<String> goodsList=oneClassification.getGoodsList();
		if(sonClassification.size()==0){
			System.out.println("No son classification");
		}else{
			System.out.println("Son classification:");
			for(int i=0;i<=sonClassification.size()-1;i++){
				System.out.println(sonClassification.get(i));
			}
		}
		if(goodsList.size()==0){
			System.out.println("No goods");
		}else{
			System.out.println("GoodsList:");
			for(int i=0;i<=goodsList.size()-1;i++){
				System.out.println(goodsList.get(i));
			}
		}
		ClassificationPO classification=new ClassificationPO();
		classification.setName("A");
		classification.setNumber("1");
		classification.setFatherClassification("null");
		ArrayList<String> sonClassifications=new ArrayList<String>();
		sonClassifications.add("AA 11");
		sonClassifications.add("AB 12");
		sonClassifications.add("AC 13");
		classification.setSonClassification(sonClassifications);
		classification.setGoodsList(null);
		//do some changes 2017.11.06
		if(classificationData.insert(classification)){
			//test insert()
			System.out.println("Insert a classification successfully");
		}else{
			System.out.println("Insert unsuccessfully");
		}
		if(classificationData.delete(classification)){
			//test delete()
			System.out.println("Delete a classification successfully");
		}else{
			System.out.println("Delete unsuccessfully");
		}
		if(classificationData.update(classification)){
			//test update()
			System.out.println("Update a classification successfully");
		}else{
			System.out.println("Update unsuccessfully");
		}
		//只返回了boolean，那我怎么知道到底数据文件里有没有更新、插入、删除呢，但这又是黑盒测试，所以？？？
	}
}
