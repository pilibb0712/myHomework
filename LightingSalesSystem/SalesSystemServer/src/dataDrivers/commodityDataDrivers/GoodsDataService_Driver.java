package dataDrivers.commodityDataDrivers;

import java.util.ArrayList;

import dataService.commodityDataService.GoodsDataService;
import po.GoodsPO;

public class GoodsDataService_Driver {
public void drive(GoodsDataService goodsData){
	ArrayList<GoodsPO> allGoods=goodsData.finds();
	//test finds()
	System.out.println("Get All Goods:");
	if(allGoods.size()==0){
		System.out.println("There are no goods.");
	}else{
	for(int i=0;i<=allGoods.size()-1;i++){
		System.out.println(allGoods.get(i).getName());
	}
	}

	ArrayList<GoodsPO> keyGoods=goodsData.fuzzyFinds("±´±´");
	//test fuzzyFinds()
	if(keyGoods.size()==0){
		System.out.println("There are no goods of the keyword!");
	}else{
		for(int i=0;i<=keyGoods.size()-1;i++){
			GoodsPO good=keyGoods.get(i);
			System.out.println(good.getName()+"-"+good.getNumber()+"-"+good.getType());
			System.out.println();
		}
	}

	GoodsPO oneGoods=goodsData.find("AAa","00001A");
	//test find(String goodsName,String goodsType)
	System.out.println("The infor of Classification AAa(00001A) is:");
	System.out.println("name:"+oneGoods.getName());
	System.out.println("number:"+oneGoods.getNumber());
	System.out.println("Classification:"+oneGoods.getClassification());
	System.out.println("type:"+oneGoods.getType());
	System.out.println("amount:"+oneGoods.getAmount());
	System.out.println("cost price:"+oneGoods.getCostPrice());
	System.out.println("selling price:"+oneGoods.getSellingPrice());
	System.out.println("current cost price:"+oneGoods.getCurrentCostPrice());
	System.out.println("current selling price:"+oneGoods.getCurrentSellingPrice());


	oneGoods=goodsData.find("11001");
	//test find(String tag)
	System.out.println("The infor of Classification 11001 is:");
	System.out.println("name:"+oneGoods.getName());
	System.out.println("number:"+oneGoods.getNumber());
	System.out.println("Classification:"+oneGoods.getClassification());
	System.out.println("type:"+oneGoods.getType());
	System.out.println("amount:"+oneGoods.getAmount());
	System.out.println("cost price:"+oneGoods.getCostPrice());
	System.out.println("selling price:"+oneGoods.getSellingPrice());
	System.out.println("current cost price:"+oneGoods.getCurrentCostPrice());
	System.out.println("current selling price:"+oneGoods.getCurrentSellingPrice());
	GoodsPO goods=new GoodsPO();
	goods.setName("AAa");
	goods.setNumber("11001");
	goods.setClassification("AA");
	goods.setType("00001A");
	goods.setAmount(100);
	goods.setCostPrice("100");
	goods.setSellingPrice("110");
	goods.setCurrentCostPrice("100");
	goods.setCurrentSellingPrice("110");
	if(goodsData.insert(goods)){
		//test insert()
		System.out.println("Insert a goods successfully");
	}else{
		System.out.println("Insert unsuccessfully");
	}

	if(goodsData.delete(goods)){
		//test delete()
		System.out.println("Delete a goods successfully");
	}else{
		System.out.println("Delete unsuccessfully");
	}

	if(goodsData.update(goods)){
		//test update()
		System.out.println("Update a goods successfully");
	}else{
		System.out.println("Update unsuccessfully");
	}
}
}
