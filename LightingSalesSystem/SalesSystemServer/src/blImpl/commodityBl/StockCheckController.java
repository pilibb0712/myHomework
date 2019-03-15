package blImpl.commodityBl;

import java.rmi.RemoteException;

import assistant.utility.Date;
import blService.commodityBlService.StockCheckBlService;
import vo.StockCheckSheetVO;

public class StockCheckController implements StockCheckBlService{

	@Override
	public StockCheckSheetVO stockCheckSheet(Date beginTime, Date endTime) throws RemoteException {
		// TODO Auto-generated method stub
		CheckSheet checkSheet=new CheckSheet();
		StockCheckSheetVO stoCheckSh=checkSheet.getStockCheckSheet(beginTime, endTime);
		return stoCheckSh;
	}

}
