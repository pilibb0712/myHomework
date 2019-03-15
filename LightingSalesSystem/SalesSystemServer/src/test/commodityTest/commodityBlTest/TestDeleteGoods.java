package test.commodityTest.commodityBlTest;

import java.rmi.RemoteException;
import java.text.ParseException;

import assistant.utility.Date;
import blImpl.commodityBl.AddGoodsController;
import blImpl.commodityBl.DeleteGoodsController;
import po.GoodsPO;
import vo.GoodsVO;
import vo.StockInOutVO;

public class TestDeleteGoods {
public void testDelete(){
	StockInOutVO stock=new StockInOutVO();
	stock.setAmount(-20);
	stock.setCurrentCostPrice(null);
	stock.setCurrentSellingPrice("130");
	stock.setGoodsName("¾­µä±´±´µÆ");
	stock.setGoodsID("G01010102");
	stock.setOperatorName("zbb");
	stock.setProductionDate("2017-01-01");
	try{
	Date date1=new Date("2017-12-17_21:00:00");
	//yyyy-MM-dd_HH:mm:ss
	stock.setTime(date1);
	}catch(ParseException e){
		System.out.println("¸ñÊ½²»¶Ô");
	}

	try{
	DeleteGoodsController test=new DeleteGoodsController();
	test.delete(stock);
	}catch(RemoteException e){
		e.printStackTrace();
	}
}
public void testObsolete(){
	GoodsVO goods4=new GoodsVO();
	goods4.setAmount(100);
	goods4.setClassification("±´±´µÆ111ºÅ");
	goods4.setCostPrice("180");
	goods4.setSellingPrice("190");
	goods4.setCurrentCostPrice("190");
	goods4.setCurrentSellingPrice("200");
	goods4.setName("ÐÄÐÍ±´±´µÆ");
	goods4.setNumber("G01010104");
	goods4.setType("T0001");

	try{
		DeleteGoodsController test=new DeleteGoodsController();
		test.obsoleteGoods(goods4);
		}catch(RemoteException e){
			e.printStackTrace();
		}
}
public void testCompareWarning(){
	try{
		DeleteGoodsController test=new DeleteGoodsController();
		int dValue=test.compareTowarning("G010201", 20);
		System.out.println("warningValue: "+(20-dValue)+" dValue: "+dValue);
		}catch(RemoteException e){
			e.printStackTrace();
		}
}
public static void main(String[] args){
	TestDeleteGoods test=new TestDeleteGoods();
//	test.testDelete();
//	test.testObsolete();
	test.testCompareWarning();


}
}
