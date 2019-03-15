package dataStubs.commodityDataStubs;

import java.text.ParseException;
import java.util.ArrayList;

import assistant.utility.Date;
import dataService.commodityDataService.StockInOutRecordService;
import po.StockInOutPO;
//do some changes 2017.11.06
public class StockInOutRecordService_Stub implements StockInOutRecordService{
	public ArrayList<StockInOutPO> finds(String goodsID){
		ArrayList<StockInOutPO> someGoodsRecords=new ArrayList<StockInOutPO>();
		StockInOutPO record=new StockInOutPO();
		record.setOperatorName("Beibei Zhang");
		record.setGoodsID(goodsID);
		record.setGoodsName("Aa");
		record.setAmount((-50));
		try{
			Date date=new Date("2017-10-20_14:00:00");
			record.setTime(date);
		}catch(ParseException e){
			System.out.println("Format of date is wrong.");
		}
		record.setProductionDate("2017-10-19");
		record.setCurrentCostPrice("200");
		record.setCurrentSellingPrice("0");
		someGoodsRecords.add(record);
		return someGoodsRecords;
	}
	public ArrayList<StockInOutPO> finds(Date beginTime,Date endTime){
		ArrayList<StockInOutPO> someDateRecords=new ArrayList<StockInOutPO>();
		StockInOutPO record=new StockInOutPO();
		record.setOperatorName("Beibei Zhang");
		record.setGoodsID("11001");
		record.setGoodsName("Aa");
		record.setAmount((-50));
		try{
			Date date=new Date("2017-10-20_14:00:00");
			record.setTime(date);
		}catch(ParseException e){
			System.out.println("Format of date is wrong.");
		}
		record.setProductionDate("2017-10-19");
		record.setCurrentCostPrice("200");
		record.setCurrentSellingPrice("0");
		someDateRecords.add(record);
		return someDateRecords;
	}
	public boolean insert(StockInOutPO po){
		return true;
	}

}
