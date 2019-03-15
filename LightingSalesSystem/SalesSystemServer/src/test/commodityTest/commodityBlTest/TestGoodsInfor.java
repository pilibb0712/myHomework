package test.commodityTest.commodityBlTest;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blImpl.commodityBl.GoodsInforController;
import po.GoodsPO;
import vo.GoodsVO;

public class TestGoodsInfor {
public void testIfGoodsExist(){
	try{
	GoodsInforController service=new GoodsInforController();
	boolean exist=service.ifGoodsExist("¾­µä±´±´µÆ","T0001");
	System.out.println("If the goods exist: "+exist);
	}catch(RemoteException e){
		e.printStackTrace();
	}
    try{
	GoodsInforController service=new GoodsInforController();
	boolean exist=service.ifGoodsExist("¾­µä±´±´µÆ","T0001");
	System.out.println("If the goods exist: "+exist);
    }catch(RemoteException e){
    	e.printStackTrace();
    }
}
public void testFuzzyFinds(){
	try{
		GoodsInforController service=new GoodsInforController();
		ArrayList<GoodsVO> goods=service.fuzzyEnquiry("ÐÇ");
		if(goods.size()==0){
			System.out.println("There are no goods of the keyword!");
		}else{
			for(int i=0;i<=goods.size()-1;i++){
				GoodsVO good=goods.get(i);
				System.out.println(good.getName()+"-"+good.getNumber()+"-"+good.getType());
				System.out.println();
			}
		}
		}catch(RemoteException e){
			e.printStackTrace();
		}
}
public void testGoodsInfor(){
	try{
		GoodsInforController service=new GoodsInforController();
		GoodsVO good=service.goodsINfor("G01010101");
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
		}catch(RemoteException e){
			e.printStackTrace();
		}
}
public void testModifyGoodsInfor(){
	try{
		GoodsInforController service=new GoodsInforController();
		GoodsVO good=new GoodsVO();
		good.setAmount(50);
		good.setClassification("±´±´µÆ13ºÅ");
		good.setCostPrice("100");
		good.setSellingPrice("100");
		good.setCurrentCostPrice("100");
		good.setCurrentSellingPrice("100");
		good.setName("ìÅ²Ê±´±´µÆ");
		good.setNumber("G01010102");
		good.setType("T0001");
		service.modifyGoodsInfor(good);
		}catch(RemoteException e){
			e.printStackTrace();
		}
}
public static void main(String[] args){
	TestGoodsInfor test=new TestGoodsInfor();
//	test.testIfGoodsExist();
//	test.testFuzzyFinds();
//	test.testGoodsInfor();
//	test.testModifyGoodsInfor();
}
}
