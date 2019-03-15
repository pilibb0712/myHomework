package test.commodityTest.commodityBlTest;

import java.rmi.RemoteException;
import java.text.ParseException;

import assistant.utility.Date;
import blImpl.commodityBl.AddGoodsController;
import vo.GoodsVO;
import vo.StockInOutVO;

public class TestAddGoods {
public void testAddNewGoods(){
	GoodsVO goods1=new GoodsVO();
	goods1.setAmount(50);
	goods1.setClassification("贝贝灯12号");
	goods1.setCostPrice("100");
	goods1.setSellingPrice("100");
	goods1.setCurrentCostPrice("100");
	goods1.setCurrentSellingPrice("100");
	goods1.setName("新型贝贝灯");
	goods1.setType("T0001");

	StockInOutVO stock1=new StockInOutVO();
	stock1.setAmount(50);
	stock1.setCurrentCostPrice("100");
	stock1.setCurrentSellingPrice("100");
	stock1.setGoodsName("新型贝贝灯");
	stock1.setOperatorName("zbb");
	stock1.setProductionDate("2017-01-01");
	try{
	Date date1=new Date("2017-12-17_20:00:00");
	//yyyy-MM-dd_HH:mm:ss
	stock1.setTime(date1);
	}catch(ParseException e){
		System.out.println("格式不对");
	}

	try{
	AddGoodsController test=new AddGoodsController();
	test.addNewGoods(goods1, stock1);
	}catch(RemoteException e){
		e.printStackTrace();
	}
}
public void testAdd(){
	StockInOutVO stock=new StockInOutVO();
	stock.setAmount(10);
	stock.setCurrentCostPrice("120");
	stock.setCurrentSellingPrice(null);
	stock.setGoodsName("炫彩贝贝灯");
	stock.setGoodsID("G01010102");
	stock.setOperatorName("zbb");
	stock.setProductionDate("2017-05-05");
	try{
	Date date1=new Date("2017-12-17_21:00:00");
	//yyyy-MM-dd_HH:mm:ss
	stock.setTime(date1);
	}catch(ParseException e){
		System.out.println("格式不对");
	}

	try{
	AddGoodsController test=new AddGoodsController();
	test.add(stock);
	}catch(RemoteException e){
		e.printStackTrace();
	}
}
public static void main(String[] args){
	TestAddGoods test=new TestAddGoods();
//	test.testAddNewGoods();
	test.testAdd();
}
}
