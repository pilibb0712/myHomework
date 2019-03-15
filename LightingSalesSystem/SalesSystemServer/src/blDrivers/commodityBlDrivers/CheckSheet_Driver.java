package blDrivers.commodityBlDrivers;
import java.text.ParseException;
//2017.11.06 Beibei Zhang
//use drivers to test other classes (except those controllers) of the pkg
import java.util.ArrayList;

import assistant.utility.Date;
import blImpl.commodityBl.CheckSheet;
import vo.InventoryCheckSheetVO;
import vo.StockCheckSheetVO;

public class CheckSheet_Driver {
public void drive(CheckSheet sheet){
	//test sheet.getStockCheckSheet(String beginTime.String endTime);
	StockCheckSheetVO stockCheckSheet=new StockCheckSheetVO();
	try{
		Date beginTime=new Date("2017-11-06_19:00:00");
		Date endTime=new Date("2017-11-06_21:00:00");
		stockCheckSheet=sheet.getStockCheckSheet(beginTime,endTime);
	}catch(ParseException e){
		System.out.println("Format of date is wrong");
	}
	ArrayList<String[]> stockCheckContents=stockCheckSheet.getStockCheckSheet();
	if(stockCheckContents.size()==0){
		System.out.println("There are no stock during this time.");
	}else{
		System.out.println("StockCheckSheet Contents are:");
		for(int i=0;i<=stockCheckContents.size()-1;i++){
			String[] infor=stockCheckContents.get(i);
			for(int j=0;j<=infor.length-1;j++){
				System.out.print(infor[j]+"  ");
			}
			System.out.println();
		}
	}

	//test sheet.getInventoryCheckSheet();
	ArrayList<String[]> inventoryCheckContents=new ArrayList<String[]>();
	InventoryCheckSheetVO invenCheckSheet=sheet.getInventoryCheckSheet();
	inventoryCheckContents=invenCheckSheet.getInventoryCheckSheet();
	if(inventoryCheckContents.size()==0){
		System.out.println("There is no stock today.");
	}else{
		System.out.println("InventoryCheckSheet Contents are:");
		for(int i=0;i<=inventoryCheckContents.size()-1;i++){
			String[] infor=inventoryCheckContents.get(i);
			for(int j=0;j<=infor.length-1;j++){
				System.out.print(infor[j]+"  ");
			}
			System.out.println();
		}
	}

	//test sheet.getAveragePrice(String goodsID);
	int averagePrice=sheet.getAveragePrice("11001");
	System.out.println("The average price of goods 11001 is "+averagePrice);

	//test sheet.getProductionDate(String goodsID);
	String productionDate=sheet.getProductionDate("11001");
	System.out.println("The production date of goods 11001 is "+productionDate);
}
}
