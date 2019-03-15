package blStubs.commodityBlStubs;

import java.rmi.RemoteException;

import blService.commodityBlService.ActualInventoryCheckBlService;

public class ActualInventoryCheckService_Stub implements ActualInventoryCheckBlService{
	public int actualInventoryCheck(String goodsTag,int actualAmount) throws RemoteException{
		return 0;
		//0 represents the actualAmount is equal to systemAmount
		//<0 represents the actualAmount is less than systemAmount
		//>0 represents the actualAmount is more than systemAmount
	}


}
