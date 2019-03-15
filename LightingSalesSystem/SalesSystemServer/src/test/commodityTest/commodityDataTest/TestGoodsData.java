package test.commodityTest.commodityDataTest;

import java.util.ArrayList;

import dataImpl.commodityData.GoodsData;
import po.GoodsPO;

public class TestGoodsData {
public void testInsert(){
	GoodsPO goods1=new GoodsPO();
	goods1.setAmount(50);
	goods1.setClassification("±´±´µÆ111ºÅ");
	goods1.setCostPrice("100");
	goods1.setSellingPrice("110");
	goods1.setCurrentCostPrice("120");
	goods1.setCurrentSellingPrice("130");
	goods1.setName("¾­µä±´±´µÆ");
	goods1.setNumber("G01010101");
	goods1.setType("T0001");

	GoodsPO goods2=new GoodsPO();
	goods2.setAmount(100);
	goods2.setClassification("±´±´µÆ111ºÅ");
	goods2.setCostPrice("110");
	goods2.setSellingPrice("120");
	goods2.setCurrentCostPrice("120");
	goods2.setCurrentSellingPrice("140");
	goods2.setName("¾­µä±´±´µÆ");
	goods2.setNumber("G01010102");
	goods2.setType("T0002");

	GoodsPO goods3=new GoodsPO();
	goods3.setAmount(100);
	goods3.setClassification("±´±´µÆ111ºÅ");
	goods3.setCostPrice("180");
	goods3.setSellingPrice("190");
	goods3.setCurrentCostPrice("190");
	goods3.setCurrentSellingPrice("200");
	goods3.setName("ÐÇ¿Õ±´±´µÆ");
	goods3.setNumber("G01010103");
	goods3.setType("T0001");

	GoodsPO goods4=new GoodsPO();
	goods4.setAmount(100);
	goods4.setClassification("±´±´µÆ111ºÅ");
	goods4.setCostPrice("180");
	goods4.setSellingPrice("190");
	goods4.setCurrentCostPrice("190");
	goods4.setCurrentSellingPrice("200");
	goods4.setName("ÐÄÐÍ±´±´µÆ");
	goods4.setNumber("G01010104");
	goods4.setType("T0001");

	GoodsPO goods5=new GoodsPO();
	goods5.setAmount(100);
	goods5.setClassification("±´±´µÆ111ºÅ");
	goods5.setCostPrice("200");
	goods5.setSellingPrice("210");
	goods5.setCurrentCostPrice("220");
	goods5.setCurrentSellingPrice("230");
	goods5.setName("Ó«¹â±´±´µÆ");
	goods5.setNumber("G01010105");
	goods5.setType("T0001");

	GoodsData data=new GoodsData();
	data.insert(goods1);
	data.insert(goods2);
	data.insert(goods3);
	data.insert(goods4);
	data.insert(goods5);

}
public void testFinds(){
	GoodsData data=new GoodsData();
	ArrayList<GoodsPO> goods=data.finds();
	if(goods.size()==0){
		System.out.println("There are no goods!");
	}else{
		for(int i=0;i<=goods.size()-1;i++){
			GoodsPO good=goods.get(i);
			System.out.println("name: "+good.getName());
			System.out.println("number: "+good.getNumber());
			System.out.println("type: "+good.getType());
			System.out.println("classification: "+good.getClassification());
			System.out.println("amount: "+good.getAmount());
			System.out.println("costPrice: "+good.getCostPrice());
			System.out.println("sellingPrice: "+good.getSellingPrice());
			System.out.println("currentcostPrice: "+good.getCurrentCostPrice());
			System.out.println("currentsellingPrice: "+good.getCurrentSellingPrice());
			System.out.println();
		}
	}
	}
public void testFind(String id){
	GoodsData data=new GoodsData();
	GoodsPO good=data.find(id);
	if(good==null){
		System.out.println("There is no the goods!");
	}else{
		System.out.println("name: "+good.getName());
		System.out.println("number: "+good.getNumber());
		System.out.println("type: "+good.getType());
		System.out.println("classification: "+good.getClassification());
		System.out.println("amount: "+good.getAmount());
		System.out.println("costPrice: "+good.getCostPrice());
		System.out.println("sellingPrice: "+good.getSellingPrice());
		System.out.println("currentcostPrice: "+good.getCurrentCostPrice());
		System.out.println("currentsellingPrice: "+good.getCurrentSellingPrice());
		System.out.println();
	}
}
public void testFind(String name,String type){
	GoodsData data=new GoodsData();
	GoodsPO good=data.find(name,type);
	if(good==null){
		System.out.println("There is no the goods!");
	}else{
		System.out.println("name: "+good.getName());
		System.out.println("number: "+good.getNumber());
		System.out.println("type: "+good.getType());
		System.out.println("classification: "+good.getClassification());
		System.out.println("amount: "+good.getAmount());
		System.out.println("costPrice: "+good.getCostPrice());
		System.out.println("sellingPrice: "+good.getSellingPrice());
		System.out.println("currentcostPrice: "+good.getCurrentCostPrice());
		System.out.println("currentsellingPrice: "+good.getCurrentSellingPrice());
		System.out.println();
	}
}
public void testDelete(){
	GoodsData data=new GoodsData();
	GoodsPO goods2=new GoodsPO();
	goods2.setAmount(100);
	goods2.setClassification("±´±´µÆ111ºÅ");
	goods2.setCostPrice("110");
	goods2.setSellingPrice("120");
	goods2.setCurrentCostPrice("120");
	goods2.setCurrentSellingPrice("140");
	goods2.setName("¾­µä±´±´µÆ");
	goods2.setNumber("G01010101");
	goods2.setType("T0002");
	data.delete(goods2);
}
public void testUpdate(){
	GoodsPO goods3=new GoodsPO();
	goods3.setAmount(230);
	goods3.setClassification("Ó«¹â±´±´µÆ");
	goods3.setCostPrice("500");
	goods3.setSellingPrice("700");
	goods3.setCurrentCostPrice("500");
	goods3.setCurrentSellingPrice("700");
	goods3.setName("Ô­ÁÂÉ«Ó«¹â±´±´µÆ");
	goods3.setNumber("G010201");
	goods3.setType("T0001");

	GoodsData data=new GoodsData();
	data.update(goods3);
}
public void testFuzzyFind(String keyword){
	GoodsData data=new GoodsData();
	ArrayList<GoodsPO> goods=data.fuzzyFinds(keyword);
	if(goods.size()==0){
		System.out.println("There are no goods of the keyword!");
	}else{
		for(int i=0;i<=goods.size()-1;i++){
			GoodsPO good=goods.get(i);
			System.out.println(good.getName()+"-"+good.getNumber()+"-"+good.getType());
			System.out.println();
		}
	}
}
public static void main(String[] args){
	TestGoodsData test=new TestGoodsData();
//	test.testInsert();
	test.testFinds();
//	test.testFind("G00000000");
//	test.testFind("¾­µä±´±´µÆ", "T0002");
//	test.testDelete();
//	test.testFinds();
//	test.testUpdate();
//	test.testFinds();
//	test.testFuzzyFind("ÐÄ");
}
}
