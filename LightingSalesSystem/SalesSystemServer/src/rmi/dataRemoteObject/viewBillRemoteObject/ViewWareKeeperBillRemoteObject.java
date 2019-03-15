package rmi.dataRemoteObject.viewBillRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import blImpl.bill.viewBillBl.ViewWareKeeperBillBlController;
import blService.billService.viewBillBlService.ViewWareKeeperBillBlService;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;

public class ViewWareKeeperBillRemoteObject extends UnicastRemoteObject implements ViewWareKeeperBillBlService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3052585456465250479L;

	public ViewWareKeeperBillRemoteObject() throws RemoteException {
		super();
	}
	
	private ViewWareKeeperBillBlService service=new ViewWareKeeperBillBlController();

	@Override
	public InventoryWarningBillVO getInventoryWarningBill(String billID) throws RemoteException {
		return service.getInventoryWarningBill(billID);
	}

	@Override
	public InventoryLossBillVO getInventoryLossBill(String billID) throws RemoteException {
		return service.getInventoryLossBill(billID);
	}

	@Override
	public InventoryOverBillVO getInventoryOverBill(String billID) throws RemoteException {
		return service.getInventoryOverBill(billID);
	}

}
