package blService.billService.viewBillBlService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;

public interface ViewWareKeeperBillBlService extends Remote{
	public InventoryWarningBillVO getInventoryWarningBill(String billID)throws RemoteException;
	
	public InventoryLossBillVO getInventoryLossBill(String billID)throws RemoteException;;
	
	public InventoryOverBillVO getInventoryOverBill(String billID)throws RemoteException;; 
}
