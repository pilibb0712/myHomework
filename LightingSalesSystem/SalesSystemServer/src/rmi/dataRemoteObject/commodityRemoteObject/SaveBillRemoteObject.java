package rmi.dataRemoteObject.commodityRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import blImpl.commodityBl.SaveBillController;
import blService.commodityBlService.SaveBillBlService;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;

public class SaveBillRemoteObject extends UnicastRemoteObject implements SaveBillBlService{


	/**
	 *
	 */
	private static final long serialVersionUID = 3146682936879609174L;

	public SaveBillRemoteObject() throws RemoteException {
		super();
	}

	SaveBillBlService service=new SaveBillController();

	@Override
	public boolean saveInventoryWarningBill(InventoryWarningBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return service.saveInventoryWarningBill(bill);
	}

	@Override
	public boolean saveInventoryLossBill(InventoryLossBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return service.saveInventoryLossBill(bill);
	}

	@Override
	public boolean saveInventoryOverBill(InventoryOverBillVO bill) throws RemoteException {
		// TODO Auto-generated method stub
		return service.saveInventoryOverBill(bill);
	}


	}
