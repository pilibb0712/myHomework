package dataDrivers.commodityDataDrivers;
import java.text.ParseException;
//do some changes 2017.11.06
import java.util.ArrayList;

import assistant.utility.Date;
import dataService.commodityDataService.StockInOutRecordService;
import po.StockInOutPO;
import vo.StockCheckSheetVO;

public class StockInOutRecordService_Driver {
public void drive(StockInOutRecordService stockInOutRecord){
	ArrayList<StockInOutPO> someGoodsStocks=stockInOutRecord.finds("11001");
	//test finds(String date)
	System.out.println("Goods 11001 stock amount infor:");
	if(someGoodsStocks.size()==0){
		System.out.println("There are no stock of the goods.");
	}else{
	for(int i=0;i<=someGoodsStocks.size()-1;i++){
		System.out.println(someGoodsStocks.get(i).getAmount());
	}
	}
	ArrayList<StockInOutPO> someTimeStocks=new ArrayList<StockInOutPO>();
	try{
		Date beginTime=new Date("2017-10-20_13:00:00");
		Date endTime=new Date("2017-10-20_14:00:00");
		someTimeStocks=stockInOutRecord.finds(beginTime,endTime);
	}catch(ParseException e){
		System.out.println("Format of date is wrong");
	}
	//test finds(String beginTime,String endTime)
	System.out.println("2017-10-2- 13:00 ~ 2017-10-20 14:00 stock infor:");
	if(someTimeStocks.size()==0){
		System.out.println("There are no stock.");
	}else{
	for(int i=0;i<=someTimeStocks.size()-1;i++){
		System.out.println(someTimeStocks.get(i).getGoodsName()+"   "+someTimeStocks.get(i).getAmount()+"   "+someTimeStocks.get(i).getTime());
	}
	}
	StockInOutPO record=new StockInOutPO();
	record.setOperatorName("BeibeiZhang");
	//the format of ID of operator is ???
	record.setGoodsID("11001");
	record.setGoodsName("Aa");
	record.setAmount((-50));
	try{
		Date date=new Date("2017-10-20_13:00:00");;
		record.setTime(date);
	}catch(ParseException e){
		System.out.println("Format of date is wrong");
	}
	record.setProductionDate("2017-10-19");
	record.setCurrentCostPrice("200");
	record.setCurrentSellingPrice("0");
	if(stockInOutRecord.insert(record)){
		//test insert()
		System.out.println("Insert a piece of stock-in-out record successfully");
	}else{
		System.out.println("Insert unsuccessfully");
	}
}
}
