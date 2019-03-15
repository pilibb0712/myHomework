package test.commodityTest.commodityBlTest;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

import assistant.utility.Date;
import blImpl.commodityBl.StockCheckController;
import vo.StockCheckSheetVO;

public class TestStockCheck {
public void testStockCheckSheet(){
	StockCheckController service=new StockCheckController();
	try{
		Date begin=new Date("2017-12-17_15:00:00");
		Date end=new Date("2017-12-20_23:00:00");
		StockCheckSheetVO check=service.stockCheckSheet(begin, end);
		ArrayList<String[]> sheet=check.getStockCheckSheet();
		for(int i=0;i<=sheet.size()-1;i++){
			String[] content=sheet.get(i);
			for(int j=0;j<=content.length-1;j++){
				System.out.print(content[j]+"  ");
			}
			System.out.println();
		}
	}catch(ParseException e){
		System.out.println("时间格式错误");
	}catch(RemoteException e){
		e.printStackTrace();
	}
}
public static void main(String[] args){
     TestStockCheck test=new TestStockCheck();
     test.testStockCheckSheet();
}
}
