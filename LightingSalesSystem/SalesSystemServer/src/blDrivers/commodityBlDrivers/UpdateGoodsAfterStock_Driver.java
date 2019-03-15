package blDrivers.commodityBlDrivers;

import java.text.ParseException;

import assistant.utility.Date;
import blImpl.commodityBl.UpdateGoodsAfterStock;
import vo.StockInOutVO;

//2017.11.06 Beibei Zhang
public class UpdateGoodsAfterStock_Driver {
public void drive(UpdateGoodsAfterStock update){
	StockInOutVO record=new StockInOutVO();
	record.setOperatorName("BeibeiZhang");
	//the format of ID of operator is ???
	record.setGoodsID("11001");
	record.setGoodsName("Aa");
	record.setAmount((-50));
	try{
		Date date=new Date("2017-10-20_13:00:00");
		record.setTime(date);
	}catch(ParseException e){
		System.out.println("Format of date is wrong");
	}
	record.setProductionDate("2017-10-19");
	record.setCurrentCostPrice("200");
	record.setCurrentSellingPrice("0");


	//test update(StockInOutVO vo)
	//just use update(StockInOutVO vo) ???
		if(update.update(record)){
			System.out.println("Update Goods after stock successfully.");
		}else{
			System.out.println("Update Goods after stock unsuccessfully.");
		}


}
}
