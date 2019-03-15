package blStubs.commodityBlStubs;

import java.rmi.RemoteException;

import blService.commodityBlService.SaveBillBlService;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;

public class SaveBillService_Stub implements SaveBillBlService{
	public boolean saveInventoryWarningBill(InventoryWarningBillVO  bill) throws RemoteException{
		return true;
	}
	public boolean saveInventoryLossBill(InventoryLossBillVO  bill) throws RemoteException{
		return true;
	}
	public boolean saveInventoryOverBill(InventoryOverBillVO  bill) throws RemoteException{
		return true;
	}


}
