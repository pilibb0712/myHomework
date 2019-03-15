package rmi.dataRemoteObject.commodityRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import assistant.utility.Date;
import blImpl.commodityBl.StockCheckController;
import blService.commodityBlService.StockCheckBlService;
import vo.StockCheckSheetVO;

public class StockCheckRemoteObject extends UnicastRemoteObject implements StockCheckBlService{


	/**
	 *
	 */
	private static final long serialVersionUID = 181263013225746099L;

	public StockCheckRemoteObject() throws RemoteException {
		super();
	}

	StockCheckBlService service=new StockCheckController();

	@Override
	public StockCheckSheetVO stockCheckSheet(Date beginTime, Date endTime) throws RemoteException {
		// TODO Auto-generated method stub
		return service.stockCheckSheet(beginTime, endTime);
	}


	}
