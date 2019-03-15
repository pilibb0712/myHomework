package blMocks.commodityBlMocks;
//2017.11.06 Beibei Zhang
//use the stubs of other classes to test the controllers
import java.util.ArrayList;

import assistant.utility.Date;
import blImpl.commodityBl.CheckSheet;
import vo.InventoryCheckSheetVO;
import vo.StockCheckSheetVO;

public class MockCheckSheet extends CheckSheet{
	public StockCheckSheetVO getStockCheckSheet(Date beginTime,Date endTime){
		ArrayList<String[]> stockCheckContents=new ArrayList<String[]>();
		String[] infor={"Aa","11001","2017-11-06_19:00:00","0","0","-50","50"};
		String[] total={"0","0","-50","2500"};
		stockCheckContents.add(infor);
		stockCheckContents.add(total);
		StockCheckSheetVO stockCheckSheet=new StockCheckSheetVO();
		stockCheckSheet.setStockCheckSheet(stockCheckContents);
		return stockCheckSheet;
	}
	public InventoryCheckSheetVO getInventoryCheckSheet(){
		ArrayList<String[]> inventoryCheckContents=new ArrayList<String[]>();
		String[] infor={"AAa","00001A","100","100","2017-11-06"};
		inventoryCheckContents.add(infor);
		InventoryCheckSheetVO invenCheckSheet=new InventoryCheckSheetVO();
		invenCheckSheet.setInventoryCheckSheet(inventoryCheckContents);
		return invenCheckSheet;
	}
	public int getAveragePrice(String goodsID){
		return 0;
	}
	public String getProductionDate(String goodsID){
		return "2017-11-06";
	}

}
