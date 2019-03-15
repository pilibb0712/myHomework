package blStubs.commodityBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.utility.Date;
import blService.commodityBlService.StockCheckBlService;
import vo.StockCheckSheetVO;
//do some changes 2017.11.06
public class StockCheckService_Stub implements StockCheckBlService{
	public StockCheckSheetVO stockCheckSheet(Date beginTime,Date endTime) throws RemoteException{
		StockCheckSheetVO stockCheckSheet=new StockCheckSheetVO();
		ArrayList<String[]> sheet=new ArrayList<String[]>();
		String[] infor1={"¾­µä±´±´µÆ","G01010101","2017-01-01","2017-11-06_19:00:00",null,null,"-50","50"};
		String[] infor2={"ÐÇ¿Õ±´±´µÆ","G01010103",null,"2017-11-16_19:00:00","50","50",null,null};

		String[] buzinesstotal={"0","0","50","2500"};
		String[] notBuzinesstotal={"50","2500","0","0"};
		sheet.add(infor1);
		sheet.add(infor2);
		sheet.add(buzinesstotal);
		sheet.add(notBuzinesstotal);
		stockCheckSheet.setStockCheckSheet(sheet);
		return stockCheckSheet;
	}

}
